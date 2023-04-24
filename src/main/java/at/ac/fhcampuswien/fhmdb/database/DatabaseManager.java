package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    public static final String DB_URL = "jdbc:h2:file: ./db/watchlistmoviesdb";
    public static final String username = "user";
    public static final String password = "password";
    private static ConnectionSource connectionSource;
    // Data Access Object:
    // provides crud
    Dao<WatchlistMovieEntity, Long> dao;
    private static DatabaseManager instance;

    // private Constructor
    private DatabaseManager() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static DatabaseManager getDatabase() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        // if instance already exists just return it
        return instance;
    }

    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }

    private static void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, username, password);
    }
    public void testDB() throws SQLException {
        WatchlistMovieEntity movie = new WatchlistMovieEntity("100", "The Box", "A box..", "Action, Horror", 2023, "https://www.fhcampuswien.ac.at", 112, 100);
        dao.create(movie);
    }

}
