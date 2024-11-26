/**
 * Represents a key item.
 *
 * Keys can be collected by the player and used to unlock matching colored locked doors.
 * Each key has a specific color that corresponds to a particular locked door.
 *
 * @author Tafara Gonese, Joseph Vinson
 * Check Section 3.1 of the Functional Specification for detailed key and locked door mechanics
 */

public class Key extends Tile {
    /** The color of the key which determines which locked door it can open. */
    private final Colour colour;

    /**
     * Constructs a new Key with a specific color and position.
     *
     * @param colour The color of the key
     * @param x The x-coordinate of the key on the game map
     * @param y The y-coordinate of the key on the game map
     */
    public Key(Colour colour, int x, int y) {
        super(x, y, false, TileType.KEY);
        this.colour = colour;
    }

    /**
     * Gets the color of the key.
     *
     * @return The color of the key
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks if this key can unlock a specific locked door.
     *
     * @param lockedDoor The locked door to check against
     * @return true if the key's color matches the door's color, false otherwise
     */
    public boolean canUnlock(LockedDoor lockedDoor) {
        return lockedDoor.getColour() == this.colour;
    }

    /**
     * Attempts to use the key to unlock a specific locked door.
     * If the key's color matches the door's color, the door is unlocked.
     *
     * @param lockedDoor The locked door to attempt to unlock
     */
    public void use(LockedDoor lockedDoor) {
        if (canUnlock(lockedDoor)) {
            lockedDoor.unlock();
        }
    }
}
