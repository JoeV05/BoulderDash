import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {

    @FXML
    private Canvas demoCanvas;

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

    @FXML
    public void initialize() {

        Image brickImage = new Image("Brick.png");
        GraphicsContext gc = demoCanvas.getGraphicsContext2D();

        //Test for drawing graphics using iteration ONLY
        for (int i = 0; i <= 12; i++) {
            for (int j = 0; j <= 10; j++) {
                gc.drawImage(brickImage, i * 48, j * 48, 48, 48);
            }
        }


    }
}
