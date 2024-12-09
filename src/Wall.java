import  javafx.scene.image.Image;

/**
 * Represents a wall tile in the game. Walls are barriers of movements for both
 * players and enemies with specific type based behaviours.
 * @author Joseph Vinson
 * @version 1.3
 */
public class Wall extends Tile {
    protected WallType wallType; // The specific type of this wall

    /**
     * Constructor for wall class
     * @param x x position
     * @param y y position
     * @param wallType type of wall
     */
    public Wall(int x, int y, WallType wallType) {
        super(x, y, false, TileType.WALL, Wall.getWallSprite(wallType));

        this.wallType = wallType;
    }

    /**
     * Determine the sprite to use for a wall given its wall type.
     * @param wallType The type of the wall.
     * @return Image object containing the sprite for the wall.
     */
    private static Image getWallSprite(WallType wallType) {
        Image img;
        switch (wallType) {
            case WallType.NORMAL_WALL:
                img = new Image("./sprites/wall.png");
                break;
            case WallType.TITANIUM_WALL:
                img = new Image("./sprites/titanium.png");
                break;
            case WallType.MAGIC_WALL:
                img = new Image("./sprites/magic_wall.png");
                break;
            case WallType.EXIT:
                img = new Image("./sprites/exit_locked.png");
                break;
            case WallType.LOCKED_DOOR:
                img = new Image("./sprites/titanium.png");
                break;
            default:
                System.out.println(wallType);
                throw new IllegalArgumentException("Invalid wall type "
                        + wallType);
        }
        return img;
    }

    /**
     * Get the type of this wall.
     * @return NORMAL_WALL, TITANIUM_WALL, MAGIC_WALL, LOCKED_DOOR or EXIT
     */
    public WallType getWallType() {
        return this.wallType;
    }
}
