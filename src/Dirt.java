import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Dirt extends Tile {

    public Dirt(int x, int y) {
        super(x, y, false, TileType.DIRT, new Image("./sprites/dirt.png"));
    }

    public void dig() {

    }
}
