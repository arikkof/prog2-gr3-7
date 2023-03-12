package at.ac.fhcampuswien.fhmdb.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {


    @BeforeAll
    public static void beforeAll(){

    }
    @Test
    public void initialize_returns_a_nonempty_list_of_movies(){
        List<Movie> movies;
        movies = Movie.initializeMovies();
        assertTrue(movies.size() > 0);
    }


}