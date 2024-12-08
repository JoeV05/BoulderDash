import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a connected group of amoeba in the game. Grows at a certain
 * rate (i.e. every n ticks, where n is some integer provided in the
 * constructor).
 * @author Joseph Vinson
 * @version 1.3
 */
public class AmoebaGroup {
    private ArrayList<Amoeba> amoeba;
    private int maximumSize;
    private int growthRate;
    private int tickCount;

    /**
     * Creates an amoeba group, with an initial amoeba at the given (x, y)
     * coordinates.
     * @param maximumSize Maximum size the group can reach before dying.
     * @param growthRate Rate at which the amoeba should spread.
     * @param x The x coordinate for the initial amoeba.
     * @param y The y coordinate for the initial amoeba.
     */
    public AmoebaGroup(int maximumSize, int growthRate, int x, int y) {
        this.maximumSize = maximumSize;
        this.growthRate = growthRate;
        this.amoeba = new ArrayList<>();
        Amoeba initialAmoeba = new Amoeba(x, y);
        this.amoeba.add(initialAmoeba);
        this.tickCount = 0;
    }

    /**
     * Get the starting amoeba of the group.
     * @return Amoeba object
     */
    public Amoeba getFirst() {
        return amoeba.get(0);
    }

    /**
     * Update the amoeba group on each tick. Checks if the amoeba group needs
     * to update, then checks if the group needs to be transformed into
     * boulders. If the group is not transformed it will attempt to grow, if
     * the group cannot grow it is transformed into diamonds.
     */
    public void tick() {
        if (this.tickCount != growthRate) {
            this.tickCount++;
            return;
        }
        //Reset tick count
        this.tickCount = 0;
        //Check if the group has reached maximum size
        if (this.amoeba.size() == maximumSize) {
            transformToBoulders();
            return;
        }
        //Find which amoeba in the group can grow
        ArrayList<Amoeba> growable = new ArrayList<>();
        for (int i = 0; i < amoeba.size(); i++) {
            if (amoeba.get(i).canGrow()) {
                growable.add(amoeba.get(i));
            }
        }
        if (growable.isEmpty()) {
            transformToDiamonds();
            return;
        }
        //Random choice of amoeba to grow
        Random rand = new Random();
        int growIndex = rand.nextInt(growable.size());
        Amoeba grower = amoeba.get(growIndex);
        //Random choice of which direction to grow in
        Random r = new Random();
        ArrayList<Direction> growDirs = grower.growthDirections();
        if (growDirs.isEmpty()) {
            return;
        }
        int randIndex = r.nextInt(growDirs.size());
        Direction randDir = growDirs.get(randIndex);
        //Create grown amoeba
        Amoeba grown = grower.grow(randDir);
        amoeba.add(grown);
    }

    /**
     * Transform the amoeba in this group into diamonds. Goes through each
     * amoeba in the group, replaces it with a diamond, then removes the group
     * from the game.
     */
    private void transformToDiamonds() {
        for (int i = 0; i < amoeba.size(); i++) {
            Amoeba a = amoeba.get(i);
            int aX = a.getX();
            int aY = a.getY();
            Diamond d = new Diamond(aX, aY);
            Game.getGame().replaceEntity(aX, aY,d);
            Game.getGame().addFallingEntity(d);
        }
        Game.getGame().removeAmoebaGroup(this);
    }

    /**
     * Transform the amoeba in this group into boulders. Goes through each
     * amoeba in the group, replaces it with a boulder, then removes the group
     * from the game.
     */
    private void transformToBoulders() {
        for (int i = 0; i < amoeba.size(); i++) {
            Amoeba a = amoeba.get(i);
            int aX = a.getX();
            int aY = a.getY();
            Boulder b = new Boulder(aX, aY);
            Game.getGame().replaceEntity(aX, aY, b);
            Game.getGame().addFallingEntity(b);
        }
        Game.getGame().removeAmoebaGroup(this);
    }
}
