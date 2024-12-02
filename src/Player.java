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
        Path p = new Path(x, y);
        switch (key) {
            case UP:
            case W:
                setSprite(new Image("./sprites/Player_Down.png"));
                Game.replaceEntity(x, y - 1, this);
                setY(y - 1);
                Game.replaceEntity(x, y, p);
                break;
            case DOWN:
            case S:
                setSprite(new Image("./sprites/Player_Up.png"));
                Game.replaceEntity(x, y + 1, this);
                setY(y + 1);
                Game.replaceEntity(x, y, p);
                break;
            case LEFT:
            case A:
                setSprite(new Image("./sprites/Player_Left.png"));
                Game.replaceEntity(x - 1, y, this);
                setX(x - 1);
                Game.replaceEntity(x, y, p);
                break;
            case RIGHT:
            case D:
                setSprite(new Image("./sprites/Player_Right.png"));
                Game.replaceEntity(x + 1, y, this);
                setX(x + 1);
                Game.replaceEntity(x, y, p);
                break;
            default:
                // all redundant key inputs are sent here
                break;
        }
        Game.checkForChangeInView(x, y, this.getX(), this.getY());
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
