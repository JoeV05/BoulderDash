import javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 */

public class MagicWall extends ActionWall {
    private boolean hasEntityStored;
    private FallingType stored;

    public MagicWall(int x, int y) {
        super(x, y, WallType.MAGIC_WALL);
        this.hasEntityStored = false;
    }

    @Override
    public void tick() {
        if (this.y > Game.GRID_HEIGHT - 2) {
            return;
        }
        Entity below = Game.getEntity(this.x, this.y + 1);
        if (this.hasEntityStored) {
            if (below instanceof Path) {
                FallingEntity f = this.dropStored();
                Game.replaceEntity(this.x, this.y + 1, f);
                Game.addFallingEntity(f);
                this.hasEntityStored = false;
                this.stored = null;
            } else if (below instanceof MagicWall) {
                ((MagicWall) below).transform(this.stored);
                this.hasEntityStored = false;
                this.stored = null;
            }
        }
    }

    // TODO - think this is actually all fine, maybe a sanity check from Jamie and Tafara?
    // TODO - javadoc method comment
    public void transform(FallingEntity falling) {
        Game.removeFallingEntity(falling);
        int fX = falling.getX();
        int fY = falling.getY();
        if (falling instanceof Boulder) {
            this.stored = FallingType.DIAMOND;
            Game.replaceEntity(fX, fY, new Path(fX, fY));
        } else {
            this.stored = FallingType.BOULDER;
            Game.replaceEntity(fX, fY, new Path(fX, fY));
        }
        this.hasEntityStored = true;
    }

    public void transform(FallingType stored) {
        if (stored == FallingType.BOULDER) {
            this.stored = FallingType.DIAMOND;
        } else {
            this.stored = FallingType.BOULDER;
        }
        this.hasEntityStored = true;
    }

    // TODO - Make the magic wall either drop the FallingEntity it has stored or transfer it into a MagicWall
    //  if there is one beneath this one (make use of static methods in Game)
    // TODO - javadoc method comment
    public FallingEntity dropStored() {
        if (this.y > Game.GRID_HEIGHT - 2) {
            System.out.println("Liam is a wet fish");
        }
        Entity below = Game.getEntity(this.x, this.y + 1);
        if (below instanceof Path) {
            FallingEntity f;
            if (this.stored == FallingType.BOULDER) {
                f = new Boulder(this.x, this.y + 1);
            } else {
                f = new Diamond(this.x, this.y + 1);
            }
            return f;
        }/* else if (below instanceof MagicWall) {
            // TODO - somehow chain the effect of magic wall transformation
        }*/
        return null;
    }
}
