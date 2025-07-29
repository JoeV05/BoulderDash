import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Creates a map/character array from a parsed text file and defines level
 * properties of the level such as Amoeba growth rate and Diamonds required
 * to complete the level.
 * @author Joe Devlin, Joe Vinson, James Harvey
 * @version 1.5
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
     * Automatically creates the next cave by loading the relevant level file.
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
     * Creates the Cave of the parsed file.
     * @param fileName Name of the file to be parsed.
     * @throws FileNotFoundException Throws an error if the required level file
     * is not found.
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
     * Reads the text file line-by-line and by character and adds it to the map
     * to be read by the game class.
     * @throws FileNotFoundException Throws an error if the file url is incorrect.
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
        Game.getGame().setDiamondsNeeded(Integer.parseInt(lines.get(tilesTall)));
        Game.getGame().setTimeLimit(Integer.parseInt(lines.get(tilesTall + 1)));
        Game.getGame().setAmoebaMaxGrowth(Integer.parseInt(lines.get(tilesTall + 2)));
        Game.getGame().setAmoebaGrowthRate(Integer.parseInt(lines.get(tilesTall
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
