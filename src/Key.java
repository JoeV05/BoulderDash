import javafx.scene.image.Image;

/**
 * Represents a key within the game. Keys can be collected by the player and
 * used to unlock matching coloured locked doors. Each key has a specific
 * colour that corresponds to a particular locked door.
 * @author Tafara Gonese, Joseph Vinson
 */
public class Key extends Walkable {
    //The colour of the key which determines which locked door it can open.
    private final Colour colour;

    /**
     * Constructs a new Key with a specific colour and position.
     * @param colour The colour of the key
     * @param x The x-coordinate of the key on the game map
     * @param y The y-coordinate of the key on the game map
     */
    public Key(int x, int y, Colour colour) {
        super(x, y, spriteSwitch(colour));
        this.colour = colour;
    }

    /**
     * Choose the correct sprite for a key of a given colour.
     * @param colour The colour of the key to get the sprite of.
     * @return RED, YELLOW, GREEN or BLUE
     */
    private static Image spriteSwitch(Colour colour) {
        switch (colour) {
            case RED:
                return new Image("sprites/key_red.png");
            case YELLOW:
                return new Image("sprites/key_yellow.png");
            case GREEN:
                return new Image("sprites/key_green.png");
            case BLUE:
                return new Image("sprites/key_blue.png");
            default:
                throw new IllegalArgumentException("Illegal colour " + colour);
        }
    }

    /**
     * Gets the colour of the key.
     * @return The colour of the key
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Checks if this key can unlock a specific locked door.
     * @param lockedDoor The locked door to check against.
     * @return true if key colour matches the door colour, otherwise false.
     */
    public boolean canUnlock(LockedDoor lockedDoor) {
        return lockedDoor.getColour() == this.colour;
    }
}
