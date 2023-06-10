package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.HomeController;
import javafx.util.Callback;

public class HomeControllerFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> aClass) {
        try{
            return (HomeController) aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
