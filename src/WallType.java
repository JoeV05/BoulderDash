/**
 * Enumeration to represent the different types of wall
 * @author Joseph Vinson
 */

public enum WallType {
    /**
     * A standard wall that blocks all movement. Normal walls are destructible
     * by explosions and can be replaced by a path tile when destroyed.
     */
    NORMAL_WALL,
    /**
     * An indestructible wall that blocks all movement. Titanium walls cannot
     * be destroyed by explosions, making them permanent obstacles.
     */
    TITANIUM_WALL,
    /**
     * A special wall that transforms falling objects:
     * Boulders entering a magic wall become diamonds.
     * Diamonds entering a magic wall become boulders.
     * Magic walls block movement but are vulnerable to explosions, which destroy them.
     */
    MAGIC_WALL,
    /**
     * Acts as a titanium wall until unlocked by the player using
     * The corresponding key. Once unlocked, the door transforms into
     * a walkable path tile
     */
    LOCKED_DOOR
}
