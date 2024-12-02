import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class MagicWall extends Wall {
    private boolean isActive;
    private FallingEntity stored;

    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL, new Image("./sprites/Magic_Wall.png"));
    }

    // TODO - think this is actually all fine, maybe a sanity check from Jamie and Tafara?
    public void transform(FallingEntity falling) {
        if (falling.getEntityType() == FallingType.BOULDER) {
            this.stored = new Diamond(this.x, this.y);
        } else {
            this.stored = new Boulder(this.x, this.y);
        }
        this.isActive = true;
    }

    // TODO - Shoot me in the head, this game is a fucking nightmare
    //  Make the magic wall either drop the FallingEntity it has stored or transfer it into a MagicWall
    //  if there is one beneath this one (make use of static methods in Game)
    public void dropStored() {
        // TODO - If the tile below is empty somehow drop the stored FallingEntity
        if (this.x < 23) {// TODO - ask James if getEntity() should be static so it can be used here
            // TODO - drop stored into tile below, update x and y for stored (y should increase by 1)
        } else if (true) { // TODO - check if tile below is magic wall
            // TODO - somehow chain the effect of magic wall transformation
        }
    }

    public void chainMagicWalls() {
        // TODO - Chain magic walls together
    }
}
