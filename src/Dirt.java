import javafx.scene.image.Image;

/**
 * Represents a dirt tile in the game.
 * @author Joseph Vinson
 * @version 1.3
 */
public class Dirt extends Walkable {
    /**
     * Creates a dirt tile with the given (x, y) coordinates.
     * @param x The x coordinate of the dirt tile.
     * @param y The y coordinate of the dirt tile.
     */
    public Dirt(int x, int y) {
        super(x, y, new Image("./sprites/dirt.png"));
    }
}
