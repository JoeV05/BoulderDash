import javafx.scene.image.Image;

/**
 * Represents a butterfly enemy in the game.
 * @author Daniel Beesley
 */
public class Firefly extends Enemy {
    private boolean gameEnd;

    /**
     * Creates a firefly at a given (x, y) position.
     * @param x The x coordinate of the firefly.
     * @param y The y coordinate of the firefly.
     */
    public Firefly(int x, int y) {
        super(x, y, new Image("./sprites/firefly.png"));
        this.gameEnd = false;
    }

    /**
     * Create a firefly at a given (x, y) position with a given sprite.
     * @param x The x coordinate of the firefly.
     * @param y The y coordinate of the firefly.
     * @param image The image to use as the sprite of the firefly.
     */
    protected Firefly(int x, int y, Image image) {
        super(x, y, image);
    }

    /**
     *
     */
    public void move() {

    }

    /**
     * No clue what this does.
     */
    @Override
    public void movementTests() {

    }

    /**
     * Performs any actions done when an enemy dies by a falling object and returns what they should drop on their death
     * it then checks every adjacent tile and does the nessicary action depending on the tile
     * @return nothing as it handles the conversions itself
     */
    @Override
    public void onDeath(Entity below) {
        int positionX = below.getX();
        int positionY= below.getY();
        if(checker(positionX, positionY) == true){
            Game.getGame().updateLevel(positionX, positionY, below);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        if (checker(positionX, positionY) == true){
            Entity replaced =Game.getGame().getEntity(positionX, positionY);
            Game.getGame().updateLevel(positionX, positionY, replaced);
            positionY= positionY+1;
        } else {
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
       if (gameEnd = true){
            gameEnd =false;
            Player.getPlayer().playerDeath();
        }
    }
	
	/**
     * Used by the method called upon an enemy dying via a falling object this
     * method sees if the tiles selected result in an outcome differing from
     * the default.
     */
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
        } else if (check instanceof Player) {
            gameEnd = true;
            return true;
        } else {
            return true;
        }
    }
    
}
