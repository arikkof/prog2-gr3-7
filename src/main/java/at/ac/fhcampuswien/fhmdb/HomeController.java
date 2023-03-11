package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.collections.transformation.FilteredList;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import at.ac.fhcampuswien.fhmdb.services.FilterService;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    public SortState sortState = SortState.NONE;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    private final FilteredList<Movie> movieFilteredList = new FilteredList<>(observableMovies);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);

        // initialize UI stuff
        movieListView.setItems(movieFilteredList);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // add genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        searchBtn.setOnAction(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue());
        });
        searchField.setOnKeyTyped(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue());
        });
        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                observableMovies.sort(Comparator.comparing(Movie::getTitle)); // same same but different: movie -> movie.getTitle()
                sortState = SortState.ASCENDING;
                sortBtn.setText("Sort (desc)");
            } else {
                observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
                sortState = SortState.DESCENDING;
                sortBtn.setText("Sort (asc)");
            }
        });
    }

   /* public void selectGenre() {
        FilterService.selectGenre(genreComboBox.getValue(), movieFilteredList);
    }*/

    public void updateFilteredMovies(String keyword, Genre genre) {
        List<Movie> movieListFilteredByGenre = new ArrayList<>();
        List<Movie> movieListFilteredByKeyword = new ArrayList<>();
        List<Movie> movieListFilteredResult = new ArrayList<>();
        for (Movie movie : observableMovies) {
            if (genre != null && movie.getGenres().contains(genre)) {
                movieListFilteredByGenre.add(movie);
            }
            if (movie.getDescription().toLowerCase().contains(keyword) || movie.getTitle().toLowerCase().contains(keyword)) {
                movieListFilteredByKeyword.add(movie);
            }
        }

        if (genre == null || genre == Genre.ALL) {
            movieListFilteredResult.addAll(movieListFilteredByKeyword);
        } else {
            for (Movie movie : movieListFilteredByKeyword) {
                if (movieListFilteredByGenre.contains(movie)) {
                    movieListFilteredResult.add(movie);
                }
            }
        }
        //alternatively: use observable List, clear first
        movieFilteredList.setPredicate(movieListFilteredResult::contains);
    }
}
