import javafx.scene.image.Image;

import java.util.Random;

// TODO - proper javadoc comment

/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    protected boolean falling;

    public static final int[] ROLLING_CHOICES = {-1, 1};

    public FallingEntity(int x, int y, Image image) {
        super(x, y,image);

        this.falling = false;
    }

    // TODO - javadoc method comment
    public void tick() {
        if (this.y > Game.GRID_HEIGHT) {
            throw new IllegalStateException("FallingEntity " + this + " out of bounds at\nx = " + this.x + "\ny = " + this.y);
        }
        // TODO - investigate why this is -2 instead of -1
        // TODO - remove magic number maybe?
        if (this.y == Game.GRID_HEIGHT - 2) {
            return;
        }
        Entity below = Game.getGame().getEntity(this.x, this.y + 1);
        if (Game.isRound(below)) {
            this.roll();
            return;
        }
        if (below instanceof Path) {
            Game.getGame().updateLevel(x, y + 1, this);
            this.falling = true;
            return;
        }
        if (below instanceof MagicWall) {
            ((MagicWall) below).transform(this);
            return;
        }
        this.falling = false;
        // TODO - handle case where entity below is either player or enemy
    }

    // TODO - javadoc method comment
    public void roll() {
        if (this.x < Game.GRID_WIDTH - 1 && this.canRollRight()) {
            if (this.x > 0 && this.canRollLeft()) {
                Random r = new Random();
                int dX = FallingEntity.ROLLING_CHOICES[r.nextInt(FallingEntity.ROLLING_CHOICES.length)];
                Game.getGame().updateLevel(this.x + dX, this.y, this);
                return;
            }
            int nX = this.x + 1;
            Game.getGame().updateLevel(nX, this.y, this);
        } else if (this.x > 0 && this.canRollLeft()) {
            int nX = this.x - 1;
            Game.getGame().updateLevel(nX, this.y, this);
        }
        this.falling = false;
    }

    // TODO - javadoc method comment
    private boolean canRollLeft() {
        return this.emptyLeft() && this.emptyDownLeft();
    }

    // TODO - javadoc method comment
    private boolean canRollRight() {
        return this.emptyRight() && emptyDownRight();
    }

    // TODO - javadoc method comment
    private boolean emptyLeft() {
        return Game.getGame().getEntity(this.x - 1, this.y) instanceof Path;
    }

    // TODO - javadoc method comment
    private boolean emptyRight() {
        return Game.getGame().getEntity(this.x + 1, this.y) instanceof Path;
    }

    // TODO - javadoc method comment
    private boolean emptyDownLeft() {
        return Game.getGame().getEntity(this.x - 1, this.y + 1) instanceof Path;
    }

    // TODO - javadoc method comment
    private boolean emptyDownRight() {
        return Game.getGame().getEntity(this.x + 1, this.y + 1) instanceof Path;
    }

    // TODO - javadoc method comment
    public boolean isFalling() {
        return falling;
    }
}
