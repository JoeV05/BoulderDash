/**
 * A class designed to represent a node in a graph which represents the level state
 * To be used in the frog for pathfinding
 * @author Edward Tickle
 */
public class GraphNode {
    private int distance = -1;
    private boolean isPlayer;
    private int x;
    private int y;
    private GraphNode parent;

    /**
     * Constructor for graph node
     * @param isPlayer true if it is a player , false if not
     */
    public GraphNode(boolean isPlayer,int y,int x){
        this.isPlayer = isPlayer;
        this.x = x;
        this.y = y;
    }

    /**
     * Gets distance
     * @return int distance from starting node returns -1 if no distance added
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets distance
     * @param distance int distance from starting node
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Gets isPlayer
     * @return boolean isPlayer
     */
    public boolean isPlayer() {
        return isPlayer;
    }

    /**
     * Sets player
     * @param player boolean player
     */
    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    /**
     * Gets X coordinate
     * @return int x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets X coordinate
     * @param x int x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y coordinate
     * @return Y int Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets Y coordinate
     * @param y int y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * gets Parent of this node
     * @return GraphNode Parent
     */
    public GraphNode getParent() {
        return parent;
    }

    /**
     * Sets Parent of graphNode
     * @param parent GraphNode parent
     */
    public void setParent(GraphNode parent) {
        this.parent = parent;
    }
}
