import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Sample application that demonstrates the use of JavaFX Canvas for a Game.
 * This class is intentionally not structured very well. This is just a starting point to show
 * how to draw an image on a canvas, respond to arrow key presses, use a tick method that is
 * called periodically, and use drag and drop.
 *
 * Do not build the whole application in one file. This file should probably remain very small.
 *
 * @author Liam O'Reilly
 */
public class Game extends Application {
    // The dimensions of the window
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 500;
    private static final String WINDOW_TITLE = "BOULDER DASH DEMO";

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 500;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 12;

    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    @FXML
    private Canvas canvas;

    // Loaded images
    private Image playerImage;
    private Image dirtImage;
    private Image iconImage;

    // X and Y coordinate of player on the grid.
    private int playerX = 0;
    private int playerY = 0;

    // Timeline which will cause tick method to be called periodically.
    private Timeline tickTimeline;

    // used for movement logic
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();
    /**
     * Set up the new application.
     * @param primaryStage The stage that is to be used for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Load the main scene.
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GameScreenDemo.fxml"));
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            buildGUI(root);
            drawGame();

            //Place the main scene on stage and show it.
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();

            tickTimeline = new Timeline(new KeyFrame(Duration.millis(125), event -> tick()));
            scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
            // Loop the timeline forever
            tickTimeline.setCycleCount(Animation.INDEFINITE);
            tickTimeline.play();


        }  catch(Exception e) {
            e.printStackTrace();
        }
        /*// Load images. Note we use png images with a transparent background.
        playerImage = new Image("player.png");
        dirtImage = new Image("dirt.png");
        iconImage = new Image("icon.png");

        // Build the GUI
        Pane root = buildGUI();

        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Register an event handler for key presses.
        // This causes the processKeyEvent method to be called each time a key is pressed.
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));

        // Register a tick method to be called periodically.
        // Make a new timeline with one keyframe that triggers the tick method every half a second.
        tickTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> tick()));
        // Loop the timeline forever
        tickTimeline.setCycleCount(Animation.INDEFINITE);
        // We start the timeline upon a button press.

        // Display the scene on the stage
        drawGame();
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
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
     * Process a key event due to a key being pressed, e.g., to move the player.
     * @param event The key event that was pressed.
     */
    public void processKeyEvent(KeyEvent event) {
        // We change the behaviour depending on the actual key that was pressed.
        switch (event.getCode()) {
            case RIGHT:
                // Right key was pressed. So move the player right by one cell.
                playerX = playerX + 1;
                break;
            default:
                // Do nothing for all other keys.
                break;
        }

        // Redraw game as the player may have moved.
        //drawGame();

        // Consume the event. This means we mark it as dealt with. This stops other GUI nodes (buttons etc.) responding to it.
        event.consume();
    }

    /**
     * Draw the game on the canvas.
     */
    public void drawGame() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image brickImage = new Image("brick.png");


        //Test for drawing graphics using iteration ONLY
        for (int i = 0; i<=12; i++) {
            for (int j = 0; j<=10; j++) {
                gc.drawImage(brickImage, i * 48, j * 48);
            }
        }


        /*// Get the Graphic Context of the canvas. This is what we draw on.
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Clear canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Set the background to gray.
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw row of dirt images
        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
        // We draw the row at y value 2.
        for (int x = 0; x < GRID_WIDTH; x++) {
            gc.drawImage(dirtImage, x * GRID_CELL_WIDTH, 2 * GRID_CELL_HEIGHT);
        }

        // Draw player at current location
        gc.drawImage(playerImage, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);*/
    }

    public void move(KeyCode key) {
        int xBefore = playerX;
        int yBefore = playerY;
        switch (key) {
            case UP:
            case W:
                System.out.println("up");
                break;
            case DOWN:
            case S:
                System.out.println("down");
                break;
            case LEFT:
            case A:
                System.out.println("left");
                break;
            case RIGHT:
            case D:
                System.out.println("righjt");
                break;
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
            move(intendedKey);
        }
        // We then redraw the whole canvas.
        extracted();
    }

    private void extracted() {
        drawGame();
    }

    /**
     * React when an object is dragged onto the canvas.
     * @param event The drag event itself which contains data about the drag that occurred.
     */
    public void canvasDragDroppedOccurred(DragEvent event) {
        double x = event.getX();
        double y = event.getY();

        // Print a string showing the location.
        String s = String.format("You dropped at (%f, %f) relative to the canvas.", x, y);
        System.out.println(s);

        // Draw an icon at the dropped location.
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Draw the image so the top-left corner is where we dropped.
        gc.drawImage(iconImage, x, y);
        // Draw the image so the center is where we dropped.
        // gc.drawImage(iconImage, x - iconImage.getWidth() / 2.0, y - iconImage.getHeight() / 2.0);
    }

    /**
     * Create the GUI.
     */
    private void buildGUI(BorderPane root) {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setBottom(toolbar); // change this from setTop to setBottom

    }

    public static void main(String[] args) {
        launch(args);
    }
}
