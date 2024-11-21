/**
 * @author Joseph Vinson
 */

public class LockedDoor extends Wall {

    public static final WallType wType = WallType.LOCKED_DOOR;

    public LockedDoor(int x, int y) {
        super(x, y, wType);
    }
}
