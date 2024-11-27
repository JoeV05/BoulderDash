/**
 * @author Joseph Vinson
 * Represents a generic tile in the game
 * Tiles come in different types specified by enum.
 * Each having unique properties related to behaviour
 *
 */

public abstract class Tile extends Entity{
    protected boolean walkable; //Indicates whether the tile can be walked on

    protected final TileType tileType; //Indicates what type of tile it is

    public Tile(int x, int y, boolean walkable, TileType tileType) {
        super(x, y); //calls the Entity constructor to set the tiles coordinates
        this.walkable = walkable;
        this.tileType = tileType;
    }

    public boolean isWalkable() {
        return walkable; //Returns true if the tile is walkable, returns false if not
    }
}
