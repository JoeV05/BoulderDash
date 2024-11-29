import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class MagicWall extends Wall {
    private boolean isActive;
    private FallingEntity stored;

    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL);
    }

    public void transform(FallingEntity falling) {
        if (falling.getEntityType() == FallingType.BOULDER) {
            this.stored = new Diamond(this.x, this.y);
        } else {
            this.stored = new Boulder(this.x, this.y);
        }
        this.isActive = true;
    }

    public void dropStored() {
        //Game.???(this.stored)
    }
}
