package at.ac.fhcampuswien.fhmdb.services;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.collections.transformation.FilteredList;

public class FilterService {


    public static void selectGenre(Genre genre, FilteredList<Movie> movieFilteredList) {
        movieFilteredList.setPredicate(movie -> {
            if (genre.equals(Genre.ALL)) {
                return true;
            } else {
                return movie.getGenres().contains(genre);
            }
        });
    }

}
