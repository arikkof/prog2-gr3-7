package at.ac.fhcampuswien.fhmdb.interfaces;

// each instance only handles data of type T (specific type with which it was instantiated)
@FunctionalInterface
public interface ClickEventHandler<T> {
    void onClick(T t);
}
