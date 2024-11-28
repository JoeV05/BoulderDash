import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Diamond extends FallingEntity {

    public Diamond(int x, int y) {
        super(x, y, FallingType.DIAMOND, new Image("./graphics/diamond.png"));
    }
}
