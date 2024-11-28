import java.awt.*;
import javafx.scene.image.Image;
/**
 * @author Joseph Vinson
 */

public class Boulder extends FallingEntity {

    public Boulder(int x, int y, Image image) {
        super(x, y, FallingType.BOULDER,image);
    }
}
