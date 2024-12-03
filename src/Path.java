import javafx.scene.image.Image;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Path extends Tile{

    public Path(int x, int y) {
        super(x, y, true, TileType.PATH, new Image("./sprites/path.png"));
    }
}
