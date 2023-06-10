package at.ac.fhcampuswien.fhmdb.interfaces;
@FunctionalInterface
public interface Observer {
    public void receiveUpdate(String message);
}
