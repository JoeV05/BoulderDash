import javafx.scene.image.Image;

public class Butterfly extends Firefly {

    // TODO - maybe remove comment?

    /**
     * pending image
     *
     * @return Image of butterfly
     */

    public Butterfly(int x, int y) {
        super(x, y, new Image("./sprites/butterfly.png"));
    }

    /**
     * A Test method designed to be used inside moveTo to test your movement is working correctly
     */
    @Override
    public void movementTests() {

    }


    /**
     * Performs any actions done when an enemy dies by a falling object and returns what they should drop on their death
     * it then checks every adjacent tile and does the nessicary action depending on the tile
     * @return nothing as it handles the conversions itself
     * butterfly will differ by replacing the path with diamonds instead
     */

    @Override
    public void onDeath(Entity below) {
        int positionX = below.getX();
        int positionY = below.getY();
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(positionX, positionY);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX = positionX + 1;
        } else {
            positionX = positionX + 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY = positionY + 1;
        } else {
            positionY = positionY + 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX = positionX - 1;
        } else {
            positionX = positionX - 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX = positionX - 1;
        } else {
            positionX = positionX - 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY = positionY - 1;
        } else {
            positionY = positionY - 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionY = positionY - 1;
        } else {
            positionY = positionY - 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
            positionX = positionX + 1;
        } else {
            positionX = positionX + 1;
        }
        if (checker(positionX, positionY) == true) {
            Diamond dropedDiamond = new Diamond(x, y);
            Game.getGame().replaceEntity(positionX, positionY, dropedDiamond);
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
        } else if (check instanceof Player) {
            gameEnd = true;
            return true;
        } else {
            return true;
        }
    }
}
