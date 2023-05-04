package at.ac.fhcampuswien.fhmdb.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class Database {
    String DB_URL = "jdbc:h2: ./db/moviedb";
    String username = "user";
    String password = "pw";
    ConnectionSource connectionSource;
    Dao<WatchlistMovieEntity, Long> dao;
    private static Database instance;

    private Database() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
    }

    ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

    //WatchlistDao getWatchlistDao() {}

    public void testDB() throws SQLException {
        WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity(1, "2", "title", "description", "genres", 2000, "url", 100, 8);
        dao.create(watchlistMovie);
        System.out.println(watchlistMovie);
    }

}