import javafx.scene.image.Image;

import java.util.Arrays;

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
    public int onDeathByFallingObject() {
        return 0;
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
        //START OF PATHFINDING

    }

}


