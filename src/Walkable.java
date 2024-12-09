import javafx.scene.image.Image;

/**
 * Represents an entity that can be walked on by the player.
 * @author Joseph Vinson
 * @version 1.1
 */
public abstract class Walkable extends Entity {
    /**
     * Constructor for walkable
     * @param x x position
     * @param y y position
     * @param sprite image
     */
    public Walkable(int x, int y, Image sprite) {
        super(x, y, sprite);
    }
}
