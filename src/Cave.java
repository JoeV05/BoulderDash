import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cave {
    private int tilesTall;
    private int tilesWide;
    private char[][] cave;
    private final String caveName;
    private final String fileName;
    private static int caveNumber = 0;

    public Cave() throws FileNotFoundException {
        caveNumber++;
        this.caveName = "Cave-" + caveNumber;
        this.fileName = "level-" + caveNumber + ".txt";
        Game.getGame().setCurrentLevelFileName(fileName);
        parseCave();
        System.out.printf("Cave instance created (%s -> %s). It is %d wide and %d tall.%n",
                caveName, fileName, tilesWide,  tilesTall);
    }

    public Cave(String fileName) throws FileNotFoundException {
        this.caveName = "Cave-" + fileName.charAt(fileName.length() - 5);
        this.fileName = fileName;
        parseCave();
        System.out.printf("Cave instance created (%s -> %s). It is %d wide and %d tall.%n",
                caveName, fileName, tilesWide,  tilesTall);
    }

    private void parseCave() throws FileNotFoundException {
        File file = new File("src/caves/%s".formatted(fileName));
        if (!file.exists()) {
            file = new File("src/caves/level-MAGIC-WALL-TEST.txt");
        }
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        tilesTall = lines.size() - 4;
        tilesWide = lines.getFirst().length();

        cave = new char[tilesTall][tilesWide];

        for (int y = 0; y < tilesTall; y++) {
            String line = lines.get(y);
            for (int x = 0; x < tilesWide; x++) {
                cave[y][x] = line.charAt(x);
            }
        }
        scanner.close();
        Game.setDiamondsNeeded(Integer.parseInt(lines.get(tilesTall)));
        Game.setTimeLimit(Integer.parseInt(lines.get(tilesTall + 1)));
        Game.setAmoebaMaxGrowth(Integer.parseInt(lines.get(tilesTall + 2)));
        Game.setAmoebaGrowthRate(Integer.parseInt(lines.get(tilesTall + 3)));
    }

    public void printCave() {
        for (int x = 0; x < tilesTall; x++) {
            for (int y = 0; y < tilesWide; y++) {
                System.out.print(cave[x][y]);
            }
            System.out.println();
        }
    }

    public char[][] getCave() {
        return cave;
    }

    public int getTilesTall() {
        return tilesTall;
    }

    public int getTilesWide() {
        return tilesWide;
    }

    //Get CaveNumber
    public static int getCaveNumber() {
        return caveNumber;
    }

    //get CaveNumber
    public static void setCaveNumber(int number) {
        caveNumber = number;
    }
}
