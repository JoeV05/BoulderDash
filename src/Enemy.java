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
}
