package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
// stellt Funktionalitaeten fuer UI bereit
// kontrolliert: welche Datenquelle wird angesprochen? Z.B: Auch lokal. Von welchem UI-Element? Und wie?
// Uebermanager der Datenlayer!!!
public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao; // same ONE DAO created from DBManager
    void removeFromWatchlist(WatchlistMovieEntity movie){}
    // List<WatchlistMovieEntity> getAll(){}
    void addToWatchlist(WatchlistMovieEntity movie){}

}