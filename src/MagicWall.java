/**
 * Represents a magic wall that can transform boulders and diamonds that
 * fall onto it.
 * @author Joseph Vinson
 * @version 1.5
 */

public class MagicWall extends ActionWall {
    public static final int MAXIMUM_HEIGHT_INDEX = Game.MAX_HEIGHT_INDEX - 1;
    private boolean hasEntityStored;
    private FallingType stored;

    /**
     * Create a new magic wall at a given (x, y) coordinate. Starts with no
     * falling entity stored inside.
     * @param x The x coordinate to create the magic wall at.
     * @param y The y coordinate to create the magic wall at.
     */
    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL);
        this.hasEntityStored = false;
    }

    /**
     * Update the magic wall on a tick. Check if the magic wall should drop its
     * stored falling entity, if it should chain transformation with a magic
     * wall below, otherwise it will simply retain its stored falling
     * entity.
     */
    @Override
    public void tick() {
        Game game = Game.getGame();
        if (this.y > MAXIMUM_HEIGHT_INDEX) {
            return;
        }
        Entity below = game.getEntity(this.x, this.y + 1);

        if (this.hasEntityStored) {
            if (below instanceof Path) {
                FallingEntity f = this.dropStored();
                Game.getGame().replaceEntity(this.x, this.y + 1, f);
                Game.getGame().addFallingEntity(f);
                this.hasEntityStored = false;
                this.stored = null;
            } else if (below instanceof MagicWall) {
                ((MagicWall) below).transform(this.stored);
                this.hasEntityStored = false;
                this.stored = null;
            }
        }
    }

    /**
     * Takes an incoming falling entity and transforms it to the appropriate
     * type of falling entity. Turns diamonds into boulders and boulders
     * into diamonds.
     * @param falling The falling entity to transform.
     */
    public void transform(FallingEntity falling) {
        if (this.hasEntityStored) {
            return;
        }
        Game.getGame().removeFallingEntity(falling);
        int fX = falling.getX();
        int fY = falling.getY();
        if (falling instanceof Boulder) {
            this.stored = FallingType.DIAMOND;
            Game.getGame().replaceEntity(fX, fY, new Path(fX, fY));
        } else {
            this.stored = FallingType.BOULDER;
            Game.getGame().replaceEntity(fX, fY, new Path(fX, fY));
        }
        this.hasEntityStored = true;
    }

    /**
     * Takes the type of an incoming falling entity and transforms it to the
     * appropriate type. Diamonds become boulders and boulders become diamonds.
     * Used when a magic wall is below another magic wall and the
     * transformation process should be chained.
     * @param incomingEntity The type of the falling entity to transform.
     */
    public void transform(FallingType incomingEntity) {
        if (incomingEntity == FallingType.BOULDER) {
            this.stored = FallingType.DIAMOND;
        } else {
            this.stored = FallingType.BOULDER;
        }
        this.hasEntityStored = true;
    }

    /**
     * Drop the falling entity stored inside the magic wall.
     * @return Either a Boulder object, a Diamond object or null.
     */
    public FallingEntity dropStored() {
        Game game = Game.getGame();
        Entity below = game.getEntity(this.x, this.y + 1);
        if (below instanceof Path) {
            FallingEntity f;
            if (this.stored == FallingType.BOULDER) {
                f = new Boulder(this.x, this.y + 1);
            } else {
                f = new Diamond(this.x, this.y + 1);
            }
            return f;
        }
        return null;
    }
}
