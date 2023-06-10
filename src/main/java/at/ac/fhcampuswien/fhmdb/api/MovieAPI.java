package at.ac.fhcampuswien.fhmdb.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import at.ac.fhcampuswien.fhmdb.builders.MovieAPIRequestBuilder;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

// library for http responses and requests
import okhttp3.*;
// from JSON to Java Object and back
import com.google.gson.Gson;

// sample request URL: http://localhost:8080/movies?genre=COMEDY&releaseYear=2000
// fh-server API URL: "https://prog2.fh-campuswien.ac.at/movies"
// local API URL: "http://localhost:8080/movies"
public class MovieAPI {
    private static final String BASEURL = "https://prog2.fh-campuswien.ac.at/movies";
    // create Movie Objects from http response (json) and return in Array
    public static List<Movie> getAllMovies() {
        return getMovies(null, null, null, null);
    }
    public static List<Movie> getMovies(String query, Genre genre, String releaseYear, String ratingFrom) {
        String url = new MovieAPIRequestBuilder(BASEURL)
                .appendQuery(query)
                .appendGenre(genre)
                .appendReleaseYear(releaseYear)
                .appendRatingFrom(ratingFrom)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String responseBody = response.body().string();
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);
            return Arrays.asList(movies);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
