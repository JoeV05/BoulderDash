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
     *
     */
    @Override
    public void move() {
        Game game = Game.getGame();
        Entity[][] currentLevelState = game.getMap();
        int x = 0;
        int y = 0;
        //Goes through everything that is not a path or player and changes it to null
        for (int i = 0; i < currentLevelState.length * currentLevelState[0].length; i++) {
            System.out.println("Resetting");
            boolean reset = false;
            if (!(currentLevelState[y][x] instanceof Player) && !(currentLevelState[y][x] instanceof Path)){
                currentLevelState[y][x] = null;
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
        Entity startLocation = currentLevelState[getY()][getX()];
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] startingLocation = {getX(),getY()};
        queue.add(startingLocation);
        ArrayList<Integer[]> visited = new ArrayList<>();
        while (queue.size() > 0){
            visited.add(queue.poll());
        }




    }

}


