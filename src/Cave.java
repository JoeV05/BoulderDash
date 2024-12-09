import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a level within the game.
 * @author Joe Devlin
 * @version 1.0
 */
public class Cave {
    public static final int METADATA_LENGTH = 5;
    private int tilesTall;
    private int tilesWide;
    private char[][] cave;
    private final String caveName;
    private final String fileName;
    private static int caveNumber = 0;

    /**
     * Creates a cave by loading the relevant level file.
     * @throws FileNotFoundException Throws an error if the required level file
     * is not found.
     */
    public Cave() throws FileNotFoundException {
        caveNumber++;
        this.caveName = "Cave-" + caveNumber;
        this.fileName = "level-" + caveNumber + ".txt";
        Game.getGame().setCurrentLevelFileName(fileName);
        parseCave();
        System.out.printf("Cave instance created (%s -> %s)."
                        + "It is %d wide and %d tall.%n",
                caveName, fileName, tilesWide,  tilesTall);
    }

    /**
     * Creates a cave by loading a given file.
     * @param fileName The name of the level file to load.
     * @throws FileNotFoundException Throw an exception if the level file
     * can't be found.
     */
    public Cave(String fileName) throws FileNotFoundException {
        this.caveName = "Cave-" + fileName.charAt(fileName.length()
                - METADATA_LENGTH);
        this.fileName = fileName;
        parseCave();
        System.out.printf("Cave instance created (%s -> %s). "
                        + "It is %d wide and %d tall.%n",
                caveName, fileName, tilesWide,  tilesTall);
    }

    /**
     * Read through a level file and store the data.
     * @throws FileNotFoundException Throw an exception if the level file can't
     * be found.
     */
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

        tilesTall = lines.size() - (METADATA_LENGTH - 1);
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
        Game.setAmoebaGrowthRate(Integer.parseInt(lines.get(tilesTall
                + METADATA_LENGTH - 2)));
    }

    /**
     * Return the character representation of the map as a string.
     * @return String of characters.
     */
    @Override
    public String toString() {
        String displayString = "";
        for (int x = 0; x < tilesTall; x++) {
            for (int y = 0; y < tilesWide; y++) {
                displayString += cave[x][y];
            }
            displayString += "\n";
        }
        return displayString;
    }

    /**
     * Retrieve the character representation of the level map.
     * @return 2D array of characters
     */
    public char[][] getCave() {
        return cave;
    }

    /**
     * Retrieve the height of the cave.
     * @return A positive integer.
     */
    public int getTilesTall() {
        return tilesTall;
    }

    /**
     * Retrieve the width of the cave.
     * @return A positive integer.
     */
    public int getTilesWide() {
        return tilesWide;
    }

    /**
     * Get the cave number.
     * @return A positive integer.
     */
    public static int getCaveNumber() {
        return caveNumber;
    }

    /**
     * Set the cave number.
     * @param number Cave number to set to.
     */
    public static void setCaveNumber(int number) {
        caveNumber = number;
    }
}
