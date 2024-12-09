import javafx.scene.image.Image;

/**
 * Represents a butterfly enemy in the game.
 * @author Daniel Beesley
 */
public class Firefly extends Enemy {
    private boolean gameEnd;
    private Direction cling;
    private Direction currentMovementDir;

    /**
     * Creates a firefly at a given (x, y) position.
     * @param x The x coordinate of the firefly.
     * @param y The y coordinate of the firefly.
     * @param cling Left or right clinging.
     */
    public Firefly(int x, int y, Direction cling) {
        super(x, y, new Image("./sprites/firefly.png"));
        this.gameEnd = false;
        this.cling = cling;
        this.currentMovementDir = cling;
    }

    /**
     * Create a firefly at a given (x, y) position with a given sprite.
     * @param x The x coordinate of the firefly.
     * @param y The y coordinate of the firefly.
     * @param image The image to use as the sprite of the firefly.
     * @param cling Left or right clinging.
     */
    public Firefly(int x, int y, Image image, Direction cling) {
        super(x, y, image);
        this.cling = cling;
        this.currentMovementDir = cling;
    }

    /**
     *
     */
    public void move() {
        if (this.cling == Direction.RIGHT) {
            this.moveClingRight();
            return;
        }
        this.moveClingLeft();
    }

    /**
     * Move while clinging to the left.
     */
    public void moveClingLeft() {
        Entity north = Game.getGame().getEntity(x, y - 1);
        Entity east = Game.getGame().getEntity(x + 1, y);
        Entity south = Game.getGame().getEntity(x, y + 1);
        Entity west = Game.getGame().getEntity(x - 1, y);
        switch (currentMovementDir) {
            case Direction.RIGHT:
                if (north instanceof Path) {
                    Game.getGame().updateLevel(x, y - 1, this);
                    currentMovementDir = Direction.DOWN;
                } else if (east instanceof Path) {
                    Game.getGame().updateLevel(x + 1, y, this);
                } else if (south instanceof Path) {
                    Game.getGame().updateLevel(x, y + 1, this);
                    currentMovementDir = Direction.UP;
                } else if (west instanceof Path) {
                    Game.getGame().updateLevel(x - 1, y, this);
                    currentMovementDir = Direction.LEFT;
                }
                break;
            case Direction.UP:
                if (east instanceof Path) {
                    Game.getGame().updateLevel(x + 1, y, this);
                    currentMovementDir = Direction.RIGHT;
                } else if (south instanceof Path) {
                    Game.getGame().updateLevel(x, y + 1, this);
                    currentMovementDir = Direction.UP;
                } else if (west instanceof Path) {
                    Game.getGame().updateLevel(x - 1, y, this);
                    currentMovementDir = Direction.LEFT;
                } else if (north instanceof Path) {
                    Game.getGame().updateLevel(x, y - 1, this);
                    currentMovementDir = Direction.DOWN;
                }
                break;
            case Direction.LEFT:
                if (south instanceof Path) {
                    Game.getGame().updateLevel(x, y + 1, this);
                    currentMovementDir = Direction.UP;
                } else if (west instanceof Path) {
                    Game.getGame().updateLevel(x - 1, y, this);
                    currentMovementDir = Direction.LEFT;
                } else if (north instanceof Path) {
                    Game.getGame().updateLevel(x, y - 1, this);
                    currentMovementDir = Direction.DOWN;
                } else if (east instanceof Path) {
                    Game.getGame().updateLevel(x + 1, y, this);
                    currentMovementDir = Direction.RIGHT;
                }
                break;
            case Direction.DOWN:
                if (west instanceof Path) {
                    Game.getGame().updateLevel(x - 1, y, this);
                    currentMovementDir = Direction.LEFT;
                } else if (north instanceof Path) {
                    Game.getGame().updateLevel(x, y - 1, this);
                    currentMovementDir = Direction.DOWN;
                } else if (east instanceof Path) {
                    Game.getGame().updateLevel(x + 1, y, this);
                    currentMovementDir = Direction.RIGHT;
                } else if (south instanceof Path) {
                    Game.getGame().updateLevel(x, y + 1, this);
                    currentMovementDir = Direction.UP;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Move while clinging to the right.
     */
    public void moveClingRight() {
    }

    /**
     * Performs any actions done when an enemy dies by a falling object and
     * returns what they should drop on their death. Then checks every adjacent
     * tile and does the necessary action depending on the tile.
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
       if (gameEnd == true) {
            gameEnd = false;
            Player.getPlayer().playerDeath();
        }
    }

    /**
     * Used by the method called upon an enemy dying via a falling object this
     * method sees if the tiles selected result in an outcome differing from
     * the default.
     * @param x X coordinate of the entity to check.
     * @param y Y coordinate of the entity to check.
     * @return true or false.
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

}
