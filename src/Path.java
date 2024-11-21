/**
 * @author Joseph Vinson
 */

public class Path extends Tile{

    public static final boolean WALK = true;
    public static final TileType TTYPE = TileType.PATH;

    public Path(int x, int y) {
        super(x, y, WALK, TTYPE);
    }
}
