import javafx.scene.image.Image;

/**
 * Represents a boulder in the game.
 * @author Joseph Vinson
 * @version 1.0
 */
public class Boulder extends FallingEntity {
    /**
     * Creates a boulder at the given (x, y) coordinates.
     * @param x The x coordinate of the boulder.
     * @param y The y coordinate of the boulder.
     */
    public Boulder(int x, int y) {
        super(x, y, new Image("./sprites/boulder.png"));
    }
}
