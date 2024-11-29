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
        // TODO - If the tile below is empty somehow drop the stored FallingEntity
        if (this.x < 23) {// TODO - ask James if getEntity() should be static so it can be used here
            // TODO - drop stored into tile below, update x and y for stored (y should increase by 1)
        } else if (true) { // TODO - check if tile below is magic wall
            // TODO - somehow chain the effect of magic wall transformation
        }
    }

    public void chainMagicWalls() {
        // TODO - Maybe magic walls can be chained together? (not in spec but as additional feature)
    }
}
