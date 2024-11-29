import javafx.scene.image.Image;
/**
 * @author Joseph Vinson
 * Represents a generic tile in the game
 * Tiles come in different types specified by enum.
 * Each having unique properties related to behaviour
 *
 */

public abstract class Tile extends Entity{
    protected boolean walkable;

    protected final TileType tileType;

    public Tile(int x, int y, boolean walkable, TileType tileType, Image sprite) {
        super(x, y, sprite);
        this.walkable = walkable;
        this.tileType = tileType;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
