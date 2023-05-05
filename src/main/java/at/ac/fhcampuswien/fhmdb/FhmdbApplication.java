package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.contoller.ScreenController;
import at.ac.fhcampuswien.fhmdb.data.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/*
TODO: add button "add to watchlist" to movie cell in home screen
TODO: add button "remove movie from watchlist" to movie cell in watchlist screen
*/

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScreenController.initializeStage(stage);
        ScreenController.switchToHomeView();

        try {
            DatabaseManager.getDatabase().testDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch();
    }
}