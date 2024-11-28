import javafx.scene.image.Image;

public class Firefly extends Enemy {

    /**
     * Talk to james about image
     *
     * @return Image of Firefly
     */

    protected Firefly(Image image, int row, int column) {
        super(row,column,image);
    }
    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     * @return int representing a particular item or set of items to be dropped on enemy death
     */

    @Override
    public void movementTests() {

    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     * @return int representing a particular item or set of items to be dropped on enemy death
     */

    @Override
    public int onDeathByHazard() {
        return 0;
    }
    
    /**
     * Getter for Position
     * @return Int[] Position
     */

    @Override
    public int onDeathByFallingObject() {
        return 0;
    }
}
