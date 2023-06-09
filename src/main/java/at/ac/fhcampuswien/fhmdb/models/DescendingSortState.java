package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;

import java.util.Comparator;

public class DescendingSortState extends SortState{
    public DescendingSortState(HomeController homeController) {
        super(homeController);
    }
    @Override
    public void sort() {
        homeController.observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed()); // Sort Descescending
    }
    @Override
    public void updateSortButton() {
        homeController.sortBtn.setText("Sort (mix up)");
    }

    @Override
    public void toggleSortStates() {
        homeController.setSortState(new NoneSortState(homeController));
    }
}
