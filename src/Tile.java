/**
 * @author Joseph Vinson
 */

public abstract class Tile extends Entity{
    protected boolean walkable;

    protected final TileType tileType;

    public Tile(int x, int y, boolean walkable, TileType tileType) {
        super(x, y);
        this.walkable = walkable;
        this.tileType = tileType;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
