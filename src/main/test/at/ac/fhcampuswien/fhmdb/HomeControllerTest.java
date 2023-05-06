package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;
    private Object Movie;

    @BeforeAll
    static void init() { homeController = new HomeController();}

    @Test
    void movies_and_observableMovies_are_equal(){
        //GIVEN
        homeController.initializeState();

        //WHEN & THEN
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void checks_if_all_movies_exist(){
        homeController.allMovies.clear();
        homeController.initializeState();
        //Movie.initializeMovies();
        assertEquals(15, homeController.allMovies.size());
    }
}