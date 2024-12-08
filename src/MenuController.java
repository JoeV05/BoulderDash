import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.input.KeyEvent;

public class MenuController {
    @FXML
    private Button startGameButton;

    @FXML
    private Button exitButton;

    /**
     * Start the game when the start button is pressed on the main menu.
     * @throws IOException Throw an error if the fxml file can't be found.
     */
    @FXML
    public void startGameButtonPress() throws IOException {
        Stage primaryStage = (Stage) startGameButton.getScene().getWindow();
        BorderPane root;
        root = FXMLLoader.load(getClass().getResource("layout.fxml"));
        Scene scene = new Scene(root, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);

        primaryStage.setTitle(Main.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.setCenter(Main.getCanvas());
        Main.setInLevel(true);
        Main m = Main.getMain();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, m::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, m::handleKeyReleased);

        Game.getGame().loadingCave();
    }

    /**
     * Open the tutorial screen when the tutorial button is pressed.
     */
    @FXML
    public void tutorialButtonPress() {
    }

    /**
     * Load the save file into the game when the save button is pressed.
     */
    @FXML
    public void loadSaveButtonPress() {
        Game.getGame().loadGame("save.txt");
    }

    /**
     * Save the game to a text file.
     */
    @FXML
    public void saveGameButtonPress() {
        Game.getGame().saveGame("savegame.txt");
    }

    /**
     * Exit the game when the exit game button is pressed.
     */
    @FXML
    public void exitButtonPress() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
