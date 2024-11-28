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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author James, Luke, and Joe
 * Fixing the game class. The errors are mainly due to the relationships between Tile, Wall, and Entity.
 * I'll be changing this class alot cant lie. It'll get worse before it gets better.
 */
public class Game extends Application {
    // window properties.
    private static final int WINDOW_WIDTH = 840;
    private static final int WINDOW_HEIGHT = 640;
    private static final String WINDOW_TITLE = "Boulder Dash";

    // canvas. canvas is contained within window.
    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 640;
    private Canvas canvas;

    // cell.
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // grid.
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 23;

    // entity map.
    private static Entity[][] map;

    // timeline.
    private Timeline tickTimeline;

    // player.
    private Player player;

    // control registering -> actively held keys.
    private final Queue<KeyCode> pressedKeys = new LinkedList<>();
    private final HashSet<KeyCode> seenKeys = new HashSet<>();


    /**
     *
     * @param primaryStage The stage that is to be used for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Load the main scene.
            BorderPane root = (BorderPane)FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameScreenDemo.fxml")));
            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

            //Place the main scene on stage and show it.
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();

            // james want me to do movement so output to screen
            player = new Player(0, 0);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
            scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyReleased);
            Timeline tickTimeline = new Timeline(new KeyFrame(Duration.millis(125), event -> tick()));
            tickTimeline.setCycleCount(Animation.INDEFINITE);
            tickTimeline.play();

            initialLevel();
            //drawGame();
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

    // its likely we'll have keys that the menu screen will need.
    // escape [esc] for example. so we'll keep this code here for now.
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
     * Read the characters from level.txt and convert to 2D array
     */
    public void initialLevel() {
        int rowLength = GRID_WIDTH;
        int colHeight = GRID_HEIGHT;
        level = new char[colHeight][rowLength];
        File file = new File("src/level.txt");
        System.out.println(file.exists());
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int col = 0; col < colHeight; col++) {
            level[col] = scanner.nextLine().toCharArray();
        }
        System.out.println(Arrays.deepToString(level));

        for (int col = 0; col < colHeight; col++) {
            for (int row = 0; row < rowLength; row++) {
                switch (level[col][row]) {
                    case '#':
                        levelState[col][row] = new Wall(row, col, WallType.NORMAL_WALL);
                        break;
                    case 'T':
                        levelState[col][row] = new Wall(row, col, WallType.TITANIUM_WALL);
                        break;
                    case 'M':
                        levelState[col][row] = new Wall(row, col, WallType.MAGIC_WALL);
                        break;
                    case 'E':
                        levelState[col][row] = new Exit(row, col, );
                        //Need to edit and read metadata at the end of the file to determine the required score
                        break;
                    case 'R':
                        levelState[col][row] = new LockedDoor(row, col, Colour.RED);
                        break;
                    case 'G':
                        levelState[col][row] = new LockedDoor(row, col, Colour.GREEN);
                        break;
                    case 'B':
                        levelState[col][row] = new LockedDoor(row, col, Colour.BLUE);
                        break;
                    case 'Y':
                        levelState[col][row] = new LockedDoor(row, col, Colour.YELLOW);
                        break;
                    case 'r':
                        levelState[col][row] = new Key(row, col, Colour.RED);
                        break;
                    case 'g':
                        levelState[col][row] = new Key(row, col, Colour.GREEN);
                        break;
                    case 'b':
                        levelState[col][row] = new Key(row, col, Colour.BLUE);
                        break;
                    case 'y':
                        levelState[col][row] = new Key(row, col, Colour.YELLOW);
                        break;
                    case 'P':
                        levelState[col][row] = new Player(row, col);
                        break;
                    case 'O':
                        levelState[col][row] = new Boulder(row, col);
                        break;
                    case '*':
                        levelState[col][row] = new Diamond(row, col);
                        break;
                    case 'W':
                        levelState[col][row] = new Butterfly(row, col);
                        //Metadata needed for left/right-hand wall cling
                        break;
                    case 'X':
                        levelState[col][row] = new Firefly(row, col);
                        //Metadata needed for left/right-hand wall cling
                        break;
                    case 'F':
                        levelState[col][row] = new Frog(row, col);
                        break;
                    case 'A':
                        levelState[col][row] = new Amoeba(row, col, );
                        //Metadata needed for Amoeba maximumSize
                        break;
                    default:
                        levelState[col][row] = new Dirt(row, col);
                }
            }
        }
        updateSprites();
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
        levelState[y][x] = entity;
    }

    private void updateSprites() {
        GameController.drawGame(levelState);
    }

    /**
     * Returns the entity at the given x/y coords
     * @return Entity to be moved
     */
    public Entity getEntity(int x, int y) {
        return levelState[y][x];
    }

    /**
     * Draw the game on the canvas.
     */
    @FXML
//    public void drawGame() {
//
//        Image brickImage = new Image("Brick.png");
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        //Test for drawing graphics using iteration ONLY
//        for (int i = 0; i <= 12; i++) {
//            for (int j = 0; j <= 10; j++) {
//                gc.drawImage(brickImage, i * 48, j * 48, 48, 48);
//            }
//        }
//
//        /*// Get the Graphic Context of the canvas. This is what we draw on.
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        // Clear canvas
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        // Set the background to gray.
//        gc.setFill(Color.GRAY);
//        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//        // Draw row of dirt images
//        // We multiply by the cell width and height to turn a coordinate in our grid into a pixel coordinate.
//        // We draw the row at y value 2.
//        for (int x = 0; x < GRID_WIDTH; x++) {
//            gc.drawImage(dirtImage, x * GRID_CELL_WIDTH, 2 * GRID_CELL_HEIGHT);
//        }
//
//        // Draw player at current location
//        gc.drawImage(playerImage, playerX * GRID_CELL_WIDTH, playerY * GRID_CELL_HEIGHT);*/
//    }
    /**
     * Process a key event due to a key being pressed, e.g., to move the player.
     * @param event The key event that was pressed.
     */
    public void processKeyEvent(KeyEvent event) {
        // We change the behaviour depending on the actual key that was pressed.
        switch (event.getCode()) {
            case RIGHT:
                // Right key was pressed. So move the player right by one cell.

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
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    private int count = 0;
    public void tick() {
        count ++;
        System.out.print("tick num -> " + count + " , movement -> ");
        if (!pressedKeys.isEmpty()) {
            KeyCode intendedKey = pressedKeys.peek();
            player.move(intendedKey);
        }
        System.out.println(" ");
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
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        // Draw the image so the top-left corner is where we dropped.
        //gc.drawImage(iconImage, x, y);
        // Draw the image so the center is where we dropped.
        // gc.drawImage(iconImage, x - iconImage.getWidth() / 2.0, y - iconImage.getHeight() / 2.0);
    }

    /**
     * Create the GUI.
     * @return The panel that contains the created GUI.
     */
    private Pane buildGUI() {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a global variable so other methods can access it.
        //canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        //root.setCenter(canvas);

        // Create a toolbar with some nice padding and spacing
        HBox toolbar = new HBox();
        toolbar.setSpacing(10);
        toolbar.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(toolbar);

        // Create the toolbar content

        // Reset Player Location Button
        Button resetPlayerLocationButton = new Button("Reset Player");
        toolbar.getChildren().add(resetPlayerLocationButton);


        // Center Player Button
        Button centerPlayerLocationButton = new Button("Center Player");
        toolbar.getChildren().add(centerPlayerLocationButton);

        // Tick Timeline buttons
        Button startTickTimelineButton = new Button("Start Ticks");
        Button stopTickTimelineButton = new Button("Stop Ticks");
        // We add both buttons at the same time to the timeline (we could have done this in two steps).
        toolbar.getChildren().addAll(startTickTimelineButton, stopTickTimelineButton);
        // Stop button is disabled by default
        stopTickTimelineButton.setDisable(true);

        // Set up the behaviour of the buttons.
        startTickTimelineButton.setOnAction(e -> {
            // Start the tick timeline and enable/disable buttons as appropriate.
            startTickTimelineButton.setDisable(true);
            tickTimeline.play();
            stopTickTimelineButton.setDisable(false);
        });

        stopTickTimelineButton.setOnAction(e -> {
            // Stop the tick timeline and enable/disable buttons as appropriate.
            stopTickTimelineButton.setDisable(true);
            tickTimeline.stop();
            startTickTimelineButton.setDisable(false);
        });

        // Setup a draggable image.
        ImageView draggableImage = new ImageView();
        draggableImage.setImage(iconImage);
        toolbar.getChildren().add(draggableImage);

        // This code setup what happens when the dragging starts on the image.
        // You probably don't need to change this (unless you wish to do more advanced things).
        draggableImage.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Mark the drag as started.
                // We do not use the transfer mode (this can be used to indicate different forms
                // of drags operations, for example, moving files or copying files).
                Dragboard db = draggableImage.startDragAndDrop(TransferMode.ANY);

                // We have to put some content in the clipboard of the drag event.
                // We do not use this, but we could use it to store extra data if we wished.
                ClipboardContent content = new ClipboardContent();
                content.putString("Hello");
                db.setContent(content);

                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        // This code allows the canvas to receive a dragged object within its bounds.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // Mark the drag as acceptable if the source was the draggable image.
                // (for example, we don't want to allow the user to drag things or files into our application)
                if (event.getGestureSource() == draggableImage) {
                    // Mark the drag event as acceptable by the canvas.
                    event.acceptTransferModes(TransferMode.ANY);
                    // Consume the event. This means we mark it as dealt with.
                    event.consume();
                }
            }
        });

        // This code allows the canvas to react to a dragged object when it is finally dropped.
        // You probably don't need to change this (unless you wish to do more advanced things).
        canvas.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // We call this method which is where the bulk of the behaviour takes place.
                canvasDragDroppedOccurred(event);
                // Consume the event. This means we mark it as dealt with.
                event.consume();
            }
        });

        // Finally, return the border pane we built up.
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
