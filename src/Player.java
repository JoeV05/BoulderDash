import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.util.ArrayList;

// TODO - javadoc class comment

/**
 * @author James Harvey, Joseph Vinson, Joe Devlin
 */
public class Player extends Entity {

    private ArrayList<Key> keys = new ArrayList<>();
    private int diamonds = 0;

    private static Player thePlayer;

    private Player(int x, int y) {
        super(x, y, new Image("./sprites/Player_Down.png"));
    }

    // TODO - javadoc method comment
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
        int nX = newCoordinates[0];
        int nY = newCoordinates[1];
        this.validateMove(nX, nY, moveDir);
    }

    // TODO - javadoc method comment
    private void validateMove(int nX, int nY, Direction moveDir) {
        int oldX = this.x;
        int oldY = this.y;
        if (Game.isValidMove(this.x, this.y, moveDir)) {
            Game.updateLevel(nX, nY, this);
        }
        Game.checkForChangeInView(oldX, oldY, this.x, this.y);
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
                System.out.println(dir);
                throw new LiamWetFishException("HOW THE FISH DID YOU MESS THIS UP SO BADLY!!!");
        }
    }

    // TODO - javadoc method comment
    public static Player getPlayer() {
        if (thePlayer != null) {
            return thePlayer;
        } else {
            return null;
        }
    }

    // TODO - javadoc method comment
    public static Player getPlayer(int x, int y) {
        thePlayer = new Player(x, y);
        return thePlayer;
    }
}
