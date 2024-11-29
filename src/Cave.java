import javafx.scene.image.Image;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cave {
    private int tilesWide;
    private int tilesTall;
    private char[][] cave;
    private final String caveName;
    private final String fileName;

    public Cave(String nameForCave, String nameForFile) throws FileNotFoundException {
        this.caveName = nameForCave;
        this.fileName = nameForFile;
        parseCave();
        System.out.printf("Cave instance created (%s -> %s). It is %d wide and %d tall.%n", caveName, fileName, tilesWide, tilesTall);
    }

    private void parseCave() throws FileNotFoundException {
        File file = new File("src/caves/%s".formatted(fileName));
        Scanner scanner = new Scanner(file);

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        tilesTall = lines.size();
        tilesWide = lines.getFirst().length();

        cave = new char[tilesTall][tilesWide];

        for (int x = 0; x < tilesTall; x++) {
            String line = lines.get(x);
            for (int y = 0; y < tilesWide; y++) {
                cave[x][y] = line.charAt(y);

            }
        }

        scanner.close();
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
    public String getCaveName() {
        return caveName;
    }
    public int getTilesWide() {
        return tilesWide;
    }
    public int getTilesTall() {
        return tilesTall;
    }
}
