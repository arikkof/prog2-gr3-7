package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class HomeController implements Initializable {
    @FXML
    public JFXButton filterButton;
    @FXML
    public TextField searchField;
    @FXML
    public JFXListView<Movie> movieListView;
    @FXML
    public JFXComboBox<Genre> genreComboBox;
    @FXML
    public JFXComboBox<String> releaseYearComboBox;
    @FXML
    public JFXComboBox<String> ratingComboBox;
    @FXML
    public JFXButton sortBtn;
    public List<Movie> allMovies;
    // Observable List: automatically updates corresponding UI elements when underlying data changes
    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    public final ObservableList<Genre> observableGenres = FXCollections.observableArrayList();
    public final ObservableList<String> observableReleaseYears = FXCollections.observableArrayList();
    public final ObservableList<String> observableRatings = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        // Filter Button Event Handler (Event Listener)
        filterButton.setOnAction(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
        });
        // Search Field Event Handler (Listener)
        searchField.setOnKeyTyped(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
        });
        // Sort Button Event Handler (Event Listener)
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                observableMovies.sort(Comparator.comparing(Movie::getTitle)); // same same but different: movie -> movie.getTitle()
                //sortState = SortState.ASCENDING;
                sortBtn.setText("Sort (desc)");
            } else {
                observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
                //sortState = SortState.DESCENDING;
                sortBtn.setText("Sort (asc)");
            }
        });
    }
    public void initializeState(){
        // allMovies = Movie.initializeMovies(); // Fill with Dummy Data
        allMovies = MovieAPI.getAllMovies();
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }
    public void initializeLayout(){
        // do once: set data of observable lists to movieListView and combo boxes
        movieListView.setItems(observableMovies);
        genreComboBox.setItems(observableGenres);
        releaseYearComboBox.setItems(observableReleaseYears);
        ratingComboBox.setItems(observableRatings);
        // use custom cell factory to display Movies
        movieListView.setCellFactory(movieListView -> new MovieCell());
        // set prompt Texts
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Rating from");
        // first time updateLayout
        updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
    }
    public void updateLayout(Genre genre, String releaseYear, String rating){
        // clear observable lists
        observableGenres.clear();
        observableRatings.clear();
        observableReleaseYears.clear();
        // add ALL
        observableGenres.add(Genre.ALL);
        observableReleaseYears.add("ALL");
        observableRatings.add("ALL");
        // stream trough current genres/releaseYears/ratings and add respective items to respective observable list
        getCurrentGenres(observableMovies).stream().distinct().forEach(observableGenres::add);
        getCurrentReleaseYears(observableMovies).stream().distinct().sorted().map(Object::toString).forEach(observableReleaseYears::add);
        getCurrentRatings(observableMovies).stream().distinct().sorted().map(Object::toString).forEach(observableRatings::add);
        // set Value of combo boxes to current value to display currently selected items
        genreComboBox.setValue(genre);
        releaseYearComboBox.setValue(releaseYear);
        ratingComboBox.setValue(rating);
    }

    public List<Genre> getCurrentGenres(List<Movie> currentMovies){
        return currentMovies.stream().map(Movie::getGenres).flatMap(List::stream).filter(Objects::nonNull).toList();
    }
    public List<Integer> getCurrentReleaseYears(List<Movie> currentMovies){
        return currentMovies.stream().map(Movie::getReleaseYear).toList();
    }
    public List<Double> getCurrentRatings(List<Movie> currentMovies){
        return currentMovies.stream().map(Movie::getRating).toList();
    }
    public void updateFilteredMovies(String keyword, Genre genre, String releaseYear, String rating) {
        // if ALL or nothing: make null
        if(genre == Genre.ALL) genre = null;
        if(Objects.equals(releaseYear, "ALL") || Objects.equals(releaseYear, "")) releaseYear = null;
        if(Objects.equals(rating, "ALL") || Objects.equals(rating, "")) rating = null;
        // clear and add movies according to filter settings to observable movies
        observableMovies.clear();
        observableMovies.addAll(MovieAPI.getMovies(keyword, genre, releaseYear, rating));
    }
    // @TODO: implement these methods with Streams
    String getMostPopularActor(List<Movie> movies){ return new String(); }
    int getLongestMovieTitle(List<Movie> movies){ return 0; }
    public long countMoviesFrom(List<Movie> movies, String director) {
        var result = movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
        return result;
    }
  //  List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear){ List<Movie> moviesF = new List<Movie>; return moviesF; };

}
