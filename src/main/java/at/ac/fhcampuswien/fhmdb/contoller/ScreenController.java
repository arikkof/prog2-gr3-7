package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Objects;

// helps to switch scenes
public class ScreenController {

    private static Stage stage = null;
    public static void initializeStage(Stage stage){
        ScreenController.stage=stage;
    }
    public static void switchToHomeView() {
        try {
            switchViews("home-view.fxml", "FHMDb", HomeControllerFactory.getHomeControllerFactoryInstance());
        } catch (IOException e) {
            System.out.println("FXML Failure");
            e.printStackTrace();
        }
    }
    public static void switchToWatchlistView() {
        try {
            switchViews("watchlist-view.fxml", "Watchlist", WatchlistControllerFactory.getWatchlistControllerFactoryInstance());
        } catch (IOException e) {
            System.out.println("FXML Failure in switching to Watchlist View: " + e.getMessage() + e.getCause());
        }
    }
    public static void switchViews(String fxmlFileName, String Title, Callback mySpecificControllerFactory) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource(fxmlFileName));
        fxmlLoader.setControllerFactory(mySpecificControllerFactory);
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle(Title);
        stage.setScene(scene);
        stage.show();
    }
}
