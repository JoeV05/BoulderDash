import java.util.ArrayList;

import javafx.scene.image.Image;

// TODO - looking at this code makes me want to cry, needs fixing

/**
 * Represents an Amoeba
 *
 * Amoeba are unique organisms that spread across the game board and have specific transformation rules.
 * According to the game specification, amoebae:
 * - Spread periodically to neighboring empty path tiles or dirt tiles
 * - Can kill enemies touching them in cardinal directions
 * - Transform based on their growth:
 * 1. If growth is blocked, they turn into diamonds
 * 2. If they reach a predefined size, they turn into boulders
 * Amoeba are harmless until they spread or transform, adding a strategic element to the game.
 *
 * @author Joseph Vinson, Tafara Gonese
 * Check Section 3.8 of the Functional Specification for detailed Amoeba behavior
 */

public class Amoeba extends Tile {
    /**
     * The current size of the amoeba.
     */
    private int size;

    // TODO - remove duplicate variables

    /**
     * The maximum size the amoeba can reach before transforming.
     */
    private final int maximumSize;

    /**
     * The size at which the amoeba transforms into boulders.
     */

    private ArrayList<Amoeba> group;

    /**
     * Constructs a new Amoeba tile.
     *
     * @param x                  The x-coordinate of the amoeba tile.
     * @param y                  The y-coordinate of the amoeba tile.
     * @param maximumSize        The maximum size the amoeba can reach before transforming.
     * @param maximumSize The size at which the amoeba transforms into boulders.
     */
    public Amoeba(int x, int y, int maximumSize) {
        super(x, y, false, TileType.AMOEBA, new Image("./sprites/Amoeba.png"));
        this.maximumSize = maximumSize;
        this.size = 1; // Start with a size of 1
        this.group = new ArrayList<>();
        this.group.add(this);
    }

    public Amoeba(int x, int y, int maximumSize, Image image, ArrayList<Amoeba> group) {
        super(x, y, false, TileType.AMOEBA, image);
        this.maximumSize = maximumSize;
        this.group = group;
        this.group.add(this);
        this.size = this.group.size();
    }

    /**
     * Grows the amoeba by one size.
     * If the size reaches the maximum size, the amoeba transforms into boulders and diamonds.
     */
    public void grow() {
        if (this.size < this.maximumSize) {
            // TODO - Check the thing
            // TODO - if (the thing) {
            //      do the growing
            //  }
            this.size++;
        } else if (this.size >= this.maximumSize) {
            transformToBouldersAndDiamonds();
        }
    }

    /**
     * Transforms the amoeba into boulders and diamonds.
     * The transformation starts with boulders, and any remaining amoeba are transformed into diamonds.
     */
    private void transformToBouldersAndDiamonds() {
        transformToBouldersAndDiamonds(true);
    }

    /**
     * Transforms the amoeba into boulders and diamonds.
     *
     * @param transformToBouldersFirst If true, the amoeba is transformed into boulders first,
     *                                and the remaining amoeba are transformed into diamonds.
     *                                If false, the amoeba is transformed into diamonds.
     */
    // TODO - what the fish is this, doesnt seem like the best way to do it
    private void transformToBouldersAndDiamonds(boolean transformToBouldersFirst) {
        if (transformToBouldersFirst) {
            transformToBouldersAndLeaveRemaindersAsDiamonds();
        } else {
            transformToDiamonds();
        }
    }

    /**
     * Transforms the amoeba into boulders and leaves any remaining amoeba as diamonds.
     */
    // TODO - what the fish is this, I don't see it in the spec
    private void transformToBouldersAndLeaveRemaindersAsDiamonds() {
        // Implement the logic to create boulders from the amoeba
        // and leave any remaining amoeba as diamonds
    }

    /**
     * Transforms the amoeba into diamonds.
     */
    private void transformToDiamonds() {
        // Implement the logic to create diamonds from the amoeba
    }
}
