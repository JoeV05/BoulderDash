/**
 * @author Joseph Vinson
 */

public class MagicWall extends Wall {
    private boolean isActive;

    public static final WallType WTYPE = WallType.MAGIC_WALL;

    public MagicWall(int x, int y) {
        super(x, y, WTYPE);
    }
}
