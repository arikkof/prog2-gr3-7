package at.ac.fhcampuswien.fhmdb.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import okhttp3.*; // library for http responses and requests
import com.google.gson.Gson; // from JSON to Java Object and back

// sample request URL: http://localhost:8080/movies?genre=COMEDY&releaseYear=2000
public class MovieAPI {
    private static final String URL = "http://localhost:8080/movies";// API URL
    private static final String DELIMITER = "&";
    // create request
    private static String buildUrl(String query, Genre genre, String releaseYear, String ratingFrom){
        StringBuilder url = new StringBuilder(URL);
        if((query != null && !query.isEmpty()) || genre != null || releaseYear != null || ratingFrom != null){
            url.append("?");
            if(query != null && !query.isEmpty()){
                url.append("query=").append(query).append(DELIMITER);
            }
            if(genre != null){
                url.append("genre=").append(genre).append(DELIMITER);
            }
            if (releaseYear != null) {
                url.append("releaseYear=").append(releaseYear).append(DELIMITER);
            }
            if (ratingFrom != null) {
                url.append("ratingFrom=").append(ratingFrom).append(DELIMITER);
            }
        }
        return url.toString();
    }

    // create Movie Objects from http response (json) and return in Array

    public static List<Movie> getAllMovies(){
        return getMovies(null, null, null, null);
    }
    public static List<Movie> getMovies(String query, Genre genre, String releaseYear, String ratingFrom){
        String url = buildUrl(query, genre, releaseYear, ratingFrom);
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .removeHeader("User-Agent")
                .addHeader("User-Agent", "http.agent")
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()){
            String responseBody = response.body().string();
            System.out.println(responseBody);
            Gson gson = new Gson();
            Movie[] movies = gson.fromJson(responseBody, Movie[].class);
            System.out.println("Hi, try worked");
            return Arrays.asList(movies);
        }
        catch (Exception e){
            System.err.println(e.getMessage() + "We are here.... ");
        }
        return new ArrayList<>();
    }
}
