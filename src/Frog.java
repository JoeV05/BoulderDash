import javafx.scene.image.Image;

import java.util.*;

/**
 * A class designed to represent a frog,
 * @author Edward Tickle
 * @version 1.0
 */
public class Frog extends Enemy {

    /**
     * Constructor for the frog class
     * @param x int x position
     * @param y int y position
     */
    public Frog(int x, int y) {
        super(x, y, new Image("./sprites/frog.png"));
    }


    /**
     * Performs any actions done when an enemy dies
     * it then checks every adjacent tile and does the necessary action depending on the tile
     * @return nothing as it handles the conversions itself
     */
    @Override
    public void onDeath(Entity below) {
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
		if (gameEnd = true){
            gameEnd =false;
            Player.getPlayer().playerDeath();
        }
    }
	
	/**
     * used by the method called upon an enemy dying via a falling object this method sees if the tiles selected result in an outcome differing from the default 
	 *
     */
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
        } else if (check instanceof Player) {
            gameEnd = true;
            return true;
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
        Integer[] neighbour1 = {target.getY() + 1,target.getX()};
        Integer[] neighbour2 = {target.getY(),target.getX() +1};
        Integer[] neighbour3 = {target.getY() -1,target.getX()};
        Integer[] neighbour4 = {target.getY(),target.getX() -1};
        //Outer if statements make sure that each neighbour is an index in the graphed level state array
        //Inner if statements check that the neighbour is not null
        if (neighbour1[0] <= Game.getGame().MAX_HEIGHT_INDEX &&  neighbour1[1] <= Game.getGame().MAX_WIDTH_INDEX
                && neighbour1[0] >= 0 && neighbour1[1] >= 0){
            if (graphedLevelState[neighbour1[0]][neighbour1[1]] != null){
                output.add(graphedLevelState[neighbour1[0]][neighbour1[1]]);
            }
        }

        if (neighbour2[0] <= Game.getGame().MAX_HEIGHT_INDEX &&  neighbour2[1] <= Game.getGame().MAX_WIDTH_INDEX
                && neighbour2[0] >= 0 && neighbour2[1] >= 0){


            if (graphedLevelState[neighbour2[0]][neighbour2[1]] != null){
                output.add(graphedLevelState[neighbour2[0]][neighbour2[1]]);
            }

        }
        if (neighbour3[0] <= Game.getGame().MAX_HEIGHT_INDEX &&  neighbour3[1] <= Game.getGame().MAX_WIDTH_INDEX
                && neighbour3[0] >= 0 && neighbour3[1] >= 0){
            if (graphedLevelState[neighbour3[0]][neighbour3[1]] != null){
                output.add(graphedLevelState[neighbour3[0]][neighbour3[1]]);
            }
        }

        if (neighbour4[0] <= Game.getGame().MAX_HEIGHT_INDEX &&  neighbour4[1] <= Game.getGame().MAX_WIDTH_INDEX
                && neighbour4[0] >= 0 && neighbour4[1] >= 0){
            if (graphedLevelState[neighbour4[0]][neighbour4[1]] != null){
                output.add(graphedLevelState[neighbour4[0]][neighbour4[1]]);
            }

        }
        return output;
    }
    /**
     * Moves the frog to be called in main
     */
    public void move() {
        Game game = Game.getGame();
        Entity[][] currentLevelState = game.getMap();
        GraphNode[][] graphedLevelState = new GraphNode[game.MAX_HEIGHT_INDEX + 2][game.MAX_WIDTH_INDEX + 1];

        int x = 0;
        int y = 0;
        //Goes through everything that is not a path or player and changes it to null
        for (int i = 0; i < game.MAX_HEIGHT_INDEX * game.MAX_WIDTH_INDEX + 1; i++) {
            boolean reset = false;
            if (currentLevelState[y][x] instanceof Player) {
                graphedLevelState[y][x] = new GraphNode(true, y, x);

            } else if (currentLevelState[y][x] instanceof Path) {
                graphedLevelState[y][x] = new GraphNode(false, y, x);
            }

            if (x == game.MAX_WIDTH_INDEX) {
                y++;
                x = 0;
                reset = true;
            }
            if (!reset) {
                x++;
            }

        }
        //START OF PATHFINDING implementing bfs and then querying the position of the player
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(new GraphNode(false, getY(), getX()));
        queue.peek().setDistance(0);
        while (queue.size() > 0) {

            GraphNode currentNode = queue.poll();
            ArrayList<GraphNode> Neighbours = getNeighbours(currentNode,graphedLevelState);

            for (int i = 0; i < Neighbours.size(); i++) {
                if (Neighbours.get(i).getDistance() == -1){
                    Neighbours.get(i).setDistance(currentNode.getDistance() + 1);
                    Neighbours.get(i).setParent(currentNode);
                    queue.add(Neighbours.get(i));
                    graphedLevelState[Neighbours.get(i).getY()][Neighbours.get(i).getX()] = Neighbours.get(i);
                }
            }
        }
        //Checks if there is a route to the player or not

        boolean routeToPlayer = true;
        int[] playerCoordinates = new int[2];
        y = 0;
        x = 0;
        for (int i = 0; i < game.MAX_HEIGHT_INDEX  *  game.MAX_WIDTH_INDEX + 1; i++) {
            boolean reset = false;
            if (graphedLevelState[y][x] != null){
                if (graphedLevelState[y][x].isPlayer() && graphedLevelState[y][x].getDistance() == -1) {
                    routeToPlayer = false;
                }else if (graphedLevelState[y][x].isPlayer()){
                    playerCoordinates[0] = y;
                    playerCoordinates[1] = x;
                }

            }
            if (x == Game.MAX_WIDTH_INDEX) {
                y++;
                x = 0;
                reset = true;
            }
            if (!reset) {
                x++;
            }

        }
        //Decides where to move based on distance values
        if (routeToPlayer){
            GraphNode currentNode = graphedLevelState[playerCoordinates[0]][playerCoordinates[1]];
            for (int i = 0; i < graphedLevelState[playerCoordinates[0]][playerCoordinates[1]].getDistance() - 1; i++) {
                currentNode = currentNode.getParent();
            }
            game.updateLevel(currentNode.getX(),currentNode.getY(),currentLevelState[getY()][getX()]);

            if (currentNode.getY() == playerCoordinates[0] && currentNode.getX() == playerCoordinates[1]){
                Player.getPlayer().playerDeath();
            }

        }else if (!routeToPlayer){
            //Moves in random direction when player is not detected
            //randomises by using shuffling arraylist and then getting the first element in the arraylist
            graphedLevelState[getY()][getX()] = new GraphNode(false,getY(),getX());
            ArrayList<GraphNode> possibleMoveLocations = getNeighbours(graphedLevelState[getY()][getX()],graphedLevelState);
            Collections.shuffle(possibleMoveLocations);
            if (possibleMoveLocations.size() != 0){
                game.updateLevel(possibleMoveLocations.get(0).getX(),possibleMoveLocations.get(0).getY(),
                        currentLevelState[getY()][getX()]);

            }
        }


    }

}




