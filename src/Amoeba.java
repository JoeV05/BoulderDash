/**
 * @author Joseph Vinson
 */

public class Amoeba extends Tile {

    public static final TileType TTYPE = TileType.AMOEBA;
    public static final boolean WALK = false;

    public Amoeba(int x, int y) {
        super(x, y, WALK, TTYPE);

    }
}
