import javafx.scene.image.Image;

public class Butterfly extends Firefly {

    /**
     * pending image
     *
     * @return Image of butterfly
     */

    public Butterfly(int x , int y) {
        super(x, y, new Image("./sprites/butterfly.png"));
    }

    /**
     * A Test method designed to be used inside moveTo to test your movement is working correctly
     */
    @Override
    public void movementTests() {

    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     * @return int representing a particular item or set of items to be dropped on enemy death
	 * butterfly needs to differ from other enemies by dropping diamonds 
     */

    @Override
    public int onDeathByHazard() {
        return 0;
    }
    
    /**
     * performs actions done when enemies die by falling objects for most this will replace a 3 by 3 area with path excluding exeption objects of titanium walls and the exit
     *  butterfly will differ by replacing the path with diamonds instead
     */

    @Override
    public int onDeathByFallingObject() {
        return 0;
    }
}
