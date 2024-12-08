/**
 * Enumeration to represent the different types of tile.
 * @author Joseph Vinson
 */
public enum TileType {
    /**
     * Represents a walkable tile. Paths are accessible by both players and
     * enemies.
     */
    PATH,
    /**
     * Represents a dirt tile. Players can walk on dirt, but it transforms into
     * a path after being stepped on. Enemies cannot walk on dirt.
     */
    DIRT,
    /**
     * The key is a collectible tile of a specific colour used to open a door.
     */
    KEY,
    /**
     * Represents an amoeba tile.
     */
    AMOEBA,
    /**
     * Represents a wall that blocks all movement.
     */
    WALL,
    /**
     * Represents a locked door that acts as a wall until the player uses
     * the colour specified key turning the wall to a path.
     */
    DOOR,
    /**
     * The exit to the level. To access this tile the player must collect the
     * required number of diamonds without the timer running out
     */
    EXIT
}
