import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.input.*;

public class MenuController {
    @FXML
    public Button startGameButton;

    @FXML
    public Button exitButton;

    @FXML
    public void startGameButtonPress(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage) startGameButton.getScene().getWindow();

        BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout.fxml")));
        Scene scene = new Scene(root, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);

        primaryStage.setTitle(Main.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

        root.setCenter(Main.getCanvas());
        Main.setInLevel(true);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, Main.getMain()::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, Main.getMain()::handleKeyReleased);
        Game.getGame();
        Game.getGame().loadingCave();
    }

    @FXML
    public void tutorialButtonPress(ActionEvent actionEvent) {
    }

    @FXML
    public void loadSaveButtonPress(ActionEvent actionEvent) {
        Game.getGame().loadGame("savegame.txt");

    }

    @FXML
    public void saveGameButtonPress(java.awt.event.ActionEvent actionEvent) {
        Game.getGame().saveGame("savegame.txt");
    }

    @FXML
    public void exitButtonPress(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
