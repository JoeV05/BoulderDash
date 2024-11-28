import java.awt.*;
import javafx.scene.image.Image;
/**
 * @author Joseph Vinson
 */

public class Dirt extends Tile {

    public Dirt(int x, int y, Image image) {
        super(x, y, false, TileType.DIRT,image);
    }

    public void dig() {

    }
}
