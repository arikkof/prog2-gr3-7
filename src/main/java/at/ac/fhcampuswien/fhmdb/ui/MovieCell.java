package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie>{
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final Button showDetailsButton = new Button();
    private final Button watchlistBtn = new Button();
    private final HBox buttonsHbox = new HBox(showDetailsButton, watchlistBtn);
    private final VBox layout = new VBox(title, buttonsHbox, detail, genre, releaseYear, rating);

   public MovieCell(ClickEventHandler<Movie> onAddToOrRemoveFromWatchlistClicked, String buttonText){
        super();
        watchlistBtn.setOnMouseClicked(mouseEvent -> { // if movie cell's button has been clicked
            onAddToOrRemoveFromWatchlistClicked.onClick( // relates to passed method implementation
                    getItem() // get movie, which has been clicked on
            );
        });
        watchlistBtn.setText(buttonText); // set to custom text passed with MovieCell constructor
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);
        if (empty || movie == null) {
            setText(null);
            // !!! FIX of bug displaying obsolete movies:
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            if(movie.getGenres()!=null) {
                genre.setText(movie.getGenres().toString());
            };
            genre.getStyleClass().add("text-genre");
            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            showDetailsButton.setText("Show Details");
            showDetailsButton.getStyleClass().add("background-yellow");
            watchlistBtn.getStyleClass().add("background-yellow");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
            releaseYear.setText("Released: " + String.valueOf(movie.getReleaseYear()));
            releaseYear.getStyleClass().add("text-white");
            rating.setText("Rating: " + String.valueOf(movie.getRating()));
            rating.getStyleClass().add("text-white");
            // layout
            buttonsHbox.setSpacing(5);
            //buttonsHbox.setAlignment(Pos.TOP_RIGHT);
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }

}

