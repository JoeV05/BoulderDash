import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {

    // canvas.
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;

    // cell.
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // grid.
    private static final int GRID_WIDTH = 40;
    private static final int GRID_HEIGHT = 23;

    @FXML
    private static Canvas canvas;

    @FXML
    // arrays work y then x.
    public static void drawGame(Entity[][] levelState) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int x = 0; x < GRID_WIDTH; x++) {

            for (int y = 0; y < GRID_HEIGHT; y++) {

                Image currentImage = levelState[y][x].getSprite();

                gc.drawImage(currentImage, (x+1) * CELL_WIDTH, (y+1) * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

    @FXML
    public void initialize() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}
