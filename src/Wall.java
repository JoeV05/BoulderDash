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
        super(x, y, false, TileType.WALL, new Image("./sprites/titanium_wall.png"));
        this.wallType = wallType;
    }

    public Wall(int x, int y, WallType wallType, Image sprite) {
        super(x, y, false, TileType.WALL, sprite);
        this.wallType = wallType;
    }
}
