import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class MagicWall extends ActionWall {
    private boolean hasEntityStored;
    private boolean finishedTransforming;
    private FallingEntity stored;

    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL);
        this.hasEntityStored = false;
    }

    @Override
    public void tick() {
        if (this.hasEntityStored) {
            this.dropStored();
            this.hasEntityStored = false;
        }
    }

    // TODO - think this is actually all fine, maybe a sanity check from Jamie and Tafara?
    // TODO - javadoc method comment
    public void transform(FallingEntity falling) {
        Game.removeFallingEntity(falling);
        int fX = falling.getX();
        int fY = falling.getY();
        if (falling instanceof Boulder) {
            this.stored = new Diamond(this.x, this.y);
            Game.replaceEntity(fX, fY, new Path(fX, fY));
        } else {
            this.stored = new Boulder(this.x, this.y);
            Game.replaceEntity(fX, fY, new Path(fX, fY));
        }
        this.hasEntityStored = true;
    }

    // TODO - Make the magic wall either drop the FallingEntity it has stored or transfer it into a MagicWall
    //  if there is one beneath this one (make use of static methods in Game)
    // TODO - javadoc method comment
    public void dropStored() {
        if (this.y > Game.GRID_HEIGHT - 2) {
            System.out.println("Liam is a wet fish");
        }
        Entity below = Game.getEntity(this.x, this.y + 1);
        if (below instanceof Path) {
            this.stored.setY(this.y + 1);
            Game.replaceEntity(this.x, this.y + 1, this.stored);
            Game.addFallingEntity(this.stored);
            Game.removeFallingEntity(this.stored);
            this.stored = null;
            this.hasEntityStored = false;
        } else if (below instanceof MagicWall) {
            // TODO - somehow chain the effect of magic wall transformation
        }
    }
}
