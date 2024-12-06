import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Diamond extends FallingEntity {

    public Diamond(int x, int y) {
        super(x, y, new Image("sprites/diamond_Blue.png"));
        setSprite(randomSprite());
    }

    // TODO - proper method comment
    //Diamonds can have 1 of 4 variations, chosen at random, does not impact behaviour, only image
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
