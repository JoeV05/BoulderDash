import com.sun.javafx.css.CalculatedValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * GUI controller for the Main class.
 * @author James Harvey, Joe Devlin
 */
public class GameController{
    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 750;
    private static final int CANVAS_HEIGHT = 400;
    @FXML
    private static Canvas canvas;
    @FXML
    public ImageView scoreFirstDigit;
    @FXML
    public ImageView scoreSecondDigit;
    @FXML
    public ImageView timerFistDigit;
    @FXML
    public ImageView timerSecondDigit;
    @FXML
    public ImageView timerThirdDigit;

    /**
     * Sets an image based off whether it is yellow or white and what the
     * number the image should represent is.
     * @param image Image to set.
     * @param isYellow Whether the image should be set to a yellow number.
     * @param number The number the image should be set to.
     */
    private static void setImage(ImageView image, boolean isYellow, int number) {
        String path = String.format("/%s/%d.png",
                isYellow ? "yellow" : "white", number);
        System.out.println(path);
        Image img = new Image(GameController.class.getResourceAsStream(path));
        image.setImage(img);
    }

    /**
     *Sets all digits to starting numbers
     */
    @FXML
    public void initialize() {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        setImage(scoreFirstDigit, true, 0);
        setImage(scoreSecondDigit, true, 0);

        setImage(timerFistDigit, false, 0);
        setImage(timerSecondDigit, false, 0);
        setImage(timerThirdDigit, false, 0);
    }
}
