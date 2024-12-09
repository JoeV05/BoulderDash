/**
 * WHAT DOES THIS DO.
 * @author Luke Brace
 * @version 1.2
 */
public class PlayerProfile {
    private String name;
    private int maxLevelUnlocked;

    /**
     * Create a new player profile with a given name.
     * @param name The name of the player profile.
     */
    public PlayerProfile(String name) {
        this.name = name;
        this.maxLevelUnlocked = 1;
    }

    /**
     * Get the name of this player profile.
     * @return String object.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the maximum level the player has unlocked on this profile.
     * @return Positive integer.
     */
    public int getMaxLevelUnlocked() {
        return this.maxLevelUnlocked;
    }

    /**
     * Set the maximum level the player has unlocked on this profile.
     * @param maxLevelUnlocked The level number of the new maximum level the
     * player has unlocked on this profile.
     */
    public void setMaxLevelUnlocked(int maxLevelUnlocked) {
        this.maxLevelUnlocked = maxLevelUnlocked;
    }

    /**
     * Increment the maximum level the player has unlocked for this profile by
     * one.
     */
    public void increaseMaxLevelUnlocked() {
        this.maxLevelUnlocked++;
    }
}
