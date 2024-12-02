import javax.swing.*;

public abstract class ActionWall extends Wall {
    public ActionWall(int x, int y, WallType wallType) {
        super(x, y, wallType);
    }

    public abstract void tick();
}
