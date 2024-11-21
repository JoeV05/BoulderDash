/**
 * @author Joseph Vinson
 */

public class Amoeba extends Tile {
    private int size;

    public static final TileType TTYPE = TileType.AMOEBA;
    public static final boolean WALK = false;
    public final int MAXIMUM_SIZE;

    public Amoeba(int x, int y, int maxSize) {
        super(x, y, WALK, TTYPE);
        MAXIMUM_SIZE = maxSize;
    }
}
