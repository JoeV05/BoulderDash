import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Represents a diamond in the game.
 * @author Joseph Vinson
 * @version 1.1
 */

public class Diamond extends FallingEntity {
    /**
     * Creates a new diamond at the given (x, y) coordinates with a sprite
     * selected randomly from a list of available sprites.
     * @param x The x coordinate of the diamond.
     * @param y The y coordinate of the diamond,
     */
    public Diamond(int x, int y) {
        super(x, y, new Image("sprites/diamond_Blue.png"));
        setSprite(randomSprite());
    }

    /**
     * Randomly selects a sprite for the diamond to use.
     * @return An Image object containing one of the available sprites.
     */
    private Image randomSprite() {
        List<Image> diamonds = new ArrayList<>();
        diamonds.add(new Image("./sprites/diamond_Blue.png"));
        diamonds.add(new Image("./sprites/diamond_Red.png"));
        diamonds.add(new Image("./sprites/diamond_Green.png"));
        diamonds.add(new Image("./sprites/diamond_White.png"));
        Random rand = new Random();
        return diamonds.get(rand.nextInt(diamonds.size()));
    }
}
