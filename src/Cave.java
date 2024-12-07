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
        this.caveName = "Cave" + caveNumber;
        this.fileName = "level-" + caveNumber + ".txt";
        parseCave();
        System.out.printf("Cave instance created (%s -> %s). It is %d wide and %d tall.%n",
                caveName, fileName, tilesWide,  tilesTall);
        // TODO: Change constructor, parameters no longer needed, use caveNumber for automatic new cave generation
        // TODO - Does this mean automatically load the relevant level-n.txt for an input n?

    }

    private void parseCave() throws FileNotFoundException {
        File file = new File("src/caves/%s".formatted(fileName));
        Scanner scanner = new Scanner(file);
        //TODO: soon to change, use static caveNumber for new cave generation

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        tilesTall = lines.size() - 2;
        tilesWide = lines.getFirst().length();

        cave = new char[tilesTall][tilesWide];

        for (int x = 0; x < tilesTall; x++) {
            String line = lines.get(x);
            for (int y = 0; y < tilesWide; y++) {
                cave[x][y] = line.charAt(y);
            }
        }
        scanner.close();
        Game.setDiamondsNeeded(Integer.parseInt(lines.get(tilesTall)));
        Game.setTimeLimit(Integer.parseInt(lines.get(tilesTall+1)));
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

    // TODO - is this method necessary?
    public String getCaveName() {
        return caveName;
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

    //TODO: Have a function that loads the next cave caveN, N being the next number

}
