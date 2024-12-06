import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.util.ArrayList;

// TODO - javadoc class comment

/**
 * @author James Harvey, Joseph Vinson, Joe Devlin
 */
public class Player extends Entity {

    // TODO - make keys collectable
    private ArrayList<Key> keys = new ArrayList<>();
    // TODO - make diamonds collectable
    // TODO - make collecting diamonds increase the score
    private int diamonds = 0;

    private static Player thePlayer;

    private Player(int x, int y) {
        super(x, y, new Image("./sprites/Player_Down.png"));
    }

    // TODO - javadoc method comment
    public ArrayList<Key> getKeys() {
        return this.keys;
    }

    private void addKey(Key key) {
        this.keys.add(key);
    }

    // TODO - javadoc method comment
    // TODO - player can push boulders
    // TODO - player can open doors if they have the right key
    // TODO - player can exit the level if they have enough diamonds
    public void move(KeyCode key) {
        if (this.x > Game.GRID_WIDTH - 1 || this.x < 0 || this.y > Game.GRID_HEIGHT - 1 || this.y < 0) {
            throw new LiamWetFishException("PLAYER POSITION INVALID!!! WHAT THE FISH DID YOU DO TO GET HERE!!!");
        }
        Direction moveDir = this.dirSwitch(key);
        if (moveDir == Direction.NONE) {
            return;
        }
        this.spriteSwitch(moveDir);
        int[] newCoordinates = this.moveSwitch(key);
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
        }
    }

    private void burnKey(Colour colour) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).getColour() == colour) {
                this.keys.remove(i);
                return;
            }
        }
    }

    private boolean canUnlock(LockedDoor door) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keys.get(i).canUnlock(door)) {
                return true;
            }
        }
        return false;
    }

    // TODO - javadoc method comment
    private boolean validateMove(Direction moveDir) {
        return Game.getGame().isValidMove(this.x, this.y, moveDir);
    }

    // TODO - javadoc method comment
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

    // TODO - javadoc method comment
    // TODO - maybe only one set of keys for moving
    private int[] moveSwitch(KeyCode key) {
        switch (key) {
            case UP:
            case W:
                return new int[]{this.x, this.y - 1};
            case DOWN:
            case S:
                return new int[] {this.x, this.y + 1};
            case LEFT:
            case A:
                return new int[] {this.x - 1, this.y};
            case RIGHT:
            case D:
                return new int[] {this.x + 1, this.y};
            default:
                throw new LiamWetFishException("HOW DID YOU GET HERE!!! WHAT THE FISH!!!");
        }
    }

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
                throw new LiamWetFishException("HOW THE FISH DID YOU MESS THIS UP SO BADLY!!!");
        }
    }

    // TODO - javadoc method comment
    public static Player getPlayer() {
        return thePlayer;
    }

    // TODO - javadoc method comment
    public static Player getPlayer(int x, int y) {
        thePlayer = new Player(x, y);
        return thePlayer;
    }

    // TODO - obliterate magic numbers
    /**
     * Checks if the
     * @param oldX x coordinate before player moves
     * @param oldY y coordinate before player moves
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
        }
    }
}
