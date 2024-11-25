import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cave {
    private static final int tilesTall = 22;
    private static final int tilesWide = 40;
    private final char[][] cave = new char[tilesTall][tilesWide];
    private final String caveName;
    private final String fileName;

    public Cave(String nameForCave, String nameForFile) throws FileNotFoundException {
        this.caveName = nameForCave;
        this.fileName = nameForFile;
        parseCave();
        System.out.printf("Cave instance created (%s -> %s)%n", caveName, fileName);
    }

    private void parseCave() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/caves/%s".formatted(fileName)));
        for (int x = 0; x < tilesTall; x++) {
            String line = scanner.nextLine();
            for (int y = 0; y < tilesWide; y++) {
                cave[x][y] = line.charAt(x);
            }
        }
        scanner.close();
    }

    public char[][] getCave() {
        return cave;
    }
    public String getCaveName() {
        return caveName;
    }

}
