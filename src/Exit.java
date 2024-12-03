import javafx.scene.image.Image;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Exit extends ActionWall {
    private static int scoreRequirement;

    public Exit(int x, int y, int scoreReq) {
        super(x, y, WallType.EXIT);
        scoreRequirement = scoreReq;
    }

    // TODO - javadoc method comment
    public boolean canExit(int diamonds) {
        return diamonds >= scoreRequirement;
    }

    // TODO - maybe a change in sprite?
    // TODO - javadoc method comment
    private void unlock() {
        this.walkable = true;
    }

    // TODO - javadoc method comment
    public void updateExit(int diamonds) {
        if (this.canExit(diamonds)) {
            this.unlock();
        }
    }

    //TODO: add code that changes the sprite when the player has fulfilled the conditions to exit
    @Override
    public void tick(){
        if (this.walkable) {
            Image sprite = new Image("./sprites/exit_unlocked.png");
            this.setSprite(sprite);
        }
    }
}
