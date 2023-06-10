package at.ac.fhcampuswien.fhmdb.contoller;

import javafx.util.Callback;

public class WatchlistControllerFactory implements Callback<Class<?>,Object> {

    // each ControllerFactory is singleton
    // and stores single one instance of controller, which it returns to javaFX
    private static WatchlistControllerFactory watchlistControllerFactoryInstance;
    private final WatchlistController watchlistControllerInstance = new WatchlistController();
    private WatchlistControllerFactory(){}
    public  static WatchlistControllerFactory getInstance() {
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
