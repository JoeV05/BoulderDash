import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class MagicWall extends Wall {
    private boolean isActive;
    private FallingEntity stored;

    public MagicWall(int x, int y,Image image) {
        super(x, y, WallType.MAGIC_WALL,image);
    }

    public void transform(FallingEntity falling) {
        if (falling.getEntityType() == FallingType.BOULDER) {
            this.stored = new Diamond(this.x, this.y,new Image("tiles/diamond.png"));
        } else {
            this.stored = new Boulder(this.x, this.y,new Image("tiles/boulder.png"));
        }
        this.isActive = true;
    }

    public void dropStored() {
        //Game.???(this.stored)
    }
}
