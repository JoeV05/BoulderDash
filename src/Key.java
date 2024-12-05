import javafx.scene.image.Image;

/**
 * Represents a key item.
 *
 * Keys can be collected by the player and used to unlock matching coloured locked doors.
 * Each key has a specific colour that corresponds to a particular locked door.
 *
 * @author Tafara Gonese, Joseph Vinson
 * Check Section 3.1 of the Functional Specification for detailed key and locked door mechanics
 */

public class Key extends Walkable {
    /** The colour of the key which determines which locked door it can open. */
    private final Colour colour;

    // TODO replace placeholder parameter and switch statement with static method
    /**
     * Constructs a new Key with a specific colour and position.
     *
     * @param colour The colour of the key
     * @param x The x-coordinate of the key on the game map
     * @param y The y-coordinate of the key on the game map
     */
    public Key(int x, int y, Colour colour) {
        super(x, y, Key.spriteSwitch(colour));
        this.colour = colour;
    }

    // TODO - javadoc method comment
    private static Image spriteSwitch(Colour colour) {
        switch(colour) {
            case RED:
                return new Image("sprites/key_red.png");
            case YELLOW:
                return new Image("sprites/key_yellow.png");
            case GREEN:
                return new Image("sprites/key_green.png");
            case BLUE:
                return new Image("sprites/key_blue.png");
            default:
                throw new LiamWetFishException("BAD KEY COLOUR, THE FISH ARE COMING FOR YOU");
        }
    }

    /**
     * Gets the colour of the key.
     *
     * @return The colour of the key
     */
    public Colour getColour() {
        return colour;
    }

    // TODO - shouldn't the logic be if a Player object can unlock() a Door object with one of the Key objects it has,
    //  getColour should be sufficient for this in terms of the role of key in the operation (I think)
    /**
     * Checks if this key can unlock a specific locked door.
     *
     * @param lockedDoor The locked door to check against
     * @return true if the key's colour matches the door's colour, false otherwise
     */
    public boolean canUnlock(LockedDoor lockedDoor) {
        return lockedDoor.getColour() == this.colour;
    }

    // TODO - w
    /**
     * Attempts to use the key to unlock a specific locked door.
     * If the key's colour matches the door's colour, the door is unlocked.
     *
     * @param lockedDoor The locked door to attempt to unlock
     */
    public void use(LockedDoor lockedDoor) {
        if (canUnlock(lockedDoor)) {
            lockedDoor.unlock();
        }
    }
}
