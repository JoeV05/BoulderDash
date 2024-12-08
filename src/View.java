/**
 * Represents a view of the map. Split into quadrants.
 * @author Joe Devlin
 * @version 1.0
 */
public class View {
    int viewMode;

    public View(int viewMode) {
        this.viewMode = viewMode;
    }

    /**
     * Retrieve view boundaries.
     * hardcoded values for now but should be made a function of
     * map size.
     */
    public int[] getViewable() {
        return switch (viewMode) {
            // TODO - remove magic numbers
            case 1 -> new int[]{0, 29, 0, 15};
            case 2 -> new int[]{10, 39, 0, 15};
            case 3 -> new int[]{0, 29, 6, 21};
            case 4 -> new int[]{10, 39, 6, 21};
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