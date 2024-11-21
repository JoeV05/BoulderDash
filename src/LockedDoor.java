/**
 * @author Joseph Vinson, Tafara Gonese
 */

public class LockedDoor extends Wall {
    private final Colour colour;

    public static final WallType wType = WallType.LOCKED_DOOR;

    public LockedDoor(int x, int y, Colour colour) {
        super(x, y, wType);
        this.colour = colour;
    }

    public boolean canUnlock(Key key) {
        return key.getColour() == this.colour;
    }

    public void unlock() {
        this.walkable = false;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean isLocked() {
        return walkable;
    }
}
