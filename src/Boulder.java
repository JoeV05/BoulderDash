import javafx.scene.image.Image;

// TODO - javadoc class comment

/**
 * @author Joseph Vinson
 */
public class Boulder extends FallingEntity {

    public Boulder(int x, int y) {
        super(x, y, FallingType.BOULDER, new Image("./sprites/boulder.png"));
    }
}
