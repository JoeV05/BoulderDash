import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Diamond extends FallingEntity {

    public Diamond(int x, int y, Image image) {
        super(x, y, FallingType.DIAMOND,image);
    }
}
