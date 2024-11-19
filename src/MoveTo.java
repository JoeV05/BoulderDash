/**
 * The interface to allow the player and the enemies to tell the game class where they are moving
 * Version 1.0
 * @author Edward Tickle
 */
public interface MoveTo {

    /**
     * Calculates movement of the object it is attached to
     * @return int[] representing the coordinates to move the object to
     */
    public int[] moveTo();

}
