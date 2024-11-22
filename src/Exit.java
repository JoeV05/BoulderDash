/**
 * @author Joseph Vinson
 */

public class Exit extends Wall {
    private static int scoreRequirement;

    public Exit(int x, int y, int scoreReq) {
        super(x ,y, WallType.TITANIUM_WALL);
        scoreRequirement = scoreReq;
    }

    public boolean canExit() {
        return true;
    }

    public void unlock() {
        this.walkable = true;
    }
}
