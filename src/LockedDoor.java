import javafx.scene.image.Image;

/**
 * Represents a locked door tile. Locked doors are a type of wall that can be
 * opened by a key of a matching colour. Initially acts as a wall, but can be
 * unlocked to become a walkable path.
 * @author Joseph Vinson, Tafara Gonese
 */

public class LockedDoor extends Wall {
    //The colour of the locked door which determines which key can open it.
    private final Colour colour;

    /**
     * Constructs a new Locked Door with a specific position and colour.
     * @param x The x-coordinate of the locked door on the game map
     * @param y The y-coordinate of the locked door on the game map
     * @param colour The colour of the locked door
     */
    public LockedDoor(int x, int y, Colour colour) {
        super(x, y, WallType.LOCKED_DOOR);
        this.colour = colour;
        this.setSprite(this.spriteSwitch(this.colour));
    }

    /**
     * Choose the correct sprite for a door of a given colour.
     * @param colour The colour of the door to get the sprite of.
     * @return Image of either red, yellow, green or blue door.
     */
    private Image spriteSwitch(Colour colour) {
        switch (colour) {
            case RED:
                return new Image("sprites/door_red.png");
            case YELLOW:
                return new Image("sprites/door_yellow.png");
            case GREEN:
                return new Image("sprites/door_green.png");
            case BLUE:
                return new Image("sprites/door_blue.png");
            default:
                throw new IllegalArgumentException("Illegal colour " + colour);
        }
    }

    /**
     * Unlocks the door, making it walkable.
     */
    public void unlock() {
        this.walkable = true;
    }

    /**
     * Gets the colour of the locked door.
     * @return The colour of the door.
     */
    public Colour getColour() {
        return this.colour;
    }

    /**
     * Checks if the door is currently locked.
     * @return true if the door is still locked, false if it has been unlocked.
     */
    public boolean isLocked() {
        return this.walkable;
    }
}
