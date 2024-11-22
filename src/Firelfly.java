public class Firelfly extends Enemy {

    @Override
    public int[] moveTo(int[][] gameState) {
        return new int[0];
    }

    @Override
    public void movementTests() {

    }

    @Override
    public int onDeathByHazard() {
        return 0;
    }

    @Override
    public int onDeathByFallingObject() {
        return 0;
    }
}
