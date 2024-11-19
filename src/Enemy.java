/**
 * The Enemy Class which all enemies inherit from
 * @version 1.0
 * @author Edward Tickle
 */
public abstract class Enemy implements MoveTo {
    private int [] position;
    //TODO Some kind of image representing enemy (Talk to James about how this will work)
    @Override
    public abstract  int[] moveTo();


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

    /**
     * Getter for Position
     * @return Int[] Position
     */
    public int[] getPosition() {
        return position;
    }


}
