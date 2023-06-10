package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.WatchlistController;

public class WatchlistControllerFactory implements ControllerFactory{

    // each ControllerFactory singleton
    // stores instance of controller, which it returns to javaFX
    private static WatchlistControllerFactory watchlistControllerFactoryInstance;
    private WatchlistController watchlistControllerInstance = new WatchlistController();
    private WatchlistControllerFactory(){}
    public  static WatchlistControllerFactory getWatchlistControllerFactoryInstance() {
        if(watchlistControllerFactoryInstance ==null){
            watchlistControllerFactoryInstance = new WatchlistControllerFactory();
        }
        return watchlistControllerFactoryInstance;
    }
    @Override
    public Object call(Class<?> aClass) {
        try {
            return watchlistControllerInstance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
