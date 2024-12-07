import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * Represents the player. Keeps track of keys the player has and how many
 * diamonds the player has collected. Handles parts of player movement.
 * @author James Harvey, Joseph Vinson, Joe Devlin, Tafara Gonese
 * @version 1.8
 */
public class Player extends Entity {
    private static Player thePlayer;
    private ArrayList<Key> keys = new ArrayList<>();
    private int diamonds = 0;

    /**
     * Creates a Player object with the coordinates (x, y).
     * @param x The x coordinate of the player.
     * @param y The y coordinate of the player.
     */
    private Player(int x, int y) {
        super(x, y, new Image("./sprites/Player_Down.png"));
    }

    /**
     * Retrieve the keys the player has stored.
     * @return An ArrayList containing Key objects.
     */
    public ArrayList<Key> getKeys() {
        return this.keys;
    }

    /**
     * Adds a key to the keys the player has stored.
     * @param key The key to add to the players inventory.
     */
    private void addKey(Key key) {
        this.keys.add(key);
    }

    /**
     * Check if the player is currently at an invalid position.
     * @return true or false.
     */
    private boolean invalidPosition() {
        return this.x > Game.MAX_WIDTH_INDEX || this.x < 0
                || this.y > Game.MAX_HEIGHT_INDEX || this.y < 0;
    }

    // TODO - javadoc method comment
    // TODO - player can push boulders
    // TODO - player can exit the level if they have enough diamonds
    // TODO - better separation of responsibilities
    /**
     * Moves the player in the specified direction. Checks if the player is in
     * a valid position, then determines the direction to move the player in.
     * Sets the player sprite to match the direction the player is trying
     * to move in regardless of whether the move is considered valid, then
     * checks if the move is valid and if so performs the move, adding any
     * keys or diamonds to the players inventory as necessary.
     * @param key The key that was pressed.
     */
    public void move(KeyCode key) {
        if (this.invalidPosition()) {
            throw new IllegalStateException("Player outside boundary of map at"
                    + "\nx = " + this.x + "\ny = " + this.y);
        }
        Direction moveDir = this.dirSwitch(key);
        if (moveDir == Direction.NONE) {
            return;
        }
        this.spriteSwitch(moveDir);
        int[] newCoordinates = this.moveSwitch(moveDir);
        int oX = this.x;
        int oY = this.y;
        int nX = newCoordinates[0];
        int nY = newCoordinates[1];
        if (!this.validateMove(moveDir)) {
            return;
        }
        Entity target = Game.getGame().getEntity(nX, nY);
        if (target instanceof Walkable || target instanceof Diamond) {
            Game.getGame().updateLevel(nX, nY, this);
            this.checkForChangeInView(oX, oY);
            if (target instanceof Key) {
                this.addKey((Key) target);
            } else if (target instanceof Diamond) {
                this.diamonds += 1;
                System.out.println("You collected diamond " + this.diamonds);
                Game.getGame().removeFallingEntity((Diamond) target);
            }
        } else if (target instanceof LockedDoor) {
            LockedDoor door = (LockedDoor) target;
            if (!door.isLocked()) {
                Game.getGame().updateLevel(nX, nY, this);
                this.checkForChangeInView(oX, oY);
            }
            if (this.canUnlock(door)) {
                door.unlock();
                this.burnKey(door.getColour());
            }
        } else if (target instanceof Exit) {
            Exit exit = (Exit) target;
            if (exit.canExit(this.diamonds)) {
                Game.getGame().updateLevel(nX, nY, this);
                this.checkForChangeInView(oX, oY);
            }
        }
    }

    /**
     * Remove a key of a given colour from the players keys. Doesn't remove
     * a specified key, but a key with a specified colour, as there is
     * effectively no difference between keys of the same colour after being
     * picked up.
     * @param colour Colour of the key to remove.
     */
    private void burnKey(Colour colour) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).getColour() == colour) {
                this.keys.remove(i);
                return;
            }
        }
    }

    /**
     * Checks if the player can unlock a given door.
     * @param door The door to check if the player can unlock.
     * @return true or false.
     */
    private boolean canUnlock(LockedDoor door) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).canUnlock(door)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the move the player is trying to make is valid.
     * @param moveDir Direction the player is trying to move in.
     * @return true or false.
     */
    private boolean validateMove(Direction moveDir) {
        return Game.getGame().isValidMove(this.x, this.y, moveDir);
    }

    /**
     * Get the direction the player is trying to move in from the key
     * the player has pressed.
     * @param key The key the player pressed.
     * @return UP, DOWN, LEFT, RIGHT or NONE.
     */
    private Direction dirSwitch(KeyCode key) {
        switch (key) {
            case KeyCode.UP:
            case KeyCode.W:
                return Direction.UP;
            case KeyCode.DOWN:
            case KeyCode.S:
                return Direction.DOWN;
            case KeyCode.LEFT:
            case KeyCode.A:
                return Direction.LEFT;
            case KeyCode.RIGHT:
            case KeyCode.D:
                return Direction.RIGHT;
            default:
                return Direction.NONE;
        }
    }

    /**
     * Get the position the player will be in after moving in a given
     * direction.
     * @param dir Direction the player is trying to move in.
     * @return An array [x, y] of integers where x is between 0 and the width
     * of the map - 1 inclusive, and y is between 0 and the height of the map
     * inclusive.
     */
    private int[] moveSwitch(Direction dir) {
        switch (dir) {
            case Direction.UP:
                return new int[]{this.x, this.y - 1};
            case Direction.DOWN:
                return new int[] {this.x, this.y + 1};
            case Direction.LEFT:
                return new int[] {this.x - 1, this.y};
            case Direction.RIGHT:
                return new int[] {this.x + 1, this.y};
            default:
                throw new IllegalStateException("Invalid direction: " + dir);
        }
    }

    /**
     * Set the player sprite based off the direction the player is moving in.
     * @param dir THe direction the player is moving in.
     */
    private void spriteSwitch(Direction dir) {
        switch (dir) {
            case Direction.UP:
                setSprite(new Image("./sprites/player_up.png"));
                break;
            case Direction.DOWN:
                setSprite(new Image("./sprites/player_down.png"));
                break;
            case Direction.LEFT:
                setSprite(new Image("./sprites/player_left.png"));
                break;
            case Direction.RIGHT:
                setSprite(new Image("./sprites/player_right.png"));
                break;
            default:
                throw new IllegalStateException("Invalid direction: " + dir);
        }
    }

    /**
     * Retrieve the player instance.
     * @return The instance of player.
     */
    public static Player getPlayer() {
        return thePlayer;
    }

    /**
     * Create a Player instance at the coordinates (x, y) and return it.
     * @param x The x coordinate for the player to be at.
     * @param y The y coordinate for the player to be at.
     * @return The instance of player.
     */
    public static Player getPlayer(int x, int y) {
        thePlayer = new Player(x, y);
        return thePlayer;
    }

    public int getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }


    // TODO - replace magic numbers with constants
    /**
     * Checks if the sector of the map displayed on screen needs to
     * be changed.
     * @param oldX The x coordinate before player moves.
     * @param oldY The y coordinate before player moves.
     */
    public void checkForChangeInView(int oldX, int oldY) {
        switch (Main.VIEW.getView()) {
            case 1:
                if (oldX == 24 && this.x == 25) {
                    Main.VIEW.changeViewMode(2);
                } else if (oldY == 13 && this.y == 14) {
                    Main.VIEW.changeViewMode(3);
                }
                break;
            case 2:
                if (oldX == 17 && this.x == 16) {
                    Main.VIEW.changeViewMode(1);
                } else if (oldY == 13 && this.y == 14) {
                    Main.VIEW.changeViewMode(4);
                }
                break;
            case 3:
                if (oldX == 24 && this.x == 25) {
                    Main.VIEW.changeViewMode(4);
                } else if (oldY == 8 && this.y == 7) {
                    Main.VIEW.changeViewMode(1);
                }
                break;
            case 4:
                if (oldX == 17 && this.x == 16) {
                    Main.VIEW.changeViewMode(3);
                } else if (oldY == 8 && this.y == 7) {
                    Main.VIEW.changeViewMode(2);
                }
                break;
            default:
                break;
        }
    }

    //TODO: Javadoc and removal of magic numbers
    public void manualSwitchView(int x, int y) {
        if (x < 25 && y < 14) {
            Main.VIEW.changeViewMode(1);
        } else if (x > 25 && y < 14) {
            Main.VIEW.changeViewMode(2);
        } else if (x < 25 && y > 14) {
            Main.VIEW.changeViewMode(3);
        } else if (x > 25 && y > 14) {
            Main.VIEW.changeViewMode(4);
        }
    }
}
