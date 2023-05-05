package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
//TODO: Handle DatabaseExceptions here (show Message via UI)
public class WatchlistController implements Initializable {
    @FXML
    public JFXButton homeViewButton;
    @FXML
    public JFXListView<Movie> movieListView;
    public List<Movie> allMovies;
    public ObservableList observableWatchlistMovies;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        initializeBehaviour();
    }
    public void initializeState(){
        //TODO: get movies from DB, parse and set to allMovies
        //observableWatchlistMovies.clear();
        //observableWatchlistMovies.addAll(allMovies);
    }
    public void initializeLayout(){
        //movieListView.setItems(observableWatchlistMovies);
        //movieListView.setCellFactory(movieListView -> new MovieCell());
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
   /* private final ClickEventHandler onRemoveFromWatchlistClicked = (clickedItem) ->
    {
        // add code to rm movie from watchlist here
    };*/
}
