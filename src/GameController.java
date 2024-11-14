import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameController {

    @FXML
    private Canvas canvas;

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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 200, 200, 100, 100);
    }
}
