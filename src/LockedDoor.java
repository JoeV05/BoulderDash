/**
 * @author Joseph Vinson, Tafara Gonese
 */

public class LockedDoor extends Wall {
    private final Colour colour;

    public LockedDoor(int x, int y, Colour colour) {
        super(x, y, WallType.LOCKED_DOOR);
        this.colour = colour;
    }

    public boolean canUnlock(Key key) {
        return this.colour == key.getColour();
    }

    public void unlock() {
        this.walkable = true;
    }

    public Colour getColour() {
        return this.colour;
    }

    public boolean isLocked() {
        return this.walkable;
    }
}
