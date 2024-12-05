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
    public Button tutorialButton;

    @FXML
    public Button exitButton;

    // TODO - javadoc method comment
    @FXML
    public void startGameButtonPress(ActionEvent actionEvent) throws IOException {
        //Get the stage (i.e. the window)
        Stage primaryStage = (Stage) startGameButton.getScene().getWindow();

        //Load the fxml layout
        BorderPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("layout.fxml")));
        Scene scene = new Scene(root, Main.CANVAS_WIDTH, Main.CANVAS_HEIGHT);

        //Set the scene to the new layout
        primaryStage.setTitle(Main.GAME_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();

        //???
        root.setCenter(Main.getCanvas());
        //Set the game to allow ticks
        Main.setInLevel(true);
        //Add keyboard listeners to the scene
        scene.addEventFilter(KeyEvent.KEY_PRESSED, Main.getMain()::handleKeyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, Main.getMain()::handleKeyReleased);
        //Set the game off
        Game.getGame().loadingCave();
    }

    @FXML
    public void tutorialButtonPress(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) tutorialButton.getScene().getWindow();
    }

    @FXML
    public void loadSaveButtonPress(ActionEvent actionEvent) {
    }

    @FXML
    public void exitButtonPress(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
