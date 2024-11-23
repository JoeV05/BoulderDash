/**
 * @author Joseph Vinson
 */

public class Wall extends Tile {
    protected WallType wallType;

    public Wall(int x, int y, WallType wallType) {
        super(x, y, false, TileType.WALL);
        this.wallType = wallType;
    }
}
