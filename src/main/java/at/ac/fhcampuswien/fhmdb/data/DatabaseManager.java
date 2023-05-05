package at.ac.fhcampuswien.fhmdb.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

// wozu:
// establish connection zu DB
// user / pwd
// DAO: translates to and from SQL (provided by ORMLite), DAO.queryForAll()
public class DatabaseManager {
    public static final String DB_URL = "jdbc:h2: ./db/watchlistmoviesdb"; // "jdbc:h2:file: ./db/moveidb";
    public static final String username = "user";
    public static final String password = "password";
    private static ConnectionSource connectionSource;
    // Data Access Object:
    // provides crud
    Dao<WatchlistMovieEntity, Long> dao;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static DatabaseManager getDatabase() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        // if instance already exists just return it
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
        WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity("2", "title", "description", "genres", 2000, "url", 100, 8);
        dao.create(watchlistMovie);
        System.out.println(watchlistMovie);
    }

}