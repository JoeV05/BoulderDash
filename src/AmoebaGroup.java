import java.util.ArrayList;
import java.util.Random;

public class AmoebaGroup {
    private ArrayList<Amoeba> amoeba;
    private int maximumSize;
    private int growthRate;
    private int tickCount;

    public AmoebaGroup(int maximumSize, int growthRate, int x, int y) {
        this.maximumSize = maximumSize;
        this.growthRate = growthRate;
        this.amoeba = new ArrayList<>();
        Amoeba initialAmoeba = new Amoeba(x, y);
        this.amoeba.add(initialAmoeba);
        this.tickCount = 0;
    }

    public Amoeba getFirst() {
        return amoeba.get(0);
    }

    public void tick() {
        if (this.tickCount != growthRate) {
            this.tickCount++;
            return;
        }
        
        this.tickCount = 0;

        if (this.amoeba.size() == maximumSize) {
            System.out.println("Maximum size reached");
            transformToBoulders();
            return;
        }

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

        Random rand = new Random();
        int growIndex = rand.nextInt(growable.size());
        Amoeba grower = amoeba.get(growIndex);
        System.out.println("Growing " + growIndex);
        Random r = new Random();
        ArrayList<Direction> growDirs = grower.growthDirections();

        if (growDirs.isEmpty()) {
            return;
        }

        Direction randDir = growDirs.get(r.nextInt(growDirs.size()));

        Amoeba grown = grower.grow(randDir);
        amoeba.add(grown);
    }

    public void transformToDiamonds() {
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

    public void transformToBoulders() {
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
