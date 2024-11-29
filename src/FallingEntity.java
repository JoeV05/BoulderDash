import javafx.scene.image.Image;
/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    private final FallingType entityType;

    public FallingEntity(int x, int y, FallingType entityType, Image image) {
        super(x, y,image);

        this.entityType = entityType;
    }

    public FallingType getEntityType() {
        return entityType;
    }

    public void fall() {
        //TODO - use replaceEntity to move it to the tile below, assuming the tile below is empty
        //TODO - use transform from MagicWall if the tile below isInstanceOf(MagicWall)
        //TODO - handle case where tile below isInstanceOf(Enemy)
    }
}
