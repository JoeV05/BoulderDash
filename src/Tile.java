import javafx.scene.image.Image;

/**
 * Represents a generic tile in the game. Tiles come in different types,
 * each having unique properties related to behaviour.
 * @author Joseph Vinson
 * @version 1.2
 */
public abstract class Tile extends Entity {
    protected boolean walkable; //Indicates whether the tile can be walked on
    protected final TileType tileType; //Indicates what type of tile it is

    /**
     * Create a tile at the given (x, y) coordinates, of the given tile type
     * and with a given sprite
     * @param x The x coordinate for the tile.
     * @param y The y coordinate for the tile.
     * @param walkable Whether the tile can be walked on.
     * @param type The type of the tile.
     * @param sprite The sprite that should be used for the tile.
     */
    public Tile(int x, int y, boolean walkable, TileType type, Image sprite) {
        super(x, y, sprite);
        this.walkable = walkable;
        this.tileType = type;
    }

    /**
     * Check if this tile is walkable.
     * @return true or false.
     */
    public boolean isWalkable() {
        return walkable;
    }
}
