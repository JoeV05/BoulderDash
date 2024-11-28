import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {

    private ArrayList<Key> keys = new ArrayList<>();
    private int diamonds = 0;

    public Player(int x, int y, Image image) {
        super(x, y,image);
    }

    public void move(KeyCode key) {
        int dy = 0;
        int dx = 0;
        switch (key) {
            case UP:
            case W:
                dy = -1;
                System.out.print("Moved UP");
                break;
            case DOWN:
            case S:
                dy = 1;
                System.out.print("Moved DOWN");
                break;
            case LEFT:
            case A:
                dx = -1;
                System.out.print("Moved left");
                break;
            case RIGHT:
            case D:
                dx = 1;
                System.out.print("Moved Right");
                break;
            default:
                // all keys are sent here so we excess comparisons
                break;
        }
        Game.updateLevel(this.x + dx, this.y + dy, this);
    }
}
