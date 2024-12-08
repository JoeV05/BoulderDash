import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * What does this do?
 * Why are there no comments?
 * Why must you make me suffer?
 * I don't like suffering.
 * :(
 */
public class ChoosePlayer {
    private static final String PF = "profiles.txt";
    private List<PlayerProfile> profiles;
    private PlayerProfile curentProfile;

    /**
     *
     */
    public ChoosePlayer() {
        this.profiles = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public List<PlayerProfile> getProfiles() {
        return profiles;
    }

    /**
     *
     * @param profiles
     */
    public void setProfiles(List<PlayerProfile> profiles) {
        this.profiles = profiles;
    }

    /**
     *
     * @return
     */
    public PlayerProfile getCurentProfile() {
        return curentProfile;
    }

    /**
     *
     * @param curentProfile
     */
    public void setCurentProfile(PlayerProfile curentProfile) {
        this.curentProfile = curentProfile;
    }

    /**
     *
     * @param name
     */
    public void createProfile (String name) {
        PlayerProfile profile = new PlayerProfile(name);
        this.profiles.add(profile);
    }

    /**
     *
     * @throws IOException
     */
    private void saveProfile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PF))) {
            for (PlayerProfile profile : profiles) {
                writer.println(profile.toString());
            }
        }
    }
}
