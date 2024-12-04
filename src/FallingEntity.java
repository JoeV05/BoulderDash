import javafx.scene.image.Image;

// TODO - proper javadoc comment

/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    private final FallingType entityType;

    public FallingEntity(int x, int y, FallingType entityType, Image image) {
        super(x, y,image);

        this.entityType = entityType;
    }

    // TODO - is this method necessary
    // TODO - javadoc method comment
    public FallingType getEntityType() {
        return entityType;
    }

    // TODO - javadoc method comment
    public void fall() {
        Game game = Game.getGame();
        if (this.y > Game.GRID_HEIGHT) {
            throw new LiamWetFishException("HOT SINGLE FISH IN YOUR AREA");
        }
        // TODO - WHY THE FISH IS THIS -2 ARGHHHHHHH
        if (this.y == Game.GRID_HEIGHT - 2) {
            return;
        }
        Entity below = game.getEntity(this.x, this.y + 1);
        if (below instanceof Path) {
            Game.updateLevel(x, y + 1, this);
        }
        if (below instanceof MagicWall) {
            ((MagicWall) below).transform(this);
        }
        //TODO - handle case where entity below is either player or enemy
        //  ^^^ This is for hazards people, not tiles, stop trying to make me do your work for you
    }
}
