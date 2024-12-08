import java.util.ArrayList;
import javafx.scene.image.Image;

// TODO - looking at this code makes me want to cry, needs fixing

/**
 * Represents an Amoeba
 *
 * Amoeba are unique organisms that spread across the game board and have specific transformation rules.
 * According to the game specification, amoebae:
 * - Spread periodically to neighboring empty path tiles or dirt tiles
 * - Can kill enemies touching them in cardinal directions
 * - Transform based on their growth:
 * 1. If growth is blocked, they turn into diamonds
 * 2. If they reach a predefined size, they turn into boulders
 * Amoeba are harmless until they spread or transform, adding a strategic element to the game.
 *
 * @author Joseph Vinson, Tafara Gonese
 * Check Section 3.8 of the Functional Specification for detailed Amoeba behavior
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

    public boolean canGrow() {
        return !growthDirections().isEmpty();
    }

    public ArrayList<Direction> growthDirections() {
        ArrayList<Direction> dirs = new ArrayList<>();
        if (canGrowUp()) {
            dirs.add(Direction.UP);
        }
        if (canGrowDown()) {
            dirs.add(Direction.DOWN);
        }
        if (canGrowLeft()) {
            dirs.add(Direction.LEFT);
        }
        if (canGrowRight()) {
            dirs.add(Direction.RIGHT);
        }
        return dirs;
    }

    public Amoeba grow(Direction dir) {
        Amoeba a;
        switch (dir) {
            case Direction.UP:
                a = new Amoeba(this.x, this.y - 1);
                Game.getGame().replaceEntity(this.x, this.y - 1, a);
                return a;
                //break;
            case Direction.DOWN:
                a = new Amoeba(this.x, this.y + 1);
                Game.getGame().replaceEntity(this.x, this.y + 1, a);
                return a;
                //break;
            case Direction.LEFT:
                a = new Amoeba(this.x - 1, this.y);
                Game.getGame().replaceEntity(this.x - 1, this.y, a);
                return a;
                //break;
            case Direction.RIGHT:
                a = new Amoeba(this.x + 1, this.y);
                Game.getGame().replaceEntity(this.x + 1, this.y, a);
                return a;
            default:
                throw new IllegalArgumentException("Illegal direction " + dir);
        }
    }

    private boolean canGrowUp() {
        if (this.y == 0) {
            return false;
        }
        Entity e = Game.getGame().getEntity(this.x, this.y - 1);
        return e instanceof Path || e instanceof Dirt;
    }

    private boolean canGrowDown() {
        if (this.y == Game.MAX_HEIGHT_INDEX) {
            return false;
        }
        Entity e = Game.getGame().getEntity(this.x, this.y + 1);
        return e instanceof Path || e instanceof Dirt;
    }

    private boolean canGrowLeft() {
        if (this.x == 0) {
            return false;
        }
        Entity e = Game.getGame().getEntity(this.x - 1, this.y);
        return e instanceof Path || e instanceof Dirt;
    }

    private boolean canGrowRight() {
        if (this.x == Game.MAX_WIDTH_INDEX) {
            return false;
        }
        Entity e = Game.getGame().getEntity(this.x + 1, this.y);
        return e instanceof Path || e instanceof Dirt;
    }
}
