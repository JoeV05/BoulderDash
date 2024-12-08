import javafx.scene.image.Image;

/**
 * Represents a path tile in the game. Can be walked through, or spread to by
 * amoeba.
 * @author Joseph Vinson
 */

public class Path extends Walkable {
    /**
     * Creates a path tile with the given (x, y) coordinates.
     * @param x The x coordinate of the path.
     * @param y The y coordinate of the path.
     */
    public Path(int x, int y) {
        super(x, y, new Image("./sprites/path.png"));
    }
}
