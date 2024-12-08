public class PlayerProfile {
    private String name;
    private int maxLevelUnlocked;

    public PlayerProfile(String name, int maxLevelUnlocked) {
        this.name = name;
        this.maxLevelUnlocked = maxLevelUnlocked = 1; //Thats the default
    }
    public String getName() {
        return name;
    }
    public int getMaxLevelUnlocked() {
        return maxLevelUnlocked;
    }
    public void setMaxLevelUnlocked(int maxLevelUnlocked) {
        this.maxLevelUnlocked = maxLevelUnlocked;
    }
    public void increaseMaxLevelUnlocked() {
        maxLevelUnlocked++;
    }
}
