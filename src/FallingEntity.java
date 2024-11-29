import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    private final FallingType entityType;

    public FallingEntity(int x, int y, FallingType entityType, Image sprite) {
        super(x, y, sprite);

        this.entityType = entityType;
    }

    public FallingType getEntityType() {
        return entityType;
    }

    public void fall() {
        /*TODO - use Game.replaceEntity() [unsure of exact implementation]
            to move it to the tile below, assuming the tile below is either empty or a magic wall
            [needs to handle magic walls differently, i.e. use transform method from magic wall]
            also somehow kill the player and enemies
         */
    }
}
