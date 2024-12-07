import javafx.scene.image.Image;

/**
 * Represents a ruined door tile in the game.
 * A ruined door is created when a locked door is forced open.
 * It is walkable and has a sprite based on its color.
 *
 * @author Tafara Gonese
 */
public class RuinedDoor extends Walkable {
    /**
     * Constructs a new RuinedDoor at the specified coordinates.
     * @param x The x-coordinate of the ruined door on the game map.
     * @param y The y-coordinate of the ruined door on the game map.
     * @param colour The colour of the ruined door, used to select the sprite.
     */
    public RuinedDoor(int x, int y, Colour colour) {
        super(x, y, spriteSwitch(colour)); // Set the sprite using the colour
    }

    /**
     * Determines the sprite for the ruined door based on its colour.
     * @param colour The colour of the ruined door.
     * @return The sprite image for the specified colour.
     */
    private static Image spriteSwitch(Colour colour) {
        switch (colour) {
            case RED:
                return new Image("sprites/door_red_ruined.png");
            case GREEN:
                return new Image("sprites/door_green_ruined.png");
            case BLUE:
                return new Image("sprites/door_blue_ruined.png");
            case YELLOW:
                return new Image("sprites/door_yellow_ruined.png");
            default:
                throw new IllegalArgumentException("Invalid colour " + colour);
        }
    }
}
