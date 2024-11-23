/**
 * @author Tafara Gonese, Joseph Vinson
 */

public class Key extends Tile{
    private final Colour colour;

    public Key(Colour colour, int x, int y) {
        super(x, y, false, TileType.KEY);
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
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
