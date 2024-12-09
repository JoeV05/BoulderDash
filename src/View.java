/**
 * Represents a view of the map. Split into quadrants.
 * @author Joe Devlin, James Harvey
 * @version 1.2
 */
public class View {
    int viewMode;
    private static final int LEFT_SCREEN_UPPER_BOUND = Main.MAP_SEEN_WIDTH - 1;
    private static final int RIGHT_SCREEN_LOWER_BOUND = 10;
    private static final int RIGHT_SCREEN_UPPER_BOUND = Game.GRID_WIDTH - 1;
    private static final int BOTTOM_SCREEN_UPPER_BOUND = 6;
    private static final int BOTTOM_SCREEN_LOWER_BOUND = Game.GRID_HEIGHT - 2;
    private static final int TOP_SCREEN_LOWER_BOUND = Main.MAP_SEEN_HEIGHT - 1;

    public View(int viewMode) {
        this.viewMode = viewMode;
    }

    /**
     * Retrieve view boundaries.
     * @return Array of integers.
     */
    public int[] getViewable() {
        return switch (viewMode) {
            case 1 -> new int[]{
                    0,
                    LEFT_SCREEN_UPPER_BOUND,
                    0,
                    TOP_SCREEN_LOWER_BOUND
            };
            case 2 -> new int[]{
                    RIGHT_SCREEN_LOWER_BOUND,
                    RIGHT_SCREEN_UPPER_BOUND,
                    0,
                    TOP_SCREEN_LOWER_BOUND
            };
            case 3 -> new int[]{
                    0,
                    LEFT_SCREEN_UPPER_BOUND,
                    BOTTOM_SCREEN_UPPER_BOUND,
                    BOTTOM_SCREEN_LOWER_BOUND
            };
            case 4 -> new int[]{
                    RIGHT_SCREEN_LOWER_BOUND,
                    RIGHT_SCREEN_UPPER_BOUND,
                    BOTTOM_SCREEN_UPPER_BOUND,
                    BOTTOM_SCREEN_LOWER_BOUND
            };
            default -> throw new IllegalStateException("Invalid view mode "
                    + viewMode);
        };
    }

    /**
     * Get the current view of the map.
     * @return Integer.
     */
    public int getView() {
        return viewMode;
    }

    /**
     * Change the current view of the map.
     * @param viewMode View to change to.
     */
    public void changeViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
}
