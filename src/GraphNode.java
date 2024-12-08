/**
 * A class designed to represent a node in a graph which represents the level
 * state. To be used in the frog for pathfinding.
 * @author Edward Tickle
 */
public class GraphNode {
    private int distance = -1;
    private boolean isPlayer;
    private int x;
    private int y;
    private GraphNode parent;

    /**
     * Constructor for graph node.
     * @param isPlayer true if it is a player , false if not.
     * @param x The x coordinate for this node.
     * @param y The y coordinate for this node.
     */
    public GraphNode(boolean isPlayer, int y, int x) {
        this.isPlayer = isPlayer;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets distance from the start node.
     * @return int distance from starting node returns -1 if no distance added.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets distance from the start node.
     * @param distance int distance from starting node.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Returns whether this node is the player.
     * @return true or false.
     */
    public boolean isPlayer() {
        return isPlayer;
    }

    /**
     * Sets the player status of this node.
     * @param player Boolean representing whether this node is the player.
     */
    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    /**
     * Gets the x coordinate of this node.
     * @return Integer between 0 and map width - 1.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate of this node.
     * @param x The x coordinate to set this node as being at.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y coordinate of this node.
     * @return Integer between 0 and map height - 1.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate of this node.
     * @param y The y coordinate to set this node as being at.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets parent of this node.
     * @return GraphNode Parent.
     */
    public GraphNode getParent() {
        return parent;
    }

    /**
     * Sets Parent of this node.
     * @param parent GraphNode parent.
     */
    public void setParent(GraphNode parent) {
        this.parent = parent;
    }
}
