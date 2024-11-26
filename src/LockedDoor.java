/**
 * Represents a locked door tile.
 *
 * Locked doors are a type of wall that can be opened by a key of a matching color.
 * Initially acts as a wall, but can be unlocked to become a walkable path.
 *
 * @author Joseph Vinson, Tafara Gonese
 * Check Section 3.1 of the Functional Specification for detailed locked door behavior and key interactions
 *
 */

public class LockedDoor extends Wall {
    /** The color of the locked door which determines which key can open it. */
    private final Colour colour;

    /**
     * Constructs a new Locked Door with a specific position and color.
     *
     * @param x The x-coordinate of the locked door on the game map
     * @param y The y-coordinate of the locked door on the game map
     * @param colour The color of the locked door
     */
    public LockedDoor(int x, int y, Colour colour) {
        super(x, y, WallType.LOCKED_DOOR);
        this.colour = colour;
    }

    /**
     * Checks if the given key can unlock this door.
     *
     * @param key The key attempting to unlock the door
     * @return true if the key's color matches the door's color, false otherwise
     */
    public boolean canUnlock(Key key) {
        return this.colour == key.getColour();
    }

    /**
     * Unlocks the door, making it walkable.
     * After calling this method, the door becomes a path tile.
     */
    public void unlock() {
        this.walkable = true;
    }

    /**
     * Gets the color of the locked door.
     *
     * @return The color of the door
     */
    public Colour getColour() {
        return this.colour;
    }

    /**
     * Checks if the door is currently locked.
     *
     * @return true if the door is still locked, false if it has been unlocked
     */
    public boolean isLocked() {
        return this.walkable;
    }
}
