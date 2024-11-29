/**
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
     *
     */
    public int[] getViewable() {
        return switch (viewMode) {
            case 1 -> new int[]{0, 29, 0, 15};
            case 2 -> new int[]{10, 39, 0, 15};
            case 3 -> new int[]{0, 29, 6, 21};
            case 4 -> new int[]{10, 39, 6, 21};
            default -> null;
        };
    }

    public int getView() {
        return viewMode;
    }

    public void changeViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
}