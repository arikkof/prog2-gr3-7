package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;

public abstract class SortState {
    protected HomeController homeController;
    public SortState(HomeController homeController){
        this.homeController = homeController;
    }
    // Sort according to current SortState
    public abstract void sort();
    // set SortButton's Text to next SortState:
    public abstract void updateSortButton();
    // set to SortState, which can be expected after the current one:
    public abstract void toggleSortStates();
}
