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

    public static void setScoreRequirement(int scoreRequirement) {
        Exit.scoreRequirement = scoreRequirement;
    }

    // TODO - javadoc method comment
    public boolean canExit(int diamonds) {
        return diamonds >= scoreRequirement;
    }

    // TODO - javadoc method comment
    private void unlock() {
        Image sprite = new Image("./sprites/exit_unlocked.png");
        this.setSprite(sprite);
        this.walkable = true;
    }

    // TODO - javadoc method comment
    public void updateExit(int diamonds) {
        if (this.canExit(diamonds)) {
            this.unlock();
        }
    }

    @Override
    public void tick(){
        updateExit(Player.getPlayer().getDiamonds());
    }
}
