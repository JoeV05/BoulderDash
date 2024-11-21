/**
 * @author Joseph Vinson, Tafara Gonese
 */

public class LockedDoor extends Wall {
    private Colour colour;
    private boolean locked;

    public static final WallType wType = WallType.LOCKED_DOOR;

    private int x;
    private int y;

    public LockedDoor(int x, int y, Colour color) {
        super(x, y, wType);
        this.x = x;
        this.y = y;
        this.colour = colour;
        this.locked = true;
    }

    public boolean canUnlock(Key key) {
        return key.getColour() == this.colour;
    }

    public void unlock() {
        this.locked = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean isLocked() {
        return locked;
    }
}
