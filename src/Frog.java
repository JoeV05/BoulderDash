import javafx.scene.image.Image;

public class Frog extends Enemy {


    protected Frog(Image image,int[] position) {
        super(image,position);
    }

    /**
     * Calculates movement of the frog
     *
     * @return int [] coordinates frog should move to
     */
    @Override
    public int[] moveTo(int[][] gameState) {
        return new int[2];
    }

    /**
     * A Test method designed to be used inside moveTo to test your movement is working correctly
     */
    @Override
    public void movementTests() {

    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     *
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    @Override
    public int onDeathByHazard() {
        return 0;
    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     *
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    @Override
    public int onDeathByFallingObject() {
        return 0;
    }
}


