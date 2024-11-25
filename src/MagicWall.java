/**
 * @author Joseph Vinson
 */

public class MagicWall extends Wall {
    private boolean isActive;

    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL);
    }
}
