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
import javafx.collections.transformation.FilteredList;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    //public SortState sortState = SortState.NONE;
    // TODO: implement "ALL" option for releaseYearComboBox and ratingComboBox (how?) AND for genreComboBox, too
    // TODO: keep List of currently displayed movies, only show remaining relevant options in other comboBoxes
    // TODO: show all parameters of a Movie in MovieCell
    @FXML
    public JFXComboBox<Genre> genreComboBox;
    @FXML
    public JFXComboBox<Integer> releaseYearComboBox;
    @FXML
    public JFXComboBox<Double> ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies;

    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
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
        movieListView.setItems(movieFilteredList);   // set data of filtered list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
        // add genre filter items
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genre.values());
        // add releaseYear filter items
        releaseYearComboBox.setPromptText("Filter by Release Year");
        allMovies.stream().map(Movie::getReleaseYear).distinct().sorted().forEach(releaseYearComboBox.getItems()::add);
        // add rating filter items
        ratingComboBox.setPromptText("Filter by Rating");
        allMovies.stream().map(Movie::getRating).distinct().sorted().forEach(ratingComboBox.getItems()::add);
    }

    public void updateFilteredMovies(String keyword, Genre genre, Integer releaseYear, Double rating) {
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
        String releaseYearString=null;
        String ratingString=null;
        if(releaseYear!=null){releaseYearString=releaseYear.toString();};
        if(rating!=null){ratingString=rating.toString();}
        observableMovies.clear();
        observableMovies.addAll(MovieAPI.getMovies(keyword, genre, releaseYearString, ratingString));
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
