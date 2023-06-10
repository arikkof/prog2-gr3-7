package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;

public abstract class SortState {
    protected HomeController homeController;
    public SortState(HomeController homeController){
        this.homeController = homeController;
    }
    // Sort according to curren SortState
    public abstract void sort();
    // set SortButton's Text to current SortState
    public abstract void updateSortButton();
    // set SortState to next one
    public abstract void toggleSortStates();
}
