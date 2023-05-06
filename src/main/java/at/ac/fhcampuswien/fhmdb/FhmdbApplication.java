package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.contoller.ScreenController;
import at.ac.fhcampuswien.fhmdb.data.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
TODO: add button "add to watchlist" to movie cell in home screen
TODO: add button "remove movie from watchlist" to movie cell in watchlist screen
*/

public class FhmdbApplication extends Application {
    private DatabaseManager databaseManager = DatabaseManager.getDatabase();
    @Override
    public void start(Stage stage) throws IOException {
        ScreenController.initializeStage(stage);
        ScreenController.switchToHomeView();
        try {
            //Testing to see if the data is persisted in the DB
            Movie movie1 = new Movie("die hard", "sdsdg",  new ArrayList<>());
            WatchlistMovieEntity watchlistEntity = new WatchlistMovieEntity(movie1);
            WatchlistRepository watchlistRepository = new WatchlistRepository();
            System.out.println ("DB object shoiuld have been created");
            List<WatchlistMovieEntity> items = watchlistRepository.getDao().queryForAll();
            System.out.println ("List contains " + items.size () + " elements");
            watchlistRepository.addToWatchlist(watchlistEntity);
            items = watchlistRepository.getDao().queryForAll();
            System.out.println ("List contains " + items.size () + " elements");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}