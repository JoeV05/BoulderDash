import javafx.scene.image.Image;

// TODO - javadoc class comment
// TODO - javadoc comments in general don't seem to be particularly coherent

public class Firefly extends Enemy {

    /**
     * Talk to james about image
     *
     * @return Image of Firefly
     *
     * // TODO - has this been done, can this comment be removed?
     */

    public Firefly(int x, int y) {
        super(x, y, new Image("./sprites/firefly.png"));
    }

    //to be used by butterfly inheritance to carry its sprite up the chain
    protected Firefly(int x, int y, Image image) {
        super(x, y, image);
    }

    /**
     *
     */
    @Override
    public void move() {

    }

    // TODO - this comment doesn't seem relevant to the method it was placed above, maybe needs moving?
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
