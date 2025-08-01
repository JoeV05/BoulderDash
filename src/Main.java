import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * GUI class to render the game on user screen. Allows the user to interact
 * with the game.
 * @author James Harvey, Luke Brace, Joseph Vinson, Joe Devlin
 */
public class Main extends Application {
    // viewing system for managing the visible area of the game
    public static final View VIEW = new View(1);
    public static final int GRID_CELL_WIDTH = 25;
    public static final int GRID_CELL_HEIGHT = 25;
    public static final String GAME_TITLE = "Boulder Dash";
    public static final int CANVAS_WIDTH = 750;
    public static final int CANVAS_HEIGHT = 432;
    public static final Duration TICK_INTERVAL = Duration.millis(125);
    public static final int MAP_SEEN_WIDTH = 30;
    public static final int MAP_SEEN_HEIGHT = 16;
    public static final int X_START_INDEX = 0;
    public static final int X_END_INDEX = 1;
    public static final int Y_START_INDEX = 2;
    public static final int Y_END_INDEX = 3;
    private static Canvas canvas;
    //Main uses singleton design pattern, only one instance stored as static
    private static Main theMain;
    private static boolean inLevel;
    // control registering -> actively held keys.
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();
    private Timeline tickTimeline;
    private int autoSaveCounter = 0;
    private static final int AUTO_SAVE_INTERVAL = 40;
    /**
     * Set the main class to store the instance when the instance is made.
     */
    @Override
    public void init() {
        theMain = this;
    }

    /**
     * Retrieve the instance of main.
     * @return Main instance.
     */
    public static Main getMain() {
        return theMain;
    }

    /**
     * Method used to set off the game. Sets up the GUI to display the current
     * view, tells Game to load the level, sets events to handle pressing and
     * releasing keys via helper functions and sets up timeline for calling
     * tick method.
     * @param primaryStage The stage that the GUI is rendered on.
     * @throws IOException Throw an exception if there is an error while
     * loading the fxml file for the menu screen.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

        primaryStage.setTitle(GAME_TITLE);

        KeyFrame k = new KeyFrame(TICK_INTERVAL, event -> tick());
        tickTimeline = new Timeline(k);
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        tickTimeline.play();
        Pane root = FXMLLoader.load(getClass().getResource("menu.fxml"));

        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);

        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // TODO - Replace movement system

    /**
     * If a key has not already been seen by the program as having been pressed
     * it is added to the queue of pressed keys and the hash set of seenKeys.
     * @param event Even generated by key being pressed.
     */
    public void handleKeyPressed(KeyEvent event) {
        if (!seenKeys.contains(event.getCode())) {
            pressedKeys.add(event.getCode());
            seenKeys.add(event.getCode());
        }
        event.consume();
    }

    /**
     * Remove key presses from the presses the game handles.
     * @param event Key press event to remove.
     */
    public void handleKeyReleased(KeyEvent event) {
        pressedKeys.remove(event.getCode());
        seenKeys.remove(event.getCode());
        event.consume();
    }

    /**
     * This method is called periodically by the tick timeline,
     * allowing the Game class to call its own tick function,
     * which in turn triggers the tick function of any entity
     * on the map that needs to be updated every tick.
     */
    public void tick() {
        if (!Main.inLevel) {
            return;
        }
        if (!pressedKeys.isEmpty()) {
            Player p = Player.getPlayer();
            KeyCode intendedKey = pressedKeys.peek();
            p.move(intendedKey);
        }
        Game.getGame().tick();
        draw();

        autoSaveCounter++;
        if (autoSaveCounter > AUTO_SAVE_INTERVAL) {
            autoSaveCounter = 0;
            Game.getGame().saveGame("save.txt");
        }
    }

    // TODO - display the score
    // TODO - better separation of responsibilities

    /**
     * Draw the current level state on the canvas. Calculates what the player
     * can see given their current view of the map, then loops over the
     * entities in that area of the map and draws them.
     */
    private void draw() {
        if (!Main.inLevel) {
            return;
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int[] playerSees = VIEW.getViewable();
        int xStart = playerSees[X_START_INDEX];
        int xEnd = playerSees[X_END_INDEX];
        int yStart = playerSees[Y_START_INDEX];
        int yEnd = playerSees[Y_END_INDEX];

        Entity[][] mapSeen = new Entity[MAP_SEEN_HEIGHT][MAP_SEEN_WIDTH];

        for (int y = yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                mapSeen[y - yStart][x - xStart] = Game.getGame().getMap()[y][x];
            }
        }

        for (int y = 0; y < MAP_SEEN_HEIGHT; y++) {
            for (int x = 0; x < MAP_SEEN_WIDTH; x++) {
                Entity entity = mapSeen[y][x];
                Image sprite = entity.getSprite();
                gc.drawImage(sprite, x * GRID_CELL_WIDTH, y * GRID_CELL_HEIGHT,
                        GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
            }
        }
    }

    public void drawLevel() {
    }

    /**
     * Set the variable tracking if the game is in a level to the given value.
     * @param setInLevel Value to set the in level variable to.
     */
    public static void setInLevel(boolean setInLevel) {
        Main.inLevel = setInLevel;
    }

    /**
     * Get the canvas the game is drawn on.
     * @return Canvas object.
     */
    public static Canvas getCanvas() {
        return canvas;
    }

    /**
     * Launch the game.
     * @param args Optional arguments for running the program.
     */
    public static void main(String[] args) {
        Main.inLevel = false;
        launch(args);
    }
}
