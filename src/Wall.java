/**
 * @author Joseph Vinson
 */

public class Wall extends Tile {
    protected WallType wallType;

    public static final TileType TTYPE = TileType.WALL;
    public static final boolean walk = false;

    public Wall(int x, int y, WallType wallType) {
        super(x, y, walk, TTYPE);
        this.wallType = wallType;
    }
}
