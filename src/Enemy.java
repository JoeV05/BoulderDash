import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;

/**
 * The Enemy class which all enemies inherit from.
 * @version 1.0
 * @author Edward Tickle
 */
public abstract class Enemy extends Entity{
    /**
     * Creates an enemy at the given (x, y) coordinates with the given
     * @param x The x coordinate of the enemy.
     * @param y The y coordinate of the enemy.
     * @param sprite The sprite for the enemy to use.
     */
    protected Enemy(int x, int y, Image sprite) {
        super(x, y, sprite);
    }

    /**
     * A Test method designed to be used inside moveTo to test your movement
     * is working correctly.
     */
    @Test
    public abstract void movementTests();

   
    /**
     * Performs any actions done when an enemy dies by a hazard and returns
     * what they should drop on their death.
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    public abstract void onDeathByFallingObject(Entity below);

    /**
     * Moves the enemy.
     */
    public abstract void move();

}
