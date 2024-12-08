import java.util.ArrayList;
import java.util.List;

public class ChoosePlayer {
    private static final String PF = "profiles.txt";
    private List<PlayerProfile> profiles;
    private PlayerProfile curentProfile;

    public ChoosePlayer() {
        this.profiles = new ArrayList<>();
    }

    public List<PlayerProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<PlayerProfile> profiles) {
        this.profiles = profiles;
    }

    public PlayerProfile getCurentProfile() {
        return curentProfile;
    }

    public void setCurentProfile(PlayerProfile curentProfile) {
        this.curentProfile = curentProfile;
    }

    public void createProfile (String name) {
        PlayerProfile profile = new PlayerProfile(name);
        this.profiles.add(profile);
    }
}
