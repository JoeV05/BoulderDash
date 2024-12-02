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
    // TODO - maybe there's already a method in Game that could simplify this?
    //  not sure, this was a quick dirty fix to try to get player moving again
    public void move(KeyCode key) {
        int x = getX();
        int y = getY();
        this.moveSwitch(key);
        Game.checkForChangeInView(x, y, this.getX(), this.getY());
    }

    private void moveSwitch(KeyCode key) {
        Path p = new Path(x, y);
        switch (key) {
            case UP:
            case W:
                if (!Game.isValidMove(x, y, Direction.UP)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Down.png"));
                Game.replaceEntity(x, y - 1, this);
                Game.replaceEntity(x, y, p);
                setY(y - 1);
                break;
            case DOWN:
            case S:
                if (!Game.isValidMove(x, y, Direction.DOWN)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Up.png"));
                Game.replaceEntity(x, y + 1, this);
                Game.replaceEntity(x, y, p);
                setY(y + 1);
                break;
            case LEFT:
            case A:
                if (!Game.isValidMove(x, y, Direction.LEFT)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Left.png"));
                Game.replaceEntity(x - 1, y, this);
                Game.replaceEntity(x, y, p);
                setX(x - 1);
                break;
            case RIGHT:
            case D:
                if (!Game.isValidMove(x, y, Direction.RIGHT)) {
                    return;
                }
                setSprite(new Image("./sprites/Player_Right.png"));
                Game.replaceEntity(x + 1, y, this);
                Game.replaceEntity(x, y, p);
                setX(x + 1);
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
