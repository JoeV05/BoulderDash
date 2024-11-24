/**
 * Represents an Amoeba
 * @author Joseph Vinson
 */

public class Amoeba extends Tile {
    private int size;

    public final int MAXIMUM_SIZE;

    public Amoeba(int x, int y, int maxSize) {
        super(x, y, false, TileType.AMOEBA);
        MAXIMUM_SIZE = maxSize;
    }

    public void grow() {
        this.size += 1;
    }

    public boolean canGrow() {
        return (this.size < MAXIMUM_SIZE);
    }
}
