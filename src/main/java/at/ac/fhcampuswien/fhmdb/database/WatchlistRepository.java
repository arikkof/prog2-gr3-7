package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;
    void removeFromWatchlist(WatchlistMovieEntity movie){}
    // List<WatchlistMovieEntity> getAll(){}
    void addToWatchlist(WatchlistMovieEntity movie){}

}
