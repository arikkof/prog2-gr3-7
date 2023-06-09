package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;

import java.util.Comparator;

public class AscendingSortState extends SortState{

    public AscendingSortState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sort() {
        homeController.observableMovies.sort(Comparator.comparing(Movie::getTitle)); // Sort Ascending
    }
    public void updateSortButton(){
        homeController.sortBtn.setText("Sort (dsc)");
    }

    @Override
    public void toggleSortStates() {
        homeController.setSortState(new DescendingSortState(homeController));
    }
}
