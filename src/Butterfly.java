import javafx.scene.image.Image;

public class Butterfly extends Firefly {

    // TODO - maybe remove comment?
    /**
     * pending image
     *
     * @return Image of butterfly
     */

    public Butterfly(int x , int y) {
        super(x, y, new Image("./sprites/butterfly.png"));
    }

    /**
     * A Test method designed to be used inside moveTo to test your movement is working correctly
     */
    @Override
    public void movementTests() {

    }

    
    
    /**
     * performs actions done when enemies die by falling objects for most this will replace a 3 by 3 area with path excluding exeption objects of titanium walls and the exit
     *  butterfly will differ by replacing the path with diamonds instead
     */

    @Override
    public void onDeathByFallingObject(Entity below) {
        int positionX = below.getX();
        int positionY= below.getY();
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(positionX, positionY);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY= positionY+1;
        }else{
            positionY= positionY+1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX= positionX-1;
        }else{
            positionX= positionX-1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX= positionX-1;
        }else{
            positionX= positionX-1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY= positionY-1;
        }else{
            positionY= positionY-1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY= positionY-1;
        }else{
            positionY= positionY-1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
        if(checker(positionX, positionY) == true){
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX= positionX+1;
        }else{
            positionX= positionX+1;
        }
    }
	
	/**
     * used by the method called upon an enemy dying via a falling object this method sees if the tiles selected result in an outcome differing from the default 
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
        } else {
            return true;
        }
}
