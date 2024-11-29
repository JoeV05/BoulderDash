import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Player extends Entity {

    private ArrayList<Key> keys = new ArrayList<>();
    private int diamonds = 0;

    public Player(int x, int y) {
        super(x, y, new Image("./sprites/player.png"));
    }

    public int[] move(KeyCode key) {
        int dy = 0;
        int dx = 0;
        switch (key) {
            case UP:
            case W:
                dy = -1;
                break;
            case DOWN:
            case S:
                dy = 1;
                break;
            case LEFT:
            case A:
                dx = -1;
                break;
            case RIGHT:
            case D:
                dx = 1;
                break;
            default:
                break;
        }
        return new int[]{dy, dx};
    }
}
