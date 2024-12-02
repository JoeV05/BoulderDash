import javafx.scene.image.Image;

// TODO - maybe make a subclass for walkables so isInstanceOf can be used instead of variable check

/**
 * @author James Harvey, Joseph Vinson
 */

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image sprite;

    public Entity(int x, int y, Image sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getSprite() {
        return this.sprite;
    }

    // TODO - is this needed? it's protected, so only classes that already have the ability
    //  to directly set the value of sprite could use it
    protected void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
