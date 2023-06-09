package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.builders.MovieAPIRequestBuilder;
import at.ac.fhcampuswien.fhmdb.models.Genre;

public class Testing {
    private static final String BASEURL = "https://prog2.fh-campuswien.ac.at/movies";
    public static void main(String[] args) {
        String url = new MovieAPIRequestBuilder(BASEURL)
                .appendQuery("")
                .appendGenre(Genre.COMEDY)
                .appendReleaseYear(null)
                .appendRatingFrom(null)
                .build();
        System.out.println(url);
        String test = new StringBuilder("Test").append((String)null).toString();
        System.out.println(test);
    }
}
