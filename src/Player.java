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
        this.moveSwitch(key);
        Game.checkForChangeInView(x, y, this.getX(), this.getY());
    }

    // TODO - javadoc method comment
    private void moveSwitch(KeyCode key) {
        Path p = new Path(x, y);
        switch (key) {
            case UP:
            case W:
                if (!Game.isValidMove(x, y, Direction.UP)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Down.png"));
                Game.updateLevel(x, y - 1, this);
                break;
            case DOWN:
            case S:
                if (!Game.isValidMove(x, y, Direction.DOWN)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Up.png"));
                Game.updateLevel(x, y + 1, this);
                break;
            case LEFT:
            case A:
                if (!Game.isValidMove(x, y, Direction.LEFT)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Left.png"));
                Game.updateLevel(x - 1, y, this);
                break;
            case RIGHT:
            case D:
                if (!Game.isValidMove(x, y, Direction.RIGHT)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Right.png"));
                Game.updateLevel(x + 1, y, this);
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
