package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.interfaces.Observable;
import at.ac.fhcampuswien.fhmdb.interfaces.Observer;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// provides db's functionalities to UI
// why not make everything static?

public class WatchlistRepository implements Observable {
    private Dao<WatchlistMovieEntity, Long> dao;
    private final static String CONNECTION_ERROR_MESSAGE = "Failed to connect to database.";
    private static final String MOVIE_ALREADY_IN_WATCHLIST_MESSAGE = "The selected movie is already in your watchlist.";
    private static final String MOVIE_SUCCESSFULLY_ADDED_TO_WATCHLIST_MESSAGE = "Movie successfully added to watchlist.";

    public Dao<WatchlistMovieEntity, Long> getDao() throws DatabaseException {
        return dao;
    }
    private Observer observer;
    private static WatchlistRepository instance;

    // Singleton: private Constructor
    private WatchlistRepository() throws DatabaseException {
        this.dao = DatabaseManager.getDatabase().getDao();
    }
    // this is the only way to retrieve the instance
    public static WatchlistRepository getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    public void removeFromWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            dao.delete(dao.queryForMatching(movie));
        } catch (Exception e) {
            throw new DatabaseException("Error while removing from watchlist");
        }
    }

    public void printObservers(){
        //observers.stream().forEach(o -> System.out.println("Observer: " + o.toString()));
    }

    public void addToWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if(count > 0){
                notifySubscribers(MOVIE_ALREADY_IN_WATCHLIST_MESSAGE);
                return;
            }
            if (count == 0) {
                dao.create(movie);
            }
            if(isOnWatchlist(movie)){
                notifySubscribers(MOVIE_SUCCESSFULLY_ADDED_TO_WATCHLIST_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Error while adding to watchlist");
        }
    }

    public boolean isOnWatchlist(WatchlistMovieEntity movie) throws DatabaseException {
        try {
            return dao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DatabaseException("Error while checking if movie is on watchlist");
        }
    }

    public List<WatchlistMovieEntity> getAll() throws DatabaseException {
        try {
            if (dao != null) {
                return dao.queryForAll();
            } else {
                return Collections.emptyList();
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
        }
    }

    @Override
    public void subscribe(Observer observer) {
        //observers.add(observer);
        this.observer = observer;
    }

    @Override
    public void unsubscribe(Observer observer) {
        //observers.remove(observer);
        this.observer = null;
    }

    @Override
    public void notifySubscribers(String message) {
        //observers.stream().forEach(o -> {System.out.println(o); o.receiveUpdate(message);});
        observer.receiveUpdate(message);
    }
}
