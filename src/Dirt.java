import javafx.scene.image.Image;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Dirt extends Walkable {

    public Dirt(int x, int y) {
        super(x, y, new Image("./sprites/Dirt.png"));
    }
}
