package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

// provides db's functionalities to UI
// why not make everything static?

public class WatchlistRepository {
    private Dao<WatchlistMovieEntity, Long> dao;
    public Dao<WatchlistMovieEntity, Long> getDao() {
        return dao;
    }
    public WatchlistRepository(){
        this.dao = DatabaseManager.getDatabase().getDao();
    }
    public void removeFromWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException {
        try {
            this.dao.delete(watchlistMovieEntity);
        } catch (SQLException e) {
            throw new DatabaseException("Connection to Database failed.", e);
        }
    }
    public void addToWatchlist(WatchlistMovieEntity watchlistMovieEntity) throws DatabaseException{
        try{
            this.dao.createIfNotExists(watchlistMovieEntity);
        }catch (SQLException e){
            throw new DatabaseException("Connection to Database failed.", e);
        }
    }
    public List<WatchlistMovieEntity> getAll() throws DatabaseException {
        try {
            return dao.queryForAll();
        } catch (SQLException e){
            throw new DatabaseException("Connection to Database failed.", e);
        }
    }

}
