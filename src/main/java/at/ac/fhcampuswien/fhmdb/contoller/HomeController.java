package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.interfaces.Observer;
import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.states.NoneSortState;
import at.ac.fhcampuswien.fhmdb.states.SortState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomeController implements Initializable, Observer {
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
    @FXML
    public JFXButton watchlistButton;
    private WatchlistRepository watchlistRepository;
    public List<Movie> allMovies;
    // Observable List: automatically updates corresponding UI elements when underlying data changes
    public final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    public final ObservableList<Genre> observableGenres = FXCollections.observableArrayList();
    public final ObservableList<String> observableReleaseYears = FXCollections.observableArrayList();
    public final ObservableList<String> observableRatings = FXCollections.observableArrayList();
    private SortState sortState;
    // new Constructor provided:
    public HomeController(){
        // retrieve WatchlistRepository Object here
        try {
            watchlistRepository = WatchlistRepository.getInstance();
        } catch(DatabaseException e) {
            e.printStackTrace();
        }
        // Subscribe after instantiation
        watchlistRepository.subscribe(this);
        System.out.println("Constructing HomeController: " + this);
    }

    public void setSortState(SortState specificSortState){
        this.sortState = specificSortState;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeState();
        initializeLayout();
        initializeBehaviour();
    }
    public void testStreamMethods(){
        // Console Output for Testing
        System.out.println("MOST POPULAR ACTOR");
        System.out.println(getMostPopularActor(allMovies));
        System.out.println("LONGEST MOVIE TITLE");
        System.out.println(getLongestMovieTitle(allMovies));
        System.out.println("MAX NUMBER MOVIES OF DIRECTOR");
        System.out.println(countMoviesFrom(allMovies, "Steven Spielberg"));
        System.out.println("MOVIES BETWEEN YEARS 1990 and 2000");
        //System.out.println(getMoviesBetweenYears(allMovies,1990,2000));
        getMoviesBetweenYears(allMovies, 1990, 2000).stream().forEach(m -> System.out.println(m.getTitle()));
    }
    public void initializeState() {
        setSortState(new NoneSortState(this));
        sortState.updateSortButton();
        allMovies = MovieAPI.getAllMovies();
        setObservableMoviesToAllMovies();
        try {
            watchlistRepository = WatchlistRepository.getInstance();
        } catch (DatabaseException e){

        }
    }

    public void setObservableMoviesToAllMovies(){
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    }

    public void initializeLayout() {
        // do once: set data of observable lists to movieListView and combo boxes
        movieListView.setItems(observableMovies);
        genreComboBox.setItems(observableGenres);
        releaseYearComboBox.setItems(observableReleaseYears);
        ratingComboBox.setItems(observableRatings);
        // use custom cell factory to display Movies
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked, "Add to Watchlist"));
        // set prompt Texts
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        ratingComboBox.setPromptText("Rating from");
        // first time updateLayout
        updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
    }

    public void initializeBehaviour() {
        // Filter Button Event Handler (Event Listener)
        filterButton.setOnAction(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            sortState.sort(); // sort according to current Sort State
        });
        // Search Field Event Handler (Listener)
        searchField.setOnKeyTyped(actionEvent -> {
            updateFilteredMovies(searchField.getText().trim().toLowerCase(), genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            updateLayout(genreComboBox.getValue(), releaseYearComboBox.getValue(), ratingComboBox.getValue());
            sortState.sort(); // sort according to current Sort State
        });
        // Sort Button Event Handler (Event Listener)
        sortBtn.setOnAction(actionEvent -> {
            sortState.toggleSortStates(); // set State to next Sort State
            sortState.updateSortButton(); // set Button Text to the Sort State after that
            sortState.sort(); // sort according to current Sort State
        });
        watchlistButton.setOnAction(actionEvent -> ScreenController.switchToWatchlistView());
    }

    public void updateLayout(Genre genre, String releaseYear, String rating) {
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
    // save specific implementation of onClick method from ClickEventHandler functional interface
    private final ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedMovie) -> {
        System.out.println("Add to Watchlist clicked on movie: " + clickedMovie);
        try {
            watchlistRepository.addToWatchlist(new WatchlistMovieEntity(clickedMovie));
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    };

    public List<Genre> getCurrentGenres(List<Movie> currentMovies) {
        return currentMovies.stream().map(Movie::getGenres).flatMap(List::stream).filter(Objects::nonNull).toList();
    }

    public List<Integer> getCurrentReleaseYears(List<Movie> currentMovies) {
        return currentMovies.stream().map(Movie::getReleaseYear).toList();
    }

    public List<Double> getCurrentRatings(List<Movie> currentMovies) {
        return currentMovies.stream().map(Movie::getRating).toList();
    }

    public void updateFilteredMovies(String keyword, Genre genre, String releaseYear, String rating) {
        // if ALL or nothing: make null
        if (genre == Genre.ALL) genre = null;
        if (Objects.equals(releaseYear, "ALL") || Objects.equals(releaseYear, "")) releaseYear = null;
        if (Objects.equals(rating, "ALL") || Objects.equals(rating, "")) rating = null;
        // clear and add movies according to filter settings to observable movies
        observableMovies.clear();
        observableMovies.addAll(MovieAPI.getMovies(keyword, genre, releaseYear, rating));
    }

    public String getMostPopularActor(List<Movie> movies) {
        Map<String, Long> actorCountMap = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (actorCountMap.isEmpty()) {
            return null;
        }
        Optional<Map.Entry<String, Long>> mostFrequentActor = actorCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue());
        return mostFrequentActor.get().getKey();
    }

    int getLongestMovieTitle(List<Movie> movies) {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director) {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }

    List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        return movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());
    }

    @Override
    public void receiveUpdate(String message) {
        //System.out.println(message);
        Alert a = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        a.setHeaderText("Information for you");
        a.setTitle("INFO");
        a.show();
    }
}
