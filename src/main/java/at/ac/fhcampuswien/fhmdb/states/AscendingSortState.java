package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;

public class AscendingSortState extends SortState{

    public AscendingSortState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sort() {
        homeController.observableMovies.sort(Comparator.comparing(Movie::getTitle)); // Sort Ascending
    }
    @Override
    public void updateSortButton() { homeController.sortBtn.setText("Sort (desc)");} // In Anticipation

    @Override
    public void toggleSortStates() {
        homeController.setSortState(new DescendingSortState(homeController)); // In Anticipation
    }
}
