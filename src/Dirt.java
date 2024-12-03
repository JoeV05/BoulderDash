import javafx.scene.image.Image;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Dirt extends Tile {

    public Dirt(int x, int y) {
        super(x, y, true, TileType.DIRT, new Image("./sprites/dirt.png"));
    }
}
