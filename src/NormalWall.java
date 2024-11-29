import javafx.scene.image.Image;

/**
 * @author Luke Brace / Joe Devlin
 * Identical to Titanium Wall as of 0036 29/11/2024
 * Made, so I could add sprite locations.
 */

public class NormalWall extends Wall{

    /**
     * Constructs a new Normal Wall
     * Titanium walls cannot be destroyed
     *
     * @param x The x-coordinate of the Titanium Wall on the game map
     * @param y The y-coordinate of the Titanium Wall on the game map
     */
    public NormalWall(int x, int y) {
        super(x, y, WallType.TITANIUM_WALL);
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