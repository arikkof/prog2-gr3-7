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
    private List<Observer> observers = new ArrayList<>();
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

    public void removeFromWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException {
        try {
            if (dao != null) {
                this.dao.delete(dao.queryForEq("title", watchlistMovieEntity.getTitle()));
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
        }
    }

    public void addToWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException {
        try {
            if (dao == null) { return;}
            if (dao.queryForEq("title", watchlistMovieEntity.getTitle()).isEmpty()) { // not in db yet
                this.dao.createIfNotExists(watchlistMovieEntity);
                notifySubscribers(MOVIE_SUCCESSFULLY_ADDED_TO_WATCHLIST_MESSAGE);
                return;
            } // movie already in db
            notifySubscribers(MOVIE_ALREADY_IN_WATCHLIST_MESSAGE);
            throw new DatabaseException(MOVIE_ALREADY_IN_WATCHLIST_MESSAGE);
        } catch (SQLException | IllegalArgumentException e) {
            throw new DatabaseException(CONNECTION_ERROR_MESSAGE, e);
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
        observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers(String message) {
        observers.stream().forEach(o -> o.receiveUpdate(message));
    }
}
