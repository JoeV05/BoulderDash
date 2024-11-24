/**
 * @author Joseph Vinson
 */

public abstract class Tile {
    protected boolean walkable;
    protected int[] coordinates;

    protected final TileType tileType;

    public Tile(int x, int y, boolean walkable, TileType tileType) {
        this.coordinates = new int[] {x, y};
        this.walkable = walkable;
        this.tileType = tileType;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
