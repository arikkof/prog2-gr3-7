package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.contoller.ScreenController;
import at.ac.fhcampuswien.fhmdb.data.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FhmdbApplication extends Application {
    private DatabaseManager databaseManager = DatabaseManager.getDatabase();
    @Override
    public void start(Stage stage) throws IOException {
        ScreenController.initializeStage(stage);
        ScreenController.switchToHomeView();
        /*try {
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
        Movie movie1 = new Movie("die hard", "sdsdg", Arrays.asList(Genre.DRAMA, Genre.ACTION));
        WatchlistMovieEntity watchlistEntity = new WatchlistMovieEntity(movie1);
        Movie movie11 = new Movie(watchlistEntity);
        System.out.println(movie11.getGenres());
        Dao dao = DatabaseManager.getDatabase().getDao();
        try {
            dao.delete(dao.queryForEq("title", "The Godfather"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
    public static void main(String[] args) {
        launch();
    }
}