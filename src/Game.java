import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import javafx.scene.image.Image;

import static java.util.Objects.isNull;

/**
 * @author James, Luke, and Joe
 * Fixing the game class.
 */
public class Game extends Application {

    // title.
    private static final String GAME_TITLE = "Boulder Dash";

    // canvas.
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;
    private Canvas canvas;

    // cell.
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // grid.
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 23;

    // temp.
    private static final int GRID_CELL_WIDTH = 25;
    private static final int GRID_CELL_HEIGHT = 25;

    // entity map.
    private static Entity[][] cave;

    // timeline.
    private Timeline tickTimeline;

    // player.
    private static int rowWherePlayerIs;
    private static int colWherePlayerIs;

    // control registering -> actively held keys.
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();

    // view.
    private static final View view = new View(1);

    /**
     * // TODO: write javadoc when method is finalised
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // this has to be done for some reason.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        // setting scene.
        BorderPane root = (BorderPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout.fxml")));
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);
        // setting stage.
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

        // setting control registering.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);

        loadingCave();

        // setting tick-based system.
        Timeline tickTimeline = new Timeline(new KeyFrame(Duration.millis(125), event -> tick()));
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
    }

    // handling non-standard key-to-action relationships.
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
        Cave charCave = new Cave("Regular Cave", "level-1.txt");
        cave = new Entity[charCave.getTilesTall()][charCave.getTilesWide()];
        charCave.printCave();
        char[][] reduceGets = charCave.getCave();

        for (int row = 0; row < charCave.getTilesTall(); row++) {
            for (int col = 0; col < charCave.getTilesWide(); col++) {
                char tileChar = reduceGets[row][col];

                cave[row][col] = switch (tileChar) {
                    case '#' -> new Wall(row, col, WallType.NORMAL_WALL);
                    case 'T' -> new Wall(row, col, WallType.TITANIUM_WALL);
                    case 'M' -> new Wall(row, col, WallType.MAGIC_WALL);
                    case 'E' -> new Exit(row, col, 5);
                    case 'R' -> new LockedDoor(row, col, Colour.RED);
                    case 'G' -> new LockedDoor(row, col, Colour.GREEN);
                    case 'B' -> new LockedDoor(row, col, Colour.BLUE);
                    case 'Y' -> new LockedDoor(row, col, Colour.YELLOW);
                    case 'r' -> new Key(row, col, Colour.RED);
                    case 'g' -> new Key(row, col, Colour.GREEN);
                    case 'b' -> new Key(row, col, Colour.BLUE);
                    case 'y' -> new Key(row, col, Colour.YELLOW);
                    case 'P' -> new Player(row, col);
                    case 'O' -> new Boulder(row, col);
                    case 'V' -> new Diamond(row, col);
                    case 'W' -> new Butterfly(row, col);
                    case 'X' -> new Firefly(row, col);
                    case 'F' -> new Frog(row, col);
                    case 'A' -> new Amoeba(row, col, 10, 10);
                    default -> new Dirt(row, col);
                };
                if (tileChar == 'P') {
                    rowWherePlayerIs = row;
                    colWherePlayerIs = col;
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
        replaceEntity(oldX, oldY, new Dirt(oldX, oldY));
    }

    /**
     * Changes the entity type in the levelState at x,y
     * @param x new x position to be moved to
     * @param y new y position to be moved to
     * @param entity to be replaced with
     */
    public static void replaceEntity(int x, int y, Entity entity) {
        //levelState[y][x] = entity;
    }

    // made this for no reason
    public static void swapEntities(int dy, int dx) {
        boolean moved = !(dy == 0 && dx == 0);
        if (moved) {
            Entity a = cave[rowWherePlayerIs][colWherePlayerIs];
            Entity b = cave[rowWherePlayerIs+dy][colWherePlayerIs+dx];
            Entity c;
            c = a;
            cave[rowWherePlayerIs][colWherePlayerIs] = cave[rowWherePlayerIs+dy][colWherePlayerIs+dx];
            cave[rowWherePlayerIs+dy][colWherePlayerIs+dx] = c;

            rowWherePlayerIs += dy;
            colWherePlayerIs += dx;
        }
    }

    public static void pathTrail(int dy, int dx) {
        boolean moved = !(dy == 0 && dx == 0);
        if (moved) {
            boolean onMap = !(isNull(cave[rowWherePlayerIs+dy][colWherePlayerIs+dx]));
            if (onMap) {
                Entity a = cave[rowWherePlayerIs][colWherePlayerIs];
                cave[rowWherePlayerIs][colWherePlayerIs] = new Path(rowWherePlayerIs, colWherePlayerIs);
                cave[rowWherePlayerIs+dy][colWherePlayerIs+dx] = a;

                checkForChangeInView(colWherePlayerIs, rowWherePlayerIs, colWherePlayerIs+dx, rowWherePlayerIs+dy);

                rowWherePlayerIs += dy;
                colWherePlayerIs += dx;
            }
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
            KeyCode intendedKey = pressedKeys.peek();
            Player player = (Player) cave[rowWherePlayerIs][colWherePlayerIs];
            int[] diff = player.move(intendedKey);
            pathTrail(diff[0], diff[1]);
        }
        draw();
    }

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
                mapWeCanSee[y - yStart][x - xStart] = cave[y][x];
            }
        }

        for (int y = 0; y < 16; y++) {
            for (int x = 0; x < 30; x++) {
                Entity entity = mapWeCanSee[y][x];
                javafx.scene.image.Image sprite = entity.getSprite();
                gc.drawImage(sprite, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT, GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
            }
        }
    }

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
            }
        } else if (view.getView() == 3) {
            if (xBefore == 24 && playerX == 25) {
                view.changeViewMode(4);
            }
        } else if (view.getView() == 4) {
            if (xBefore == 17 && playerX == 16) {
                view.changeViewMode(3);
            } else if (yBefore == 8 && playerY == 7) {
                view.changeViewMode(2);
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
