import javafx.scene.image.Image;

import javax.crypto.AEADBadTagException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// TODO - javadoc class comment
// TODO - maybe tidy up comments?

public class Frog extends Enemy {


    public Frog(int x, int y) {
        super(x, y, new Image("./sprites/frog.png"));
    }

    @Override
    public void movementTests() {

    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     *
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    @Override
    public int onDeathByHazard() {
        return 0;
    }

    /**
     * Performs any actions done when an enemy dies by a hazard and returns what they should drop on their death
     *
     * @return int representing a particular item or set of items to be dropped on enemy death
     */
    @Override
    public void onDeathByFallingObject(Entity below) {
        int positionX = below.getX();
        int positionY = below.getY();
        if (checker(positionX, positionY) == true) {
            Game.getGame().updateLevel(positionX, positionY, below);
            positionX = positionX + 1;
        } else {
            positionX = positionX + 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY = positionY + 1;
        } else {
            positionY = positionY + 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX = positionX - 1;
        } else {
            positionX = positionX - 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX = positionX - 1;
        } else {
            positionX = positionX - 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY = positionY - 1;
        } else {
            positionY = positionY - 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY = positionY - 1;
        } else {
            positionY = positionY - 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX = positionX + 1;
        } else {
            positionX = positionX + 1;
        }
        if (checker(positionX, positionY) == true) {
            Entity replaced = Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX = positionX + 1;
        } else {
            positionX = positionX + 1;
        }
    }

    public boolean checker(int x, int y) {
        Entity check = Game.getGame().getEntity(x, y);
        if (check instanceof Exit) {
            return false;
        } else if (check instanceof Wall) {
            WallType notUnbreakable = ((Wall) check).getWallType();
            if (notUnbreakable == WallType.TITANIUM_WALL) {
                return false;
            } else {
                return true;
            }
        } else if (check instanceof Butterfly) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(x, y, dropedDiamond);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Get the neighbours of a specific node which are not null designed to be used for shortest path in move
     * @param target GraphNode node you are getting the neighbours of
     * @param graphedLevelState GraphNode[][] the graph the target is in
     * @return ArrayList<GraphNode> Neighbours of target which are not null
     */
    private ArrayList<GraphNode> getNeighbours(GraphNode target, GraphNode[][] graphedLevelState){
        ArrayList<GraphNode> output = new ArrayList<>();
        Integer[] neighbour1 = {target.getY() + 1,target.getX() + 1};
        Integer[] neighbour2 = {target.getY() - 1,target.getX() +1};
        Integer[] neighbour3 = {target.getY() -1,target.getX() -1};
        Integer[] neighbour4 = {target.getY() + 1,target.getX() -1};
        //Outer if statements make sure that each neighbour is an index in the graphed level state array
        //Inner if statements check that the neighbour is not null
        if (neighbour1[0] <= graphedLevelState[0].length -1 &&  neighbour1[1] <= graphedLevelState.length - 1
                && neighbour1[0] >= 0 && neighbour1[1] >= 0){
            if (graphedLevelState[neighbour1[0]][neighbour1[1]] != null){
                output.add(graphedLevelState[neighbour1[0]][neighbour1[1]]);
            }
        }

        if (neighbour2[0] <= graphedLevelState[0].length -1 &&  neighbour2[1] <= graphedLevelState.length - 1
                && neighbour2[0] >= 0 && neighbour2[1] >= 0){
            if (graphedLevelState[neighbour2[0]][neighbour2[1]] != null){
                output.add(graphedLevelState[neighbour2[0]][neighbour2[1]]);
            }

        }

        if (neighbour3[0] <= graphedLevelState[0].length -1 &&  neighbour3[1] <= graphedLevelState.length - 1
                && neighbour3[0] >= 0 && neighbour3[1] >= 0){

            if (graphedLevelState[neighbour1[0]][neighbour1[1]] != null){
                output.add(graphedLevelState[neighbour3[0]][neighbour3[1]]);
            }
        }

        if (neighbour4[0] <= graphedLevelState[0].length -1 &&  neighbour4[1] <= graphedLevelState.length - 1
                && neighbour4[0] >= 0 && neighbour4[1] >= 0){

            if (graphedLevelState[neighbour1[0]][neighbour1[1]] != null){
                output.add(graphedLevelState[neighbour1[0]][neighbour4[1]]);
            }
        }
        return output;
    }
    /**
     * Moves the frog to be called in main
     */
    @Override
    public void move() {
        Game game = Game.getGame();
        Entity[][] currentLevelState = game.getMap();
        GraphNode[][] graphedLevelState = new GraphNode[currentLevelState.length][currentLevelState[0].length];

        int x = 0;
        int y = 0;
        //Goes through everything that is not a path or player and changes it to null
        for (int i = 0; i < currentLevelState.length * currentLevelState[0].length; i++) {
            System.out.println("Resetting");
            boolean reset = false;
            if (currentLevelState[y][x] instanceof Player) {
                graphedLevelState[y][x] = new GraphNode(true, y, x);
            } else if (currentLevelState[y][x] instanceof Path) {
                graphedLevelState[y][x] = new GraphNode(false, y, x);
            }

            if (x == currentLevelState.length) {
                y++;
                x = 0;
                reset = true;
            }
            if (!reset) {
                x++;
            }

        }
        //START OF PATHFINDING implementing bfs and then querying the position of the player
        GraphNode startLocation = graphedLevelState[getY()][getX()];
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(new GraphNode(false, getY(), getX()));
        ArrayList<GraphNode> visited = new ArrayList<>();
        int distance = 0;
        while (queue.size() > 0) {
            distance ++;
            GraphNode currentNode = queue.poll();
            visited.add(currentNode);
            ArrayList<GraphNode> Neighbours = getNeighbours(currentNode,graphedLevelState);
            for (int i = 0; i < Neighbours.size() - 1; i++) {
            }
        }


    }
}




