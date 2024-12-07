/**
 * Represents a wall that should have some action performed every tick in the
 * game.
 * @author Joseph Vinson
 * @version 1.0
 */
public abstract class ActionWall extends Wall {
    /**
     * Creates an action wall at the given (x, y) coordinates with the given
     * wall type.
     * @param x The x coordinate of the action wall.
     * @param y The y coordinate of the action wall.
     * @param wallType The type of the action wall.
     */
    public ActionWall(int x, int y, WallType wallType) {
        super(x, y, wallType);
    }

    /**
     * Used for actions meant to update the action wall on a tick.
     */
    public abstract void tick();
}
