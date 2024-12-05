import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
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
    public int onDeathByFallingObject(Entity below) {
        int positionX = below.getX();
        int positionY= below.getY();
        if(checker(positionX, positionY) == true){
            Game.getGame().updateLevel(positionX, positionY, below);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY= positionY+1;
        }else{
            positionY= positionY+1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX= positionX-1;
        }else{
            positionX= positionX-1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX= positionX-1;
        }else{
            positionX= positionX-1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY= positionY-1;
        }else{
            positionY= positionY-1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY= positionY-1;
        }else{
            positionY= positionY-1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        if(checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        return 0;
    }

    @Override
    public boolean checker(int x, int y) {
        Entity check = Game.getGame().getEntity(x, y);
        if (check instanceof Exit ){
            return false;
        } else if(check instanceof Wall){
            WallType notUnbreakable =((Wall)check).getWallType();
            if (notUnbreakable == WallType.TITANIUM_WALL){
                return false;
            }else{
                return true;
            }  
        } else if (check instanceof Butterfly){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(x, y, dropedDiamond);
            return false;
        } else{
            return true;
        }
    }

    /**
     * Gets the neighbours of the target in a given Entity array
     * @param targetIndex int[] - Index of the element you want the neighbours of
     * @param currentGameState Entity[][] -Array in which target is contained
     * @return Arraylist<Integer[]> list of neighbours
     */
    //private ArrayList<Integer[]> Neighbours(int[] targetIndex,Entity[][] currentGameState);
    /**
     *Moves the frog to be called in main
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
            if (currentLevelState[y][x] instanceof Player){
                graphedLevelState[y][x] = new GraphNode(true,y,x);
            } else if (currentLevelState[y][x] instanceof  Path) {
                graphedLevelState[y][x] = new GraphNode(false,y,x);
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
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] startingLocation = {getX(),getY()};
        queue.add(startingLocation);
        ArrayList<Integer[]> visited = new ArrayList<>();
        while (queue.size() > 0){
            visited.add(queue.poll());

        }




    }

}


