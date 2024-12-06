import javafx.scene.image.Image;

import java.util.Random;

// TODO - proper javadoc comment

/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    private final FallingType entityType;
    protected boolean falling;

    public static final int[] ROLLING_CHOICES = {-1, 1};

    public FallingEntity(int x, int y, FallingType entityType, Image image) {
        super(x, y,image);

        this.entityType = entityType;
        this.falling = false;
    }

    // TODO - is this method necessary
    // TODO - javadoc method comment
    public FallingType getEntityType() {
        return entityType;
    }

    // TODO - javadoc method comment
    public void fall() {
        ///Game game = Game.getGame();
        if (this.y > Game.GRID_HEIGHT) {
            throw new LiamWetFishException("HOT SINGLE FISH IN YOUR AREA");
        }
        // TODO - WHY THE FISH IS THIS -2 ARGHHHHHHH
        if (this.y == Game.GRID_HEIGHT - 2) {
            return;
        }
        Entity below = Game.getGame().getEntity(this.x, this.y + 1);
        if (!(below instanceof Path || below instanceof MagicWall)) {
            if (this.x < Game.GRID_WIDTH - 1 && Game.getGame().getEntity(this.x + 1, this.y) instanceof Path && this.falling) {
                if (this.x > 0 && Game.getGame().getEntity(this.x - 1, this.y) instanceof Path) {
                    Random r = new Random();
                    int dX = FallingEntity.ROLLING_CHOICES[r.nextInt(FallingEntity.ROLLING_CHOICES.length)];
                    Game.getGame().updateLevel(this.x + dX, this.y, this);
                    this.falling = false;
                    return;
                }
                int nX = this.x + 1;
                Game.getGame().updateLevel(nX, this.y, this);
                this.falling = false;
                return;
            } else if (this.x > 0 && Game.getGame().getEntity(this.x - 1, this.y) instanceof Path && falling) {
                int nX = this.x - 1;
                Game.getGame().updateLevel(nX, this.y, this);
                this.falling = false;
                return;
            }
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
        //TODO - handle case where entity below is either player or enemy
    }
}
