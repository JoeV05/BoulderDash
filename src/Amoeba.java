import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * Represents an Amoeba within the game. Amoeba are unique organisms that
 * spread across the game board and are able to transform into boulders or
 * diamonds when certain conditions are met. Amoeba are harmless to the player
 * until they transform, adding a strategic element to the game.
 * @author Joseph Vinson, Tafara Gonese, Jamie Crockett
 * @version 1.5
 */

public class Amoeba extends Tile {
    /**
     * Constructs a new Amoeba tile.
     * @param x The x-coordinate of the amoeba tile.
     * @param y The y-coordinate of the amoeba tile.
     */
    public Amoeba(int x, int y) {
        super(x, y, false, TileType.AMOEBA, new Image("sprites/amoeba.png"));
    }

    /**
     * Check if this amoeba is able to grow at all.
     * @return true or false.
     */
    public boolean canGrow() {
        return !growthDirections().isEmpty();
    }

    /**
     * Get the directions the amoeba is able to grow in.
     * @return ArrayList of Direction.
     */
    public ArrayList<Direction> growthDirections() {
        ArrayList<Direction> dirs = new ArrayList<>();
        if (canGrow(this.x, this.y - 1)) {
            dirs.add(Direction.UP);
        }
        if (canGrow(this.x, this.y + 1)) {
            dirs.add(Direction.DOWN);
        }
        if (canGrow(this.x - 1, this.y)) {
            dirs.add(Direction.LEFT);
        }
        if (canGrow(this.x + 1, this.y)) {
            dirs.add(Direction.RIGHT);
        }
        return dirs;
    }

    /**
     * Grows a new amoeba in a given direction from this amoeba.
     * @param dir Direction which the new amoeba should grow from.
     * @return Amoeba object.
     */
    public Amoeba grow(Direction dir) {
        Amoeba a;
        switch (dir) {
            case Direction.UP:
                a = new Amoeba(this.x, this.y - 1);
                Game.getGame().replaceEntity(this.x, this.y - 1, a);
                return a;
            case Direction.DOWN:
                a = new Amoeba(this.x, this.y + 1);
                Game.getGame().replaceEntity(this.x, this.y + 1, a);
                return a;
            case Direction.LEFT:
                a = new Amoeba(this.x - 1, this.y);
                Game.getGame().replaceEntity(this.x - 1, this.y, a);
                return a;
            case Direction.RIGHT:
                a = new Amoeba(this.x + 1, this.y);
                Game.getGame().replaceEntity(this.x + 1, this.y, a);
                return a;
            default:
                throw new IllegalArgumentException("Illegal direction " + dir);
        }
    }

    /**
     * Checks if an amoeba is able to grow in a certain direction. Also handles
     * killing any enemies that are adjacent to the amoeba.
     * @param nX The x coordinate to check.
     * @param nY The y coordinate to check.
     * @return true or false.
     */
    private boolean canGrow(int nX, int nY) {
        if (nX > Game.MAX_WIDTH_INDEX || nX < 0
                || nY > Game.MAX_HEIGHT_INDEX || nY < 0) {
            return false;
        }
        Entity e = Game.getGame().getEntity(nX, nY);
        if (e instanceof Enemy) {
            Game.getGame().replaceEntity(nX, nY, new Path(nX, nY));
            return true;
        }
        return e instanceof Path || e instanceof Dirt;
    }
}
