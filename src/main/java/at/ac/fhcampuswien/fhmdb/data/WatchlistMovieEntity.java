package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.models.Genre;

import java.util.List;

public class WatchlistMovieEntity {

    long id;
    String apiid;
    String title;
    String description;
    String genres;
    int releaseYear;
    String imgUrl;
    int lengthInMinutes;
    double rating;
    String genresToString(List<Genre> genres){
        return new String();
    }
}
