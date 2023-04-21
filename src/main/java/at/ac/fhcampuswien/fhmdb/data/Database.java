package at.ac.fhcampuswien.fhmdb.data;

public class Database {
    String DB_URL;
    String username;
    String password;
    ConnectionSource connectionSource;
    Dao<WatchlistMovieEntity, Long> dao;

    void createConnectionSource(){}
    ConnectionSource getConnectionSource(){}
    void createTables(){}
    WatchlistDao getWatchlistDao(){}
}
