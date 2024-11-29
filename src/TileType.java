/**
 * Enumeration to represent the different types of tile
 * @author Joseph Vinson
 * edit I removed enum Door. as Door is already under Wall
 */

public enum TileType {
    PATH,
    /**
     * Represents a walkable tile. Paths are accessible by both players and enemies.
     */
    DIRT,
    /**
     * Represents a dirt tile. Players can walk on dirt, but it transforms into a path
     * after being stepped on. Enemies cannot walk on dirt.
     */
    KEY,
    /**
     * The key is a collectible tile of a specified colour used to open a locked door
     */
    AMOEBA,
    /**
     * Represents an amoeba tile. Amoebae are dynamic and can spread to adjacent tiles
     *(dirt or empty path) at a specified rate. They grow as a connected group.
     * If an amoeba group becomes completely surrounded and cannot spread, it transformsinto diamonds.
     * If the amoeba group grows beyond a predefined size, it turns into boulders,
     *Amoebae can destroy enemies upon contact (in any direction).
     *Vulnerable to explosions, which can destroy them.
     **/
    WALL,
    /**
     * Represents a wall that blocks all movement.
     * Walls have sublasses with unique abilities.
     */
    EXIT
    /**
     * The exit to the level. To access this tile the player must collect the
     * required number of diamonds without the timer running out
     */
}
