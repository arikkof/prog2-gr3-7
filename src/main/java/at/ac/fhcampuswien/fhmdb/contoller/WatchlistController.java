package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

//TODO: Handle DatabaseExceptions here (show Message via UI)
public class WatchlistController implements Initializable {
    @FXML
    public JFXButton homeViewButton;
    @FXML
    public JFXListView<Movie> movieListView;
    public List<Movie> allMovies;
    public ObservableList observableWatchlistMovies = FXCollections.observableArrayList();
    private WatchlistRepository watchlistRepository;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        initializeBehaviour();
    }
    public void initializeState(){
        //TODO: get movies from DB, parse and set to allMovies
        watchlistRepository = new WatchlistRepository();
        try {
            allMovies = watchlistRepository.getDao().queryForAll().stream().map(Movie::new).collect(Collectors.toList());
        } catch (SQLException e) {
            // pass Warning to UI
        }
        observableWatchlistMovies.clear();
        observableWatchlistMovies.addAll(allMovies);
    }
    public void initializeLayout(){
        movieListView.setItems(observableWatchlistMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveFromWatchlistClicked, "Remove from Watchlist"));
    }
    public void initializeBehaviour(){
        homeViewButton.setOnAction(actionEvent -> {
            try {
                ScreenController.switchToHomeView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
   private final ClickEventHandler<Movie> onRemoveFromWatchlistClicked = (clickedMovie) ->
    {
        System.out.println("Remove from Watchlist clicked on movie: " + clickedMovie);
        try {
            watchlistRepository.removeFromWatchlist(new WatchlistMovieEntity(clickedMovie));
        } catch (Exception e) {
            // pass error message to UI
            Alert a = new Alert(Alert.AlertType.ERROR, "Failed to remove movie.", ButtonType.OK);
            a.setHeaderText("Error");
            a.setTitle("Error");
            a.show();
        }
    };
}
