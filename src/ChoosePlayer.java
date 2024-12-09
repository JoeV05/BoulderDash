import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Choose a player from the profiles.
 * @author Luke Brace
 */
public class ChoosePlayer {
    private static final String PF = "profiles.txt";
    private List<PlayerProfile> profiles;
    private PlayerProfile curentProfile;

    /**
     * Create an empty list of profiles.
     */
    public ChoosePlayer() {
        this.profiles = new ArrayList<>();
    }

    /**
     * Get the list of player profiles.
     * @return List of PlayerProfile objects.
     */
    public List<PlayerProfile> getProfiles() {
        return profiles;
    }

    /**
     * Set the list of profiles.
     * @param profiles Profiles to set the list to.
     */
    public void setProfiles(List<PlayerProfile> profiles) {
        this.profiles = profiles;
    }

    /**
     * Get the current player profile.
     * @return PlayerProfile object.
     */
    public PlayerProfile getCurentProfile() {
        return curentProfile;
    }

    /**
     * Set the current player profile.
     * @param curentProfile The player profile to set the current profile to.
     */
    public void setCurentProfile(PlayerProfile curentProfile) {
        this.curentProfile = curentProfile;
    }

    /**
     * Create a new player profile and add it to the list of player profiles.
     * @param name Name of the player to add.
     */
    public void createProfile(String name) {
        PlayerProfile profile = new PlayerProfile(name);
        this.profiles.add(profile);
    }

    /**
     * Save the list of profiles to a text file.
     * @throws IOException Throws an exception if the file to write to doesn't
     * exist.
     */
    private void saveProfile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PF))) {
            for (PlayerProfile profile : profiles) {
                writer.println(profile.toString());
            }
        }
    }
}
