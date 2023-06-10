package at.ac.fhcampuswien.fhmdb.models;
import javafx.util.Callback;

// Restaurant

public interface ControllerFactory extends Callback<Class<?>, Object> {

    Object call(Class<?> aClass);
}

