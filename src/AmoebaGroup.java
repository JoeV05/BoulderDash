import java.util.ArrayList;
import java.util.Random;

public class AmoebaGroup {
    private ArrayList<Amoeba> amoebaList;
    private int maximumSize;
    private int growthRate;
    private int tickCount;

    public AmoebaGroup(int maximumSize, int growthRate, int x, int y) {
        this.maximumSize = maximumSize;
        this.growthRate = growthRate;
        this.amoebaList = new ArrayList<>();
        Amoeba initialAmoeba = new Amoeba(x, y);
        this.amoebaList.add(initialAmoeba);
        this.tickCount = 0;
    }

    public Amoeba getFirst() {
        return amoebaList.get(0);
    }

    public void tick() {
        if (this.tickCount != growthRate) {
            this.tickCount++;
            return;
        }
        
        this.tickCount = 0;

        if (this.amoebaList.size() == maximumSize) {
            System.out.println("Maximum size reached");
            transformToBoulders();
            return;
        }

        ArrayList<Amoeba> growable = new ArrayList<>();
        for (int i = 0; i < amoebaList.size(); i++) {
            if (amoebaList.get(i).canGrow()) {
                growable.add(amoebaList.get(i));
            }
        }

        if (growable.isEmpty()) {
            transformToDiamonds();
            return;
        }

        Random rand = new Random();
        int growIndex = rand.nextInt(growable.size());
        Amoeba activeAmoeba = growable.get(growIndex);
        Random r = new Random();
        ArrayList<Direction> growDirs = activeAmoeba.growthDirections();

        if (growDirs.isEmpty()) {
            return;
        }

        Direction randDir = growDirs.get(r.nextInt(growDirs.size()));

        Amoeba grown = activeAmoeba.grow(randDir);
        amoebaList.add(grown);
    }

    public void transformToDiamonds() {
        for (int i = 0; i < amoebaList.size(); i++) {
            Amoeba a = amoebaList.get(i);
            int aX = a.getX();
            int aY = a.getY();
            Diamond d = new Diamond(aX, aY);
            Game.getGame().replaceEntity(aX, aY,d);
            Game.getGame().addFallingEntity(d);
        }
        Game.getGame().removeAmoebaGroup(this);
    }

    public void transformToBoulders() {
        for (int i = 0; i < amoebaList.size(); i++) {
            Amoeba a = amoebaList.get(i);
            int aX = a.getX();
            int aY = a.getY();
            Boulder b = new Boulder(aX, aY);
            Game.getGame().replaceEntity(aX, aY, b);
            Game.getGame().addFallingEntity(b);
        }
        Game.getGame().removeAmoebaGroup(this);
    }
}
