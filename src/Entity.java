import javafx.scene.image.Image;

/**
 * @author James Harvey, Joseph Vinson
 */

public abstract class Entity {
    protected int x;
    protected int y;
    protected Image Sprite;

    public Entity(int x, int y, Image Sprite) {
        this.x = x;
        this.y = y;
        this.Sprite = Sprite;
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
        return this.Sprite;
    }
}
