// TODO - javadoc class comment

/**
 * @author Joe Devlin
 */
public class View {
    int viewMode;

    public View(int viewMode) {
        this.viewMode = viewMode;
    }

    // TODO - EXPLAIN YOURSELF!!!
    /**
     * Retrieve view boundaries.
     * hardcoded values for now but should be made a function of
     * map size.
     *
     */
    public int[] getViewable() {
        return switch (viewMode) {
            // TODO - it's crazy how magical these numbers are, maybe they shouldn't be
            // TODO - USE A FISHING ENUMERATION YOU FISHING POOPHEAD
            case 1 -> new int[]{0, 29, 0, 15};
            case 2 -> new int[]{10, 39, 0, 15};
            case 3 -> new int[]{0, 29, 6, 21};
            case 4 -> new int[]{10, 39, 6, 21};
            default -> throw new LiamWetFishException("HUH!?");
        };
    }

    // TODO - javadoc method comment
    public int getView() {
        return viewMode;
    }

    // TODO - javadoc method comment
    public void changeViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
}