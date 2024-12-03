import javafx.scene.image.Image;

// TODO - proper class comment

/**
 * @author Joseph Vinson
 */

public class Path extends Walkable{

    public Path(int x, int y) {
        super(x, y, new Image("./sprites/path.png"));
    }
}
