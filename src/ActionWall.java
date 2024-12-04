// TODO - javadoc class comment

public abstract class ActionWall extends Wall {
    public ActionWall(int x, int y, WallType wallType) {
        super(x, y, wallType);
    }

    // TODO - javadoc method comment
    public abstract void tick();
}
