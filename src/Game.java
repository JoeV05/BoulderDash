import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.*;

/**
 * Stores and handles data about the overall game state. Keeps track
 * of all entities on the map and updates them when needed. Contains
 * validation for some processes.
 * @author James Harvey, Luke Brace, Joseph Vinson, Joe Devlin
 * @version 1.5
 */
public class Game {
    // constants for grid and cell sizes
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 23;
    // TODO - look into why this is - 2
    public static final int MAX_HEIGHT_INDEX = Game.GRID_HEIGHT - 2;
    public static final int MAX_WIDTH_INDEX = Game.GRID_WIDTH - 1;
    private static Game theGame;
    // the map of entities representing the game
    private Entity[][] map;
    //entities effected by gravity
    private ArrayList<FallingEntity> fallingEntities;
    //walls that need to be updated on tick
    private ArrayList<ActionWall> actionWalls;
    //all active enemies
    private ArrayList<Enemy> enemies;

    /**
     * Constructor for the Game class.
     */
    private Game() {
        fallingEntities = new ArrayList<>();
        actionWalls = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    /**
     * Adds an object that falls (i.e. boulder or diamond) to the falling
     * entities the game keeps track of.
     * @param entity Entity to add.
     */
    public void addFallingEntity(FallingEntity entity) {
        fallingEntities.add(entity);
    }

    /**
     * Removes an object from the falling entities the game keeps track of.
     * @param entity Entity to no longer keep track of.
     */
    public void removeFallingEntity(FallingEntity entity) {
        fallingEntities.remove(entity);
    }

    /**
     * Remove an enemy from the enemies the game keeps track of.
     * @param enemy Enemy to stop keeping track of.
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    /**
     * Checks if a move from a given (x, y) position is valid for
     * a given direction.
     * @param x The x coordinate to move from.
     * @param y The y coordinate to move from.
     * @param dir The direction to move in.
     * @return true or false.
     */
    public boolean isValidMove(int x, int y, Direction dir) {
        return switch (dir) {
            case UP -> validMoveUp(x, y);
            case DOWN -> validMoveDown(x, y);
            case LEFT -> validMoveLeft(x, y);
            case RIGHT -> validMoveRight(x, y);
            default -> throw new IllegalStateException("Invalid direction: "
                    + dir);
        };
    }

    /**
     * Check if moving up is allowed from a given position.
     * @param x The x coordinate of the position to move up from.
     * @param y The y coordinate of the position to move up from.
     * @return true or false.
     */
    private boolean validMoveUp(int x, int y) {
        int nY = y - 1;
        Entity target = getEntity(x, nY);
        return y > 0 && moveOnValidation(target);
    }

    /**
     * Check if moving down is allowed from a given position.
     * @param x The x coordinate of the position to move down from.
     * @param y THe y coordinate of the position to move down from.
     * @return true or false.
     */
    private boolean validMoveDown(int x, int y) {
        int nY = y + 1;
        Entity target = getEntity(x, nY);
        return y < (GRID_HEIGHT - 2) && moveOnValidation(target);
    }

    /**
     * Check if moving left is allowed from a given position.
     * @param x The x coordinate of the position to move left from.
     * @param y The y coordinate of the position move left from.
     * @return true or false.
     */
    private boolean validMoveLeft(int x, int y) {
        int nX = x - 1;
        Entity target = getEntity(nX, y);
        return x > 0 && (moveOnValidation(target)
                || pushValidation(target, Direction.LEFT));
    }

    /**
     * Check if moving right is allowed from a given position.
     * @param x The x coordinate of the position to move right from.
     * @param y The y coordinate of the position to move right from.
     * @return true or false.
     */
    private boolean validMoveRight(int x, int y) {
        int nX = x + 1;
        Entity target = getEntity(nX, y);
        return x < (GRID_WIDTH - 1) && (moveOnValidation(target)
                || pushValidation(target, Direction.RIGHT));
    }

    private boolean pushValidation(Entity target, Direction dir) {
        if (!(target instanceof Boulder)) {
            return false;
        }
        Boulder b = (Boulder) target;
        int dX;
        if (dir == Direction.RIGHT) {
            if (b.getX() > MAX_WIDTH_INDEX - 1) {
                return false;
            }
            dX = 1;
            return getEntity(b.getX() + dX, b.getY()) instanceof Path;
        } else {
            if (b.getX() < 1) {
                return false;
            }
            dX = -1;
            return getEntity(b.getX() + dX, b.getY()) instanceof Path;
        }
    }

    /**
     * Check if an entity is a diamond and if so whether it is not falling.
     * @param target Entity to check.
     * @return true or false.
     */
    private boolean canMoveOnDiamond(Entity target) {
        return target instanceof Diamond && !isFallingDiamond(target);
    }

    /**
     * Check if a move is valid given the object stored on the tile that
     * would be moved to.
     * @param target Entity that is on the target tile.
     * @return true or false.
     */
    private boolean moveOnValidation(Entity target) {
        return target instanceof Walkable
                || canMoveOnDiamond(target)
                || playerCanUnlockDoor(target)
                || (target instanceof Exit && ((Exit) target).walkable);
    }

    /**
     * Check if an entity is a falling diamond. Should only be called on
     * entities that are known to be diamonds.
     * @param entity Diamond to check the falling state of.
     * @return true or false.
     */
    private boolean isFallingDiamond(Entity entity) {
        Diamond diamond = (Diamond) entity;
        return diamond.isFalling();
    }

    /**
     * Check if the tile the player is trying to move to is a door, and if it is
     * then check if the player is able to unlock it.
     * @param target Entity to check against.
     * @return true or false.
     */
    private boolean playerCanUnlockDoor(Entity target) {
        if (!(target instanceof LockedDoor)) {
            return false;
        }
        LockedDoor door = (LockedDoor) target;
        for (int i = 0; i < Player.getPlayer().getKeys().size(); i++) {
            if (Player.getPlayer().getKeys().get(i).canUnlock(door)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loads a level from a text file and initialises the game state.
     * @throws FileNotFoundException Throw an error when the level file
     * isn't found.
     */
    public void loadingCave() throws FileNotFoundException {
        //initialise the cave from the specified file
        Cave charCave = new Cave();
        map = new Entity[charCave.getTilesTall()][charCave.getTilesWide()];
        //creates a map based on the caves dimensions
        charCave.printCave();
        char[][] reduceGets = charCave.getCave();
        //loops through the cave layout adding tiles to the map
        for (int row = 0; row < charCave.getTilesTall(); row++) {
            for (int col = 0; col < charCave.getTilesWide(); col++) {
                char tileChar = reduceGets[row][col];
                this.tileSwitch(tileChar, row, col);
            }
        }
    }

    /**
     * Determines what kind of entity should be in a given location from a
     * given character, then adds that entity to the map at the given
     * location.
     * @param tileChar Character representing what type of entity to place.
     * @param y The y coordinate the entity should be placed at.
     * @param x The x coordinate the entity should be placed at.
     */
    private void tileSwitch(char tileChar, int y, int x) {
        switch (tileChar) {
            case '#':
                map[y][x] = new Wall(x, y, WallType.NORMAL_WALL);
                break;
            case 'T':
                map[y][x] = new Wall(x, y, WallType.TITANIUM_WALL);
                break;
            case 'M':
                MagicWall m = new MagicWall(x, y);
                addToOnCreate(m);
                break;
            case 'E':
                Exit e = new Exit(x, y, 5);
                addToOnCreate(e);
                break;
            // TODO - metadata needed for unlock exit condition
            case 'R':
                map[y][x] = new LockedDoor(x, y, Colour.RED);
                break;
            case 'G':
                map[y][x] = new LockedDoor(x, y, Colour.GREEN);
                break;
            case 'B':
                map[y][x] = new LockedDoor(x, y, Colour.BLUE);
                break;
            case 'Y':
                map[y][x] = new LockedDoor(x, y, Colour.YELLOW);
                break;
            case 'r':
                map[y][x] = new Key(x, y, Colour.RED);
                break;
            case 'g':
                map[y][x] = new Key(x, y, Colour.GREEN);
                break;
            case 'b':
                map[y][x] = new Key(x, y, Colour.BLUE);
                break;
            case 'y':
                map[y][x] = new Key(x, y, Colour.YELLOW);
                break;
            case 'O':
                Boulder b = new Boulder(x, y);
                addToOnCreate(b);
                break;
            case 'V':
                Diamond d = new Diamond(x, y);
                addToOnCreate(d);
                break;
            case 'W':
                map[y][x] = new Butterfly(x, y);
                break;
                // TODO - read left/right from level file
            case 'X':
                map[y][x] = new Firefly(x, y);
                break;
            // TODO - metadata needed for left/right wall cling
            case 'F':
                map[y][x] = new Frog(x, y);
                break;
            case 'A':
                map[y][x] = new Amoeba(x, y, 10);
                break;
            case 'P':
                map[y][x] = Player.getPlayer(x, y);
                Player.getPlayer().manualSwitchView(x, y);
                break;
            // TODO - metadata needed for maximum Amoeba size
            case 'D':
                map[y][x] = new Dirt(x, y);
                break;
            default:
                map[y][x] = new Path(x, y);
                break;
        }
    }

    /**
     * Add an entity to the map and the appropriate list for the game to keep
     * track of and update the entity on each tick.
     * @param entity Entity to add.
     */
    private void addToOnCreate(Entity entity) {
        map[entity.getY()][entity.getX()] = entity;
        if (entity instanceof ActionWall) {
            if (entity instanceof Exit) {
                actionWalls.add((Exit) entity);
            } else {
                actionWalls.add((MagicWall) entity);
            }
        } else if (entity instanceof FallingEntity) {
            if (entity instanceof Diamond) {
                fallingEntities.add((Diamond) entity);
            } else {
                fallingEntities.add((Boulder) entity);
            }
        } else if (entity instanceof Enemy) {
            if (entity instanceof Frog) {
                enemies.add((Frog) entity);
            } else if (entity instanceof Butterfly) {
                enemies.add((Butterfly) entity);
            } else {
                enemies.add((Firefly) entity);
            }
        }
    }

    /**
     * Updates the position of an entity on the game map
     * replacing the previous position with a path tile.
     * Used for movement.
     * @param newX The x coordinate to move the entity to.
     * @param newY The y coordinate to move the entity to.
     * @param entity The entity to move.
     */
    public void updateLevel(int newX, int newY, Entity entity) {
        int oldX = entity.getX();
        int oldY = entity.getY();
        if (getEntity(newX, newY) instanceof Exit) {
            try {
                nextLevel();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            replaceEntity(newX, newY, entity);
            entity.setX(newX);
            entity.setY(newY);
            replaceEntity(oldX, oldY, new Path(oldX, oldY));
        }

    }

    /**
     * Replace the entity at (x, y) in the map with a given entity.
     * @param x The x coordinate of the entity to replace.
     * @param y The y coordinate of the entity to replace.
     * @param entity The entity to place on the map.
     */
    public void replaceEntity(int x, int y, Entity entity) {
        map[y][x] = entity;
    }

    /**
     * Returns the entity at the given (x, y) coordinates.
     * @param x The x coordinate of the map to get the entity from.
     * @param y The y coordinate of the map to get the entity from.
     * @return Any object of type Entity.
     */
    public Entity getEntity(int x, int y) {
        return map[y][x];
    }

    /**
     * Loads the next level into the game.
     * @throws FileNotFoundException Throws an error if there is no level
     * left to load, i.e. the player has reached the final level and tries
     * to exit to the next level.
     */
    public void nextLevel() throws FileNotFoundException {
        actionWalls.clear();
        fallingEntities.clear();
        enemies.clear();
        loadingCave();
    }

    /**
     * This method is called periodically by the tick timeline
     * and would for, example move, perform logic in the game,
     * this might cause the bad guys to move (by e.g., looping
     * over them all and calling their own tick method).
     */
    public void tick() {
        for (int i = 0; i < actionWalls.size(); i++) {
            actionWalls.get(i).tick();
        }

        for (int i = 0; i < fallingEntities.size(); i++) {
            fallingEntities.get(i).tick();
        }

        for (int i = 0; i < enemies.size(); i++) {
            //enemies.get(i).???
            // TODO - Use this for enemy update on tick, #
            //  e.g. enemies.get(i).move() (preferably enemies.get(i).tick()
            //  but it's up to hazards people)
        }
    }

    /**
     * Retrieves the map.
     * @return 2D array of Entity objects.
     */
    public Entity[][] getMap() {
        return map;
    }

    /**
     * Check if an entity is considered round. Used to determine if a falling
     * entity should roll when it is above this tile.
     * @param entity Entity to check the round status of.
     * @return true or false.
     */
    public static boolean isRound(Entity entity) {
        return (entity instanceof FallingEntity)
                || (entity instanceof Wall && !(entity instanceof MagicWall));
    }

    /**
     * Retrieve the instance of the game.
     * @return Game object.
     */
    public static Game getGame() {
        if (theGame == null) {
            theGame = new Game();
        }
        return theGame;
    }

    /**
     * Saves the current state of the game to a file.
     * The save file includes the current cave number, player position,
     * number of diamonds collected, and the number of keys the player has.
     *
     * @author Tafara Gonese
     * @param filename The name of the file to save the game state to.
     */
    public void saveGame(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Save the current cave number
            writer.println("CaveNumber:" + Cave.getCaveNumber());
            // Save the player's position on the grid
            writer.println("PlayerPosition:" + Player.getPlayer().getX() + "," + Player.getPlayer().getY());
            // Save the number of diamonds the player has collected
            writer.println("Diamonds:" + Player.getPlayer().getDiamonds());
            // Save the number of keys the player has
            writer.println("Keys:" + Player.getPlayer().getKeys().size());
        } catch (IOException e) {
            // Print the stack trace for debugging if saving fails
            e.printStackTrace();
        }
    }

    /**
     * Loads the game state from a file and reinitializes the game to
     * the saved state. This includes loading the current cave number,
     * player's position, diamonds collected, and keys.
     *
     * The method reads the file line by line using a BufferedReader
     * and parses the data to restore the game state.
     *
     * @author Tafara Gonese
     * @param filename The name of the file to load the game state from.
     */
    public void loadGame(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read the save file line by line
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into a key-value pair based on the colon delimiter
                String[] parts = line.split(":");
                switch (parts[0]) {
                    case "CaveNumber":
                        // Set the cave number using the parsed integer
                        Cave.setCaveNumber(Integer.parseInt(parts[1])); // Add a setter to Cave.java
                        break;
                    case "PlayerPosition":
                        // Parse the player's x and y position
                        String[] position = parts[1].split(",");
                        Player.getPlayer().setX(Integer.parseInt(position[0]));
                        Player.getPlayer().setY(Integer.parseInt(position[1]));
                        break;
                    case "Diamonds":
                        // Set the number of diamonds the player has
                        Player.getPlayer().setDiamonds(Integer.parseInt(parts[1]));
                        break;
                    case "Keys":
                        // Clear the player's keys and add the specified number of default keys
                        int numKeys = Integer.parseInt(parts[1]);
                        Player.getPlayer().getKeys().clear();
                        for (int i = 0; i < numKeys; i++) {
                            Player.getPlayer().getKeys().add(new Key(0, 0, Colour.RED)); // Example default key
                        }
                        break;
                }
            }
            // After parsing the file, reload the current cave
            loadCave();
        } catch (IOException e) {
            // Print the stack trace for debugging if loading fails
            e.printStackTrace();
        }
    }

    /**
     * Loads the cave layout corresponding to the current cave number.
     * Initializes the game map based on the cave's tiles.
     * The cave layout is parsed from the appropriate level file.
     *
     * @author Tafara Gonese
     */
    public void loadCave() {
        try {
            // Create a new Cave instance based on the current cave number
            Cave cave = new Cave();
            // Initialize the game map with dimensions from the cave
            map = new Entity[cave.getTilesTall()][cave.getTilesWide()];
            // Retrieve the cave layout as a 2D array of characters
            char[][] caveLayout = cave.getCave();
            // Populate the map based on the characters in the layout
            for (int y = 0; y < caveLayout.length; y++) {
                for (int x = 0; x < caveLayout[0].length; x++) {
                    tileSwitch(caveLayout[y][x], y, x);
                }
            }
        } catch (FileNotFoundException e) {
            // Print the stack trace for debugging if the cave file is missing
            e.printStackTrace();
        }
    }
}
