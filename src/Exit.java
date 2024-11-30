import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

// TODO - FUCKING FUCK FUCK FUCK I HATE JAVAFX
// TODO - Set the sprite to exit sprite

public class Exit extends Wall {
    private static int scoreRequirement;

    public Exit(int x, int y, int scoreReq) {
        super(x ,y, WallType.TITANIUM_WALL);
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
