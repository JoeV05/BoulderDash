import javafx.scene.image.Image;

/**
 * @author Luke Brace
 */

public class TitaniumWall extends Wall{

    /**
     * Constructs a new Titanium Wall
     * Titanium walls cannot be destroyed
     *
     * @param x The x-coordinate of the Titanium Wall on the game map
     * @param y The y-coordinate of the Titanium Wall on the game map
     * @param image The image representing the Titanium Wall
     */
    public TitaniumWall(int x, int y, Image image) {
        super(x, y, WallType.TITANIUM_WALL, image);
    }

    /**
     * Makes the Wall unwalkable
     *
     * @return The state of the wall
     */
    @Override
    public boolean isWalkable() {
        return false;
    }
    /**
     * Makes the Wall indestructible
     *
     * @return The state of the wall
     */
    public boolean isDestructable(){
        return false;
    }
}
