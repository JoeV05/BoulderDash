import javafx.scene.image.Image;
import java.util.Random;

/**
 * Represents an object within the game that is able to fall. More specifically
 * the boulders and diamonds that must be capable of moving downwards each tick
 * if the tile beneath them is either a path tile, in which case it simply
 * inhabits the tile, or a magic wall, in which case it must be transformed
 * by the magic wall.
 * @author Joseph Vinson
 * @version 1.3
 */
public abstract class FallingEntity extends Entity {
    public static final int[] ROLLING_CHOICES = {-1, 1};
    private boolean falling;

    /**
     * Creates a new falling entity with specified x and y coordinates, as
     * well as a sprite, and assumes that the falling entity is not falling
     * when it is created.
     * @param x x coordinate of the falling entity
     * @param y y coordinate of the falling entity
     * @param sprite The sprite of the falling entity
     */
    public FallingEntity(int x, int y, Image sprite) {
        super(x, y, sprite);

        this.falling = false;
    }

    /**
     * Checks whether the falling entity needs to roll, fall or be transformed,
     * then calls the appropriate helper function for the required action.
     * @throws IllegalStateException Exception thrown if the falling entity has
     * fallen past the border of the map.
     */
    public void tick() {
        if (this.y > Game.GRID_HEIGHT - 2) {
            throw new IllegalStateException(this + " out of bounds at"
                    + "\nx = " + this.x
                    + "\ny = " + this.y
            );
        }
        if (this.y == Game.MAX_HEIGHT_INDEX) {
            return;
        }
        Entity below = Game.getGame().getEntity(this.x, this.y + 1);
        if (Game.isRound(below)) {
            this.roll();
            return;
        } else if (below instanceof Path) {
            Game.getGame().updateLevel(x, y + 1, this);
            this.falling = true;
            return;
        } else if (below instanceof MagicWall) {
            ((MagicWall) below).transform(this);
            return;
        }
        if (below instanceof LockedDoor && this.falling) {
            LockedDoor lD = (LockedDoor) below;
            RuinedDoor rD = new RuinedDoor(lD.getX(), lD.getY(), lD.getColour());
            Game.getGame().replaceEntity(below.getX(), below.getY(), rD);
        }
        this.falling = false;
        // TODO - handle case where entity below is either player or enemy
    }

    /**
     * Handles the rolling behaviour of falling entities. Checks if the
     * falling entity can roll right, then if it can also roll left
     * (in which case it will randomly roll in one of the two directions),
     * otherwise it will roll right if possible, and if not it will check
     * if it can roll left, and if possible roll left. Sets the falling
     * status to false, allowing the falling entity to resume normal behaviour.
     */
    public void roll() {
        if (this.x < Game.GRID_WIDTH - 1 && this.canRollRight()) {
            if (this.x > 0 && this.canRollLeft()) {
                this.rollEitherWay();
                return;
            } else {
                this.rollRight();
            }
        } else if (this.x > 0 && this.canRollLeft()) {
            this.rollLeft();
        }
        this.falling = false;
    }

    /**
     * Randomly selects whether to roll the falling entity to the
     * right or the left, then rolls the falling entity in the chosen
     * direction.
     */
    private void rollEitherWay() {
        Random rand = new Random();
        int randIndex = rand.nextInt(ROLLING_CHOICES.length);
        int dX = ROLLING_CHOICES[randIndex];
        Game.getGame().updateLevel(this.x + dX, this.y, this);
    }

    /**
     * Rolls the falling entity to the right.
     */
    private void rollRight() {
        int nX = this.x + 1;
        Game.getGame().updateLevel(nX, this.y, this);
    }

    /**
     * Rolls the falling entity to the left.
     */
    private void rollLeft() {
        int nX = this.x - 1;
        Game.getGame().updateLevel(nX, this.y, this);
    }

    /**
     * Checks if the falling entity can roll to the left.
     * @return true or false
     */
    private boolean canRollLeft() {
        return this.emptyLeft() && this.emptyDownLeft();
    }

    /**
     * Checks if the falling entity can roll to the right.
     * @return true or false
     */
    private boolean canRollRight() {
        return this.emptyRight() && emptyDownRight();
    }

    /**
     * Checks if the tile to the left is empty.
     * @return true or false
     */
    private boolean emptyLeft() {
        Entity target = Game.getGame().getEntity(this.x - 1, this.y);
        return target instanceof Path;
    }

    /**
     * Checks if the tile to the right is empty.
     * @return true or false
     */
    private boolean emptyRight() {
        Entity target = Game.getGame().getEntity(this.x + 1, this.y);
        return target instanceof Path;
    }

    /**
     * Checks if the tile down and to the left is empty.
     * @return true or false
     */
    private boolean emptyDownLeft() {
        Entity target = Game.getGame().getEntity(this.x - 1, this.y + 1);
        return target instanceof Path;
    }

    /**
     * Checks if the tile down and to the right is empty.
     * @return true or false
     */
    private boolean emptyDownRight() {
        Entity target = Game.getGame().getEntity(this.x + 1, this.y + 1);
        return target instanceof Path;
    }

    /**
     * Checks if the falling entity is currently falling.
     * @return true or false
     */
    public boolean isFalling() {
        return falling;
    }
}
