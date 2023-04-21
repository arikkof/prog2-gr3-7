package at.ac.fhcampuswien.fhmdb.data;

import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;
    void removeFromWatchlist(WatchlistMovieEntity movie){}
    List<WatchlistMovieEntity> getAll(){}
    void addToWatchlist(WatchlistMovieEntity movie){}

}
