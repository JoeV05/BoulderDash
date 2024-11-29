import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Diamond extends FallingEntity {
    /**
     *
     * @param x The x-coordinate of the Titanium Wall on the game map
     * @param y The y-coordinate of the Titanium Wall on the game map
     * @param image The image representing the Diamond
     */
    public Diamond(int x, int y, Image image) {
        super(x, y, FallingType.DIAMOND,image);
    }

    /**
     *
     * @param
     */
    public void Collect (Player player) {
        //Remove the object
        //Give to Player
    }
}
