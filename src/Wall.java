import  javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 * Represents a wall tile in the game. Walls are barries of movements for both
 * players and enemies with specific type based behavious
 */

public class Wall extends Tile {
    protected WallType wallType; // The specific type of this wall (e.g., Normal, Titanium, Magic, Locked Door).

    public Wall(int x, int y, WallType wallType) {
        super(x, y, false, TileType.WALL, Wall.getWallSprite(wallType)); //this type of tile is not walkable, and it belongs to the wall type

        this.wallType = wallType; //sets the type of wall i.e. magic, titanium,door
    }

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
            default:
                System.out.println(wallType);
                throw new LiamWetFishException("WHAT THE FISH??? WHERES THE FISHING WALLTYPE???");
        }
        return img;
    }

}
