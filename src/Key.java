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

public class Key extends Tile {
    /** The colour of the key which determines which locked door it can open. */
    private final Colour colour;

    /**
     * Constructs a new Key with a specific colour and position.
     *
     * @param colour The colour of the key
     * @param x The x-coordinate of the key on the game map
     * @param y The y-coordinate of the key on the game map
     */
    public Key(int x, int y, Colour colour) {
        super(x, y, false, TileType.KEY, Key.getKeySprite(colour));
        this.colour = colour;
    }

    /**
     * Gets the colour of the key.
     *
     * @return The colour of the key
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks if this key can unlock a specific locked door.
     *
     * @param lockedDoor The locked door to check against
     * @return true if the key's colour matches the door's colour, false otherwise
     */
    public boolean canUnlock(LockedDoor lockedDoor) {
        return lockedDoor.getColour() == this.colour;
    }

    /**
     * Attempts to use the key to unlock a specific locked door.
     * If the key's colour matches the door's colour, the door is unlocked.
     *
     * @param lockedDoor The locked door to attempt to unlock
     */
    public void use(LockedDoor lockedDoor) {
        // TODO - somehow burn the key from the players inventory - should player use singleton design pattern?
        if (canUnlock(lockedDoor)) {
            lockedDoor.unlock();
        }
    }

    public static Image getKeySprite(Colour colour) {
        Image sprite;

        switch (colour) {
            case Colour.RED:
                sprite = new Image("./sprites/Red_Key.png");
                break;
            case Colour.GREEN:
                sprite = new Image("./sprites/Green_Key.png");
                break;
            case Colour.YELLOW:
                sprite = new Image("./sprites/Yellow_Key.png");
                break;
            case Colour.BLUE:
                sprite = new Image("./sprites/Blue_Key.png");
                break;
            default:
                sprite = null;
                break;
        }

        return sprite;
    }
}
