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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.transformation.FilteredList;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    //public SortState sortState = SortState.NONE;

    // TODO: keep List of currently displayed movies(observableMovies), only show remaining relevant options in other comboBoxes
    @FXML
    public JFXComboBox<Genre> genreComboBox;
    @FXML
    public JFXComboBox<String> releaseYearComboBox;
    @FXML
    public JFXComboBox<String> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies;

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes


    public final ObservableList<Genre> observableGenres = FXCollections.observableArrayList();
    public final ObservableList<String> observableReleaseYears = FXCollections.observableArrayList();
    public final ObservableList<String> observableRatings = FXCollections.observableArrayList();

    // FilteredList: also an Observable (updates automagically)
    private final FilteredList<Movie> movieFilteredList = new FilteredList<>(observableMovies);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();

        // Search Button ("Filter") Event Handler (Listener)
        searchBtn.setOnAction(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
        });

        // Search Field Event Handler (Listener)
        searchField.setOnKeyTyped(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
        });

        // Sort button:
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
        //allMovies = Movie.initializeMovies(); // Fill with Dummy Data
        allMovies = MovieAPI.getAllMovies();
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    public void initializeLayout(){
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of filtered list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Filter by Rating");
        updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
    }

    public void clearCombobox(ComboBox comboBox){
        comboBox.getItems().clear();
    }
    public void updateLayout(Genre genre, String releaseYear, String rating){
        // add genre filter items
        clearCombobox(genreComboBox);
        clearCombobox(releaseYearComboBox);
        clearCombobox(ratingComboBox);

        genreComboBox.setValue(genre);
        releaseYearComboBox.setValue(releaseYear);
        ratingComboBox.setValue(rating);

        genreComboBox.getItems().add(Genre.ALL);
        getCurrentGenres(observableMovies).stream().distinct().forEach(genreComboBox.getItems()::add);
        //observableMovies.stream().map(Movie::getGenres).forEach(l -> {l.stream().distinct().sorted().forEach(e -> genreComboBox.getItems().addAll(e));});
        //genreComboBox.getItems().addAll(Genre.values());
        // add releaseYear filter items
        releaseYearComboBox.getItems().add("ALL");
        getCurrentReleaseYears(observableMovies).stream().distinct().sorted().map(Object::toString).forEach(releaseYearComboBox.getItems()::add);
        // add rating filter items
        ratingComboBox.getItems().add("ALL");
        getCurrentRatings(observableMovies).stream().distinct().sorted().map(Object::toString).forEach(ratingComboBox.getItems()::add);
    }

    public List<Genre> getCurrentGenres(List<Movie> currentMovies){
        List<Genre> result = currentMovies.stream().map(Movie::getGenres).flatMap(List::stream).toList();
        System.out.println(result);
        return result;
    }

    public List<Integer> getCurrentReleaseYears(List<Movie> currentMovies){
        return currentMovies.stream().map(Movie::getReleaseYear).toList();
    }

    public List<Double> getCurrentRatings(List<Movie> currentMovies){
        return currentMovies.stream().map(Movie::getRating).toList();
    }



    public void updateFilteredMovies(String keyword, Genre genre, String releaseYear, String rating) {
        // create 2 intermediary Lists and 1 final
//        List<Movie> movieListFilteredByGenre = new ArrayList<>();
//        List<Movie> movieListFilteredByKeyword = new ArrayList<>();
//        List<Movie> movieListFilteredResult = new ArrayList<>();
//        // iterate through all movies
//        for (Movie movie : observableMovies) {
//            // add movies according to genre match
//            if (genre != null && movie.getGenres().contains(genre)) {
//                movieListFilteredByGenre.add(movie);
//            }
//            // add movies according to match in description or title
//            if (movie.getDescription().toLowerCase().contains(keyword) || movie.getTitle().toLowerCase().contains(keyword)) {
//                movieListFilteredByKeyword.add(movie);
//            }
//        }
//        // if no genre selected or ALL: add all keyword matched movies to final list
//        if (genre == null || genre == Genre.ALL) {
//            movieListFilteredResult.addAll(movieListFilteredByKeyword);
//        } else {
//            // add all movies to final list that match both genre and keyword search
//            for (Movie movie : movieListFilteredByKeyword) {
//                if (movieListFilteredByGenre.contains(movie)) {
//                    movieListFilteredResult.add(movie);
//                }
//            }
//        }

        if(genre == Genre.ALL) genre = null;
        if(Objects.equals(releaseYear, "ALL") || Objects.equals(releaseYear, "")) releaseYear = null;
        if(Objects.equals(rating, "ALL") || Objects.equals(rating, "")) rating = null;
        observableMovies.clear();
        observableMovies.addAll(MovieAPI.getMovies(keyword, genre, releaseYear, rating));
        updateLayout(genre, releaseYear, rating);
        // alternatively: use observable List directly but clear first
        // set Predicate of filtered List: for all movies from final (result) list that are contained in movieFilteredList
        // movieFilteredList.setPredicate(movieListFilteredResult::contains);
        // movieFilteredList.setPredicate(observableMovies::contains);
    }
    // @TODO: implement these methods with Streams
    String getMostPopularActor(List<Movie> movies){ return new String(); }
    int getLongestMovieTitle(List<Movie> movies){ return 0; };
    public long countMoviesFrom(List<Movie> movies, String director) {
        var result = movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
        return result;
    }
  //  List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear){ List<Movie> moviesF = new List<Movie>; return moviesF; };


}
