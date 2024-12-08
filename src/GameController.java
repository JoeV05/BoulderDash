import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * GUI controller for the Main class.
 * @author James Harvey, Joe Devlin
 */
public class GameController {
    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;
    @FXML
    private static Canvas canvas;
    @FXML
    private ImageView topLeftImage1;
    @FXML
    private ImageView topLeftImage2;
    @FXML
    private ImageView topRightImage1;
    @FXML
    private ImageView topRightImage2;
    @FXML
    private ImageView topRightImage3;

    /**
     * Sets an image based off whether it is yellow or white and what the
     * number the image should represent is.
     * @param image Image to set.
     * @param isYellow Whether the image should be set to a yellow number.
     * @param number The number the image should be set to.
     */
    private void setImage(ImageView image, boolean isYellow, int number) {
        String path = String.format("/%s/%d.png",
                isYellow ? "yellow" : "white", number);
        System.out.println(path);
        Image img = new Image(GameController.class.getResourceAsStream(path));
        image.setImage(img);
    }

    /**
     *
     */
    @FXML
    public void initialize() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        setImage(topLeftImage1, true, 0);
        setImage(topLeftImage2, true, 0);
        setImage(topRightImage1, false, 0);
        setImage(topRightImage2, false, 0);
        setImage(topRightImage3, false, 0);
    }
}
