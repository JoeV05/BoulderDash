// TODO - if tile no longer needs to exist, presumably this also no longer needs to exist
// TODO - please, someone talk about this, I need to know if anyone agrees

/**
 * Enumeration to represent the different types of tile
 * @author Joseph Vinson
 */

public enum TileType {
    /**
     * Represents a walkable tile. Paths are accessible by both players and enemies.
     */
    PATH,
    /**
     * Represents a dirt tile. Players can walk on dirt, but it transforms into a path
     * after being stepped on. Enemies cannot walk on dirt.
     */
    DIRT,
    /**
     * The key is a collectible tile of a specified colour used to open a locked door
     */
    KEY,
    /**
     * Represents an amoeba tile. Amoebae are dynamic and can spread to adjacent tiles
     *(dirt or empty path) at a specified rate. They grow as a connected group.
     * If an amoeba group becomes completely surrounded and cannot spread, it transforms into diamonds.
     * If the amoeba group grows beyond a predefined size, it turns into boulders,
     *Amoebae can destroy enemies upon contact (in any direction).
     *Vulnerable to explosions, which can destroy them.
     **/
    AMOEBA,
    /**
     * Represents a wall that blocks all movement.
     * Walls have sublasses with unique abilities.
     */
    WALL,
    /**
     * Represents a locked door that acts as a wall until the player uses
     * the colour specified key turning the wall to a path
     */
    DOOR,
    /**
     * The exit to the level. To access this tile the player must collect the
     * required number of diamonds without the timer running out
     */
    EXIT
}
