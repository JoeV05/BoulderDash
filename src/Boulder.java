import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Boulder extends FallingEntity {

    public Boulder(int x, int y) {
        super(x, y, FallingType.BOULDER, new Image("./graphics/boulder.png"));
    }
}
