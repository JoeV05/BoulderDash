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

    // TODO - javadoc method comment
    public FallingType getEntityType() {
        return entityType;
    }

    public void fall() {
        if (this.y > Game.GRID_HEIGHT) {
            throw new LiamWetFishException("HOT SINGLE FISH IN YOUR AREA");
        }
        if (this.y == Game.GRID_HEIGHT - 1) {
            return;
        }
        if (Game.getEntity(this.x, this.y + 1) instanceof Path) {
            Game.updateLevel(x, y + 1, this);
        }
        //TODO - use updateLevel to move it to the tile below, assuming the tile below is either empty or a magic wall
        //TODO - handle case where entity below is either player or enemy
        //  ^^^ This is for hazards people, not tiles, stop trying to make me do your work for you
    }
}
