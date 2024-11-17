public class MagicWall extends Wall {
    private boolean isActive;

    public static final WallType wType = WallType.MAGIC_WALL;

    public MagicWall(int x, int y) {
        super(x, y, wType);
    }
}
