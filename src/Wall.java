import java.io.File;
import javafx.scene.image.Image;
/**
 * @author Joseph Vinson
 * Represents a wall tile in the game. Walls are barries of movements for both
 * players and enemies with specific type based behavious
 */

public class Wall extends Tile {
    protected WallType wallType;

    public Wall(int x, int y, WallType wallType) {
        super(x, y, false, TileType.WALL, getWallSprite(wallType));
        this.wallType = wallType;
    }

    private static Image getWallSprite(WallType wallType) {
        Image img;

        switch (wallType) {
            case WallType.NORMAL_WALL:
                img = new Image("./sprites/normal_wall.png");
                break;
            case WallType.TITANIUM_WALL:
                img = new Image("./sprites/titanium_wall.png");
                break;
            case WallType.MAGIC_WALL:
                img = new Image("./sprites/magic_wall.png");
                break;
            default:
                img = null;
                break;
        }

        return img;
    }
}
