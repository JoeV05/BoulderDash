/**
 * @author Joseph Vinson
 */

public abstract class FallingEntity extends Entity {
    private final FallingType entityType;

    public FallingEntity(int x, int y, FallingType entityType) {
        super(x, y);

        this.entityType = entityType;
    }

    public FallingType getEntityType() {
        return entityType;
    }

    public void fall() {
        //TODO - use replaceEntity to move it to the tile below, assuming the tile below is either empty or a magic wall
    }
}
