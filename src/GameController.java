import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 640;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int CELL_WIDTH = 32;
    private static final int CELL_HEIGHT = 32;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;

    @FXML
    private static Canvas demoCanvas;

    @FXML
    public void drawPlayerF() {
        System.out.println("Front of player");
        Image image = new Image("Spaceman_Front.png");
        displaySprite(image);
    }

    @FXML
    public void drawPlayerL() {
        System.out.println("Left Side of player");
        Image image = new Image("Spaceman_Left.png");
        displaySprite(image);
    }

    @FXML
    public void drawPlayerR() {
        System.out.println("Right Side of player");
        Image image = new Image("Spaceman_Right.png");
        displaySprite(image);
    }

    @FXML
    public void drawPlayerB() {
        System.out.println("Back of player");
        Image image = new Image("Spaceman_Back.png");
        displaySprite(image);
    }

    public void displaySprite(Image image) {
        GraphicsContext gc = demoCanvas.getGraphicsContext2D();
        gc.drawImage(image, 200, 200, 100, 100);
    }

    //made static so I can call from the other class
    //IDK what the proper way to do this is currently
    @FXML
    public static void drawGame(Entity[][] levelState) {
        //Stage is 640px
        for (int i = 0; i < (CANVAS_HEIGHT/CELL_HEIGHT); i++) {
            for (int j = 0; j < (CANVAS_WIDTH/CELL_WIDTH); j++) {
                Image currentImage = levelState[j][i].getSprite();
                GraphicsContext gc = demoCanvas.getGraphicsContext2D();
                gc.drawImage(currentImage, j * CELL_WIDTH, i * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

    @FXML
    public void initialize() {

        Image brickImage = new Image("Brick.png");
        GraphicsContext gc = demoCanvas.getGraphicsContext2D();

        //Test for drawing graphics using iteration ONLY
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                gc.drawImage(brickImage, i * 32, j * 32, 32, 32);
            }
        }
        //static test to see if drawGame worked
        //drawGame(Game.initialLevel());
    }
}
