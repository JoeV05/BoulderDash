import java.io.FileNotFoundException;
import java.util.*;

// TODO - Redo the javadoc comment

/**
 *Represents the main game logic and state management for the Boulder Game
 *This class initialises the game window, receives user input and
 *handles the rendering and updating of game state
 *The game a grid based level system with entities modelled on
 *top of it using javafx for rendering
 *
 * @author James Harvey, Luke Brace, Joseph Vinson, Joe Devlin
 * Represents the game state, stores level data and renders game window
 */
public class Game {
    private static Game theGame;

    // constants for grid and cell sizes
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 23;

    // the map of entities representing the game
    private static Entity[][] map;

    //list of entities requiring special logic
    private static ArrayList<FallingEntity> fallingEntities;//entities effected by gravity
    private static ArrayList<ActionWall> actionWalls;//special attribute walls

    private Game() {
        fallingEntities = new ArrayList<>();
        actionWalls = new ArrayList<>();
    }

    public static void addFallingEntity(FallingEntity e) {
        fallingEntities.add(e);//Adds a falling entity to the list of entities effected by gravity
    }

    public static void removeFallingEntity(FallingEntity e) {
        //removes a falling entity from the list
        fallingEntities.remove(e);
    }

    // TODO - Check tile where they are trying to move, maybe split method up
    //Validates whether a move is allowed based on the current grid
    //x = the current x coordinate of the entity
    //y = the current y coordinate of the entity
    //dir = the intended direction of the move
    // return true if the move is valid
    public static boolean isValidMove(int x, int y, Direction dir) {
        switch (dir) {
            case UP:
                return y > 0 && (Game.getEntity(x, y - 1) instanceof Walkable);
            case DOWN:
                // TODO - WHY DOES THIS NEED TO BE -2 INSTEAD OF -1
                //  WHAT THE FISH!!!
                return y < (GRID_HEIGHT - 2)  && (Game.getEntity(x, y + 1) instanceof Walkable);
            case LEFT:
                return x > 0  && (Game.getEntity(x - 1, y) instanceof Walkable);
            case RIGHT:
                return x < (GRID_WIDTH - 1)  && (Game.getEntity(x + 1, y) instanceof Walkable);
        }
        throw new LiamWetFishException("WHAT THE FISH DID YOU DO TO GET HERE");
    }

    public static void loadingCave() throws FileNotFoundException {
        Cave charCave = new Cave("Cave1", "level-1.txt");
        //TODO: Add automatic cave generation, see cave class
        map = new Entity[charCave.getTilesTall()][charCave.getTilesWide()];
        charCave.printCave();
        char[][] reduceGets = charCave.getCave();

        for (int row = 0; row < charCave.getTilesTall(); row++) {
            for (int col = 0; col < charCave.getTilesWide(); col++) {
                char tileChar = reduceGets[row][col];

                switch(tileChar) {
                    case '#':
                        map[row][col] = new Wall(col, row, WallType.NORMAL_WALL);
                        break;
                    case 'T':
                        map[row][col] = new Wall(col, row, WallType.TITANIUM_WALL);
                        break;
                    case 'M':
                        MagicWall m = new MagicWall(col, row);
                        map[row][col] = m;
                        actionWalls.add(m);
                        break;
                    case 'E':
                        Exit e = new Exit(col, row, 5);
                        map[row][col] = e;
                        actionWalls.add(e);
                        break;
                    // TODO - metadata needed for unlock exit condition
                    case 'R':
                        map[row][col] = new LockedDoor(col, row, Colour.RED);
                        break;
                    case 'G':
                        map[row][col] = new LockedDoor(col, row, Colour.GREEN);
                        break;
                    case 'B':
                        map[row][col] = new LockedDoor(col, row, Colour.BLUE);
                        break;
                    case 'Y':
                        map[row][col] = new LockedDoor(col, row, Colour.YELLOW);
                        break;
                    case 'r':
                        map[row][col] = new Key(col, row, Colour.RED);
                        break;
                    case 'g':
                        map[row][col] = new Key(col, row, Colour.GREEN);
                        break;
                    case 'b':
                        map[row][col] = new Key(col, row, Colour.BLUE);
                        break;
                    case 'y':
                        map[row][col] = new Key(col, row, Colour.YELLOW);
                        break;
                    case 'O':
                        Boulder b = new Boulder(col, row);
                        map[row][col] = b;
                        fallingEntities.add(b);
                        break;
                    case 'V':
                        Diamond d = new Diamond(col, row);
                        map[row][col] = d;
                        fallingEntities.add(d);
                        break;
                    case 'W':
                        map[row][col] = new Butterfly(col, row);
                        break;
                    case 'X':
                        map[row][col] = new Firefly(col, row);
                        break;
                    // TODO - metadata needed for left/right wall cling
                    case 'F':
                        map[row][col] = new Frog(col, row);
                        break;
                    case 'A':
                        map[row][col] = new Amoeba(col, row, 10);
                        break;
                    case 'P':
                        map[row][col] = Player.getPlayer(col, row);
                        break;
                    // TODO - metadata needed for maximum Amoeba size
                    case 'D':
                        map[row][col] = new Dirt(col, row);
                        break;
                    default:
                        map[row][col] = new Path(col, row);
                        break;
                }
            }
        }
    }

    /**
     * Change the position of an Entity in the levelState
     */
    public static void updateLevel(int newX, int newY, Entity entity) {
        int oldX = entity.getX();
        int oldY = entity.getY();
        replaceEntity(newX, newY, entity);
        entity.setX(newX);
        entity.setY(newY);
        replaceEntity(oldX, oldY, new Path(oldX, oldY));
    }

    /**
     * Changes the entity type in the levelState at x,y
     * @param x new x position to be moved to
     * @param y new y position to be moved to
     * @param entity to be replaced with
     */
    public static void replaceEntity(int x, int y, Entity entity) {
        map[y][x] = entity;
    }

    /**
     * Returns the entity at the given x/y coordinates
     * @return Entity to be moved
     */
    public static Entity getEntity(int x, int y) {
        return map[y][x];
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick() {
        for (ActionWall actionWall : actionWalls) {
            actionWall.tick();
        }

        for (FallingEntity fallingEntity : fallingEntities) {
            fallingEntity.fall();
        }
    }

    /**
     * Getter for map
     * @return Entity [][] map
     */
    public Entity[][] getMap(){
        return map;
    }

    public static Game getGame() {
        if (theGame == null) {
            theGame = new Game();
        }
        return theGame;
    }
}
