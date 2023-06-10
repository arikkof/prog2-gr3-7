package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.WatchlistController;
import javafx.util.Callback;

public class WatchlistControllerFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> aClass) {
        try{
            return (WatchlistController) aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
