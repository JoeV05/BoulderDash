import javafx.scene.input.KeyCode;

public class Player extends Entity {

    // maybe we use should Point
    public Player(int x, int y) {
        super(x, y);
    }

    public void move(KeyCode key) {
        switch (key) {
            case UP:
            case W:
                this.y -= 1;
                System.out.print("Move UP");
                break;
            case DOWN:
            case S:
                this.y += 1;
                System.out.print("Move DOWN");
                break;
            case LEFT:
            case A:
                this.x -= 1;
                System.out.print("Move left");
                break;
            case RIGHT:
            case D:
                this.x += 1;
                System.out.print("Move Right");
                break;
            default:
                // all keys are sent here so we excess comparisons
                break;
        }
    }
}
