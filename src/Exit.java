import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class Exit extends Wall {
    private static int scoreRequirement;

    public Exit(int x, int y, int scoreReq) {
        super(x ,y, WallType.TITANIUM_WALL, new Image("./sprites/exit.png"));
        scoreRequirement = scoreReq;
    }

    public boolean canExit(int diamonds) {
        return diamonds >= scoreRequirement;
    }

    private void unlock() {
        this.walkable = true;
    }

    public void updateExit(int diamonds) {
        if (this.canExit(diamonds)) {
            this.unlock();
        }
    }
}
