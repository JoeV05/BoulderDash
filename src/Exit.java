import javafx.scene.image.Image;

/**
 * Represents an exit to a specific level within the game.
 * @author James Harvey, Joseph Vinson
 */
public class Exit extends ActionWall {
    private static int scoreRequirement;

    /**
     * Creates an exit at the given (x, y) coordinates with the given score
     * requirement.
     * @param x The x coordinate of the exit.
     * @param y The y coordinate of the exit.
     * @param scoreReq The score requirement to use the exit.
     */
    public Exit(int x, int y, int scoreReq) {
        super(x, y, WallType.EXIT);
        scoreRequirement = scoreReq;
    }

    /**
     * Set the required score for using the exit.
     * @param scoreRequirement What the required score should be for using the
     * exit.
     */
    public static void setScoreRequirement(int scoreRequirement) {
        Exit.scoreRequirement = scoreRequirement;
    }

    /**
     * Check if a number of diamonds is enough to use the exit.
     * @param diamonds Number of diamonds to check against score requirement.
     * @return true or false.
     */
    public boolean canExit(int diamonds) {
        return diamonds >= scoreRequirement;
    }

    /**
     * Unlocks the exit.
     */
    private void unlock() {
        Image sprite = new Image("./sprites/exit_unlocked.png");
        this.setSprite(sprite);
        this.walkable = true;
    }

    /**
     * Check if the player has enough diamonds to leave the level, if so then
     * unlock the exit.
     */
    @Override
    public void tick() {
        if (this.canExit(Player.getPlayer().getDiamonds())) {
            this.unlock();
        }
    }
}
