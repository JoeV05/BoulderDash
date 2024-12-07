import javafx.scene.image.Image;

/**
 * Represents any object that can exist on the map withing the game.
 * @author James Harvey, Joseph Vinson
 * @version 1.2
 */
public abstract class Entity {
    protected int x;
    protected int y;
    protected Image sprite;

    /**
     * Create an entity at the given (x, y) coordinates with the given sprite.
     * @param x The x coordinate of the entity.
     * @param y The y coordinate of the entity.
     * @param sprite The sprite of the entity.
     */
    public Entity(int x, int y, Image sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    /**
     * Retrieves the x coordinate of the entity.
     * @return An integer x between 0 and the width of the map - 1.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retrieves the y coordinate of the entity.
     * @return An integer y between 0 and the height of the map - 1.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Set the x coordinate of the entity.
     * @param newX The new x coordinate for the entity.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Set the y coordinate of the entity.
     * @param newY The new y coordinate for the entity.
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Get the current sprite of the entity.
     * @return An Image object containing the sprite.
     */
    public Image getSprite() {
        return this.sprite;
    }

    /**
     * Set the sprite of the entity.
     * @param sprite The new sprite for the entity.
     */
    protected void setSprite(Image sprite) {
        this.sprite = sprite;
    }
}
