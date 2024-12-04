import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

// TODO - javadoc class comment

public class GameController {

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 23;

    @FXML
    private static Canvas canvas;

    // TODO - javadoc method comment
    // TODO - maybe not needed anymore?
    //@FXML
    // arrays work y then x.
    public static void drawGame(Entity[][] levelState) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                Image currentImage = levelState[y][x].getSprite();
                gc.drawImage(currentImage, x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

    // TODO - javadoc method comment
    @FXML
    public void initialize() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        // TODO - gc maybe not needed?
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}
