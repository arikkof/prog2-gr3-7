package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;

public class NoneSortState extends SortState{
    public NoneSortState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sort() {
        //TODO: real random sorting
        homeController.observableMovies.sort(Comparator.comparing(Movie::getId));
    }

    @Override
    public void updateSortButton() {
        homeController.sortBtn.setText("Sort asc");
    }

    @Override
    public void toggleSortStates() {
        homeController.setSortState(new AscendingSortState(homeController));
    }
}
