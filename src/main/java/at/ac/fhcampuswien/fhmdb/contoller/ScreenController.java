package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// helps to switch scenes
public class ScreenController {

    private static Stage stage = null;

    public static void initializeStage(Stage stage){
        ScreenController.stage=stage;
    }
    public static void switchToHomeView() throws IOException {
        switchViews("home-view.fxml", "FHMDb");
    }
    public static void switchToWatchlistView() throws IOException {
        switchViews("watchlist-view.fxml", "Watchlist");
    }
    public static void switchViews(String fxmlFileName, String Title) throws IOException {
        FXMLLoader watchlistViewFxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource(fxmlFileName));
        Scene watchlistScene = new Scene(watchlistViewFxmlLoader.load(), 890, 620);
        watchlistScene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle(Title);
        stage.setScene(watchlistScene);
        stage.show();
    }
}
