import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;

/**
*Represents the main game logic and state managment for the Boulder Game
*This class initilises the game window, recieves user input and
*handles the rendering and updating of game state
*The game a grid based level system with entities modelled on
*top of it using javafx for rendering
*/
/**
 * @author James Harvey, Luke Brace, Joseph Vinson, Joe Devlin
 * Represents the game state, stores level data and renders game window
 */
public class Game extends Application {

    // title.
    private static final String GAME_TITLE = "Boulder Dash";

    // constants for game window and settings
    private static final int WINDOW_WIDTH = 750;
    private static final int WINDOW_HEIGHT = 400;

    // canvas. canvas is contained within window.
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;
    private Canvas canvas;

    // constants for grid and cell sizes
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // constants for grid and cell sizes
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 23;

    //TODO: Remove, set sprites to 32px
    // TODO - There are already variables for 32px, can whoever added the comment
    //  explain what it means on the discord
    private static final int GRID_CELL_WIDTH = 25;
    private static final int GRID_CELL_HEIGHT = 25;

    // the map of entities representing the game
    private static Entity[][] map;

    // game timeline for peroidic updates
    //TODO: unsure about this comment not sure how ticktimeLine works
    private Timeline tickTimeline;

    // control registering -> actively held keys.
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();

    // viewing system for managing the visible area of the game
    private static final View view = new View(1);
    //list of entities requiring special logic
    private static ArrayList<FallingEntity> fallingEntities;//entites effected by gravity
    private static ArrayList<ActionWall> actionWalls;//special attribute walls

    public static void addFallingEntity(FallingEntity e) {
        fallingEntities.add(e);//Adds a falling entity to the list of entites effected by gravity
    }

    public static void removeFallingEntity(FallingEntity e) {
        if (fallingEntities.contains(e)) {//removes a falling entity from the list
            fallingEntities.remove(e);
        }
    }

    // TODO - Check tile where they are trying to move, maybe split method up
    //Validates whether a move is allowed based on the current grid
    //x = the current x coord of the entity
    //y = the current y coord of the entity
    //dir = the intended direction of the move
    // return true if the move is valid
    public static boolean isValidMove(int x, int y, Direction dir) {
        switch (dir) {
            case UP:
                return y > 0;
            case DOWN:
                // TODO - WHY DOES THIS NEED TO BE -2 INSTEAD OF -1
                //  WHAT THE FISH!!!
                return y < (GRID_HEIGHT - 2);
            case LEFT:
                return x > 0;
            case RIGHT:
                return x < (GRID_WIDTH - 1);
        }
        throw new LiamWetFishException("WHAT THE FISH DID YOU DO TO GET HERE");
    }

    /**
     *
     * @param primaryStage The stage that is to be used for the application.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Declare new canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        // setting the scene.
        BorderPane root = (BorderPane)FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout.fxml")));
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        // setting the stage.
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

        // setting control registering
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);

        // load the cave.
        loadingCave();

        // setting tick-based system.
        Timeline tickTimeline = new Timeline(new KeyFrame(Duration.millis(125), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
    }

    // TODO - add useful comment
    // TODO - devlin said this would have uses outside of movement (i.e. pausing the game)
    public void handleKeyPressed(KeyEvent event) {
        if (!seenKeys.contains(event.getCode())) {
            pressedKeys.add(event.getCode());
            seenKeys.add(event.getCode());
        }
        event.consume();
    }
    public void handleKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
        seenKeys.remove(event.getCode());
        event.consume();
    }

    public void loadingCave() throws FileNotFoundException {
        Cave charCave = new Cave("Cave1", "level-1.txt");
        //TODO: Add automatic cave generation, see cave class
        map = new Entity[charCave.getTilesTall()][charCave.getTilesWide()];
        charCave.printCave();
        char[][] reduceGets = charCave.getCave();

        for (int row = 0; row < charCave.getTilesTall(); row++) {
            for (int col = 0; col < charCave.getTilesWide(); col++) {
                char tileChar = reduceGets[row][col];

                switch(tileChar) {
                    case '#':
                        map[row][col] = new Wall(col, row, WallType.NORMAL_WALL);
                        break;
                    case 'T':
                        map[row][col] = new Wall(col, row, WallType.TITANIUM_WALL);
                        break;
                    case 'M':
                        MagicWall m = new MagicWall(col, row);
                        map[row][col] = m;
                        actionWalls.add(m);
                        break;
                    case 'E':
                        Exit e = new Exit(col, row, 5);
                        map[row][col] = e;
                        actionWalls.add(e);
                        break;
                    // TODO - metadata needed for unlock exit condition
                    case 'R':
                        map[row][col] = new LockedDoor(col, row, Colour.RED);
                        break;
                    case 'G':
                        map[row][col] = new LockedDoor(col, row, Colour.GREEN);
                        break;
                    case 'B':
                        map[row][col] = new LockedDoor(col, row, Colour.BLUE);
                        break;
                    case 'Y':
                        map[row][col] = new LockedDoor(col, row, Colour.YELLOW);
                        break;
                    case 'r':
                        map[row][col] = new Key(col, row, Colour.RED);
                        break;
                    case 'g':
                        map[row][col] = new Key(col, row, Colour.GREEN);
                        break;
                    case 'b':
                        map[row][col] = new Key(col, row, Colour.BLUE);
                        break;
                    case 'y':
                        map[row][col] = new Key(col, row, Colour.YELLOW);
                        break;
                    case 'O':
                        Boulder b = new Boulder(col, row);
                        map[row][col] = b;
                        fallingEntities.add(b);
                        break;
                    case 'V':
                        Diamond d = new Diamond(col, row);
                        map[row][col] = d;
                        fallingEntities.add(d);
                        break;
                    case 'W':
                        map[row][col] = new Butterfly(col, row);
                        break;
                    case 'X':
                        map[row][col] = new Firefly(col, row);
                        break;
                    // TODO - metadata needed for left/right wall cling
                    case 'F':
                        map[row][col] = new Frog(col, row);
                        break;
                    case 'A':
                        map[row][col] = new Amoeba(col, row, 10);
                        break;
                    case 'P':
                        map[row][col] = Player.getPlayer(col, row);
                        break;
                    // TODO - metadata needed for maximum Amoeba size
                    case 'D':
                        map[row][col] = new Dirt(col, row);
                        break;
                    default:
                        map[row][col] = new Path(col, row);
                        break;
                }
            }
        }
    }

    /**
     * Change the position of an Entity in the levelState
     */
    public static void updateLevel(int newX, int newY, Entity entity) {
        int oldX = entity.getX();
        int oldY = entity.getY();
        replaceEntity(newX, newY, entity);
        entity.setX(newX);
        entity.setY(newY);
        replaceEntity(oldX, oldY, new Path(oldX, oldY));
    }

    /**
     * Changes the entity type in the levelState at x,y
     * @param x new x position to be moved to
     * @param y new y position to be moved to
     * @param entity to be replaced with
     */
    public static void replaceEntity(int x, int y, Entity entity) {
        map[y][x] = entity;
    }

    /**
     * Returns the entity at the given x/y coordinates
     * @return Entity to be moved
     */
    public static Entity getEntity(int x, int y) {
        return map[y][x];
    }

    // TODO - what is this for? looks like an artefact of devlin poopfishery based off the use of deltas
    public static void swapEntities(int dy, int dx) {
        boolean moved = !(dy == 0 && dx == 0);
        if (moved) {
            Player p = Player.getPlayer();
            Entity a = map[p.getY()][p.getX()];
            Entity b = map[p.getY() +dy][p.getX() +dx];
            Entity c;
            c = a;
            map[p.getY()][p.getX()] = map[p.getY() +dy][p.getX() +dx];
            map[p.getY() +dy][p.getX() +dx] = c;

            p.setY(p.getY() + dy);
            p.setX(p.getX() + dx);
        }
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick() {
        if (!pressedKeys.isEmpty()) {
            Player p = Player.getPlayer();
            KeyCode intendedKey = pressedKeys.peek();
            p.move(intendedKey);
        }

        for (ActionWall actionWall : actionWalls) {
            actionWall.tick();
        }

        for (FallingEntity fallingEntity : fallingEntities) {
            fallingEntity.fall();
        }

        draw();
    }

    // TODO - javadoc comment
    // TODO - display the score
    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int[] whatWeCanSee = view.getViewable();
        int xStart = whatWeCanSee[0];
        int xEnd = whatWeCanSee[1];
        int yStart = whatWeCanSee[2];
        int yEnd = whatWeCanSee[3];

        Entity[][] mapWeCanSee = new Entity[16][30];

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                mapWeCanSee[y - yStart][x - xStart] = map[y][x];
            }
        }

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 30; x++) {
                Entity entity = mapWeCanSee[y][x];
                Image sprite = entity.getSprite();
                gc.drawImage(sprite, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
            }
        }
    }

    /**
     * Shifts the screen to centre the player
     * @param xBefore x-position before player movement
     * @param yBefore y-position before player movement
     * @param playerX x-position the player is currently in
     * @param playerY y-position the player is currently in
     */
    public static void checkForChangeInView(int xBefore, int yBefore, int playerX, int playerY) {
        if (view.getView() == 1) {
            if (xBefore == 24 && playerX == 25) {
                view.changeViewMode(2);
            } else if (yBefore == 13 && playerY == 14) {
                view.changeViewMode(3);
            }
        } else if (view.getView() == 2) {
            if (xBefore == 17 && playerX == 16) {
                view.changeViewMode(1);
            } else if (yBefore == 13 && playerY == 14) {
                view.changeViewMode(4);
            }
        } else if (view.getView() == 3) {
            if (xBefore == 24 && playerX == 25) {
                view.changeViewMode(4);
            } else if (yBefore == 8 && playerY == 7) {
                view.changeViewMode(1);
            }
        } else if (view.getView() == 4) {
            if (xBefore == 17 && playerX == 16) {
                view.changeViewMode(3);
            } else if (yBefore == 8 && playerY == 7) {
                view.changeViewMode(2);
            }
        }
    }

    /**
     * Getter for map
     * @return Entity [][] map
     */
    public static Entity[][] getMap(){
        return map;
    }

    public static void main(String[] args) {
        fallingEntities = new ArrayList<>();
        actionWalls = new ArrayList<>();
        launch(args);
    }
}
