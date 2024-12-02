import  javafx.scene.image.Image;

/**
 * @author Joseph Vinson
 * Represents a wall tile in the game. Walls are barries of movements for both
 * players and enemies with specific type based behavious
 */

public class Wall extends Tile {
    protected WallType wallType; // The specific type of this wall (e.g., Normal, Titanium, Magic, Locked Door).

    // TODO - is sprite switching already handled?
    //  I don't remember but it looks like it?
    public Wall(int x, int y, WallType wallType) {
        super(x, y, false, TileType.WALL, new Image("./sprites/Wall.png")); //this type of tile is not walkable and it belongs to the wall type

        this.wallType = wallType; //sets the type of wall i.e. magic, titanium,door
    }

    public Wall(int x, int y, WallType wallType, Image sprite) {
        super(x, y, false, TileType.WALL, sprite);
        this.wallType = wallType;
    }
}
