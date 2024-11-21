/**
 * @author Tafara Gonese
 */

public class Key {
    private Colour colour;
    private int x;
    private int y;

    public Key(Colour colour, int x, int y) {
        this.colour = colour;
        this.x = x;
        this.y = y;
    }

    public Colour getColour() {
        return colour;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canUnlock(LockedDoor lockedDoor) {
        return lockedDoor.getColour() == this.colour;
    }

    public void use(LockedDoor lockedDoor) {
        if (canUnlock(lockedDoor)) {
            lockedDoor.unlock();
        }
    }
}
