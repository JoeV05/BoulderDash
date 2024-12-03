import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.util.ArrayList;

// TODO - javadoc class comment

public class Player extends Entity {

    private ArrayList<Key> keys = new ArrayList<>();
    private int diamonds = 0;

    private static Player thePlayer;

    private Player(int x, int y) {
        super(x, y, new Image("./sprites/Player_Down.png"));
    }

    // TODO - javadoc method comment
    public void move(KeyCode key) {
        int x = getX();
        int y = getY();
        if (x > Game.GRID_WIDTH - 1 || x < 0 || y > Game.GRID_HEIGHT - 1 || y < 0) {
            throw new LiamWetFishException("IM GOING TO HIT YOU WITH A WET FISH!");
        }
        this.moveSwitch(key);
        Game.checkForChangeInView(x, y, this.getX(), this.getY());
    }

    // TODO - javadoc method comment
    // TODO - maybe only one set of keys for moving
    private void moveSwitch(KeyCode key) {
        Path p = new Path(x, y);
        switch (key) {
            case UP:
            case W:
                setSprite(new Image("./sprites/player_down.png"));
                if (Game.isValidMove(x, y, Direction.UP)) {
                    Game.updateLevel(x, y - 1, this);
                }
                break;
            case DOWN:
            case S:
                setSprite(new Image("./sprites/player_up.png"));
                if (Game.isValidMove(x, y, Direction.DOWN)) {
                    Game.updateLevel(x, y + 1, this);
                }
                break;
            case LEFT:
            case A:
                setSprite(new Image("./sprites/player_left.png"));
                if (Game.isValidMove(x, y, Direction.LEFT)) {
                    Game.updateLevel(x - 1, y, this);
                }
                break;
            case RIGHT:
            case D:
                setSprite(new Image("./sprites/player_right.png"));
                if (Game.isValidMove(x, y, Direction.RIGHT)) {
                    Game.updateLevel(x + 1, y, this);
                }
                break;
            default:
                // all redundant key inputs are sent here
                break;
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
