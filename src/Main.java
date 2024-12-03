import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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

import java.io.IOException;
import java.util.*;


/**
 *Represents the main game logic and state managment for the Boulder Game
 *This class initilises the game window, recieves user input and
 *handles the rendering and updating of game state
 *The game a grid based level system with entities modelled on
 *top of it using javafx for rendering

 * @author James Harvey, Luke Brace, Joseph Vinson, Joe Devlin
 * Represents the game state, stores level data and renders game window
 */
public class Main extends Application {

    // title.
    private static final String GAME_TITLE = "Boulder Dash";

    // canvas. canvas is contained within window.
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;
    private Canvas canvas;

    //TODO: Remove, set sprites to 32px
    // TODO - There are already variables for 32px, can whoever added the comment
    //  explain what it means on the discord
    private static final int GRID_CELL_WIDTH = 25;
    private static final int GRID_CELL_HEIGHT = 25;

    // control registering -> actively held keys.
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();

    // viewing system for managing the visible area of the game
    private static final View view = new View(1);

    /**
     *
     * @param primaryStage The stage that is to be used for the application.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Declare new canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        // setting the scene.
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout.fxml")));
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
        Game.loadingCave();

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
        Game.getGame().tick();
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
                mapWeCanSee[y - yStart][x - xStart] = Game.getGame().getMap()[y][x];
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

    public static void main(String[] args) {
        Game.getGame();
        launch(args);
    }
}
