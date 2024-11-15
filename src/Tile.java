public abstract class Tile {
    protected final int[] coordinates;
    protected final boolean walkable;
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
