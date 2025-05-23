import javafx.scene.image.Image;

/**
 * The Enemy Class which all enemies inherit from.
 * @version 1.0
 * @author Edward Tickle
 */
public abstract class Enemy extends Entity {
    protected boolean gameEnd = false;
    /**
     * Creates an enemy at the given (x, y) coordinates with the given sprite.
     * @param x The x coordinate of the enemy.
     * @param y The y coordinate of the enemy.
     * @param sprite The sprite for the enemy to use.
     */
    protected Enemy(int x, int y, Image sprite) {
        super(x, y, sprite);
    }

    /**
     * Moves the enemy.
     */
    public abstract void move();

    /**
     * Performs any actions done when an enemy dies by a falling object and
     * executes any on death actions they should do on their death.
     * @param below Entity below the enemy.
     */
    public abstract void onDeath(Entity below);
}
