import org.junit.jupiter.api.Test;
import javafx.scene.image.Image;

/**
 * The Enemy Class which all enemies inherit from
 * @version 1.0
 * @author Edward Tickle
 */
public abstract class Enemy extends Entity{

    protected Enemy(int row, int column, Image image) {
        super(row,column,image);
    }
    //TODO Some kind of image representing enemy (Talk to James about how this will work)
    // TODO - ^ Looks like maybe this has been done (based off image being in constructor)
    /**
     * A Test method designed to be used inside moveTo to test your movement is working correctly
     */

    @Test
    public abstract void movementTests();
    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     * @return int representing a particular item or set of items to be dropped on enemy death
     */

    public abstract int onDeathByHazard();

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    public abstract int onDeathByFallingObject();

    // TODO - void methods shouldn't really have return in javadoc comment
    /**
     * Moves the enemy
     * @return void
     */
    public abstract void move();

}
