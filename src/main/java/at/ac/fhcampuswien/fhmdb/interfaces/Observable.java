package at.ac.fhcampuswien.fhmdb.interfaces;

public interface Observable {
    public void subscribe(Observer observer);
    public void unsubscribe(Observer observer);
    public void notifySubscribers(String message, String headerText);
}
