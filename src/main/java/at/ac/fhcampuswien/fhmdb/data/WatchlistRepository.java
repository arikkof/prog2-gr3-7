package at.ac.fhcampuswien.fhmdb.data;

import com.j256.ormlite.dao.Dao;
import java.util.List;

// stellt Funktionalitaeten der Datenbank fuer UI bereit
// kontrolliert: welche Datenquelle wird angesprochen? Z.B: Auch lokal. Von welchem UI-Element? Und wie?
// Uebermanager der Datenlayer!!!

public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao; // same ONE DAO created from DBManager - how?
    //TODO: implement method to rm Movie (WatchlistMovieEntity) from DB
    void removeFromWatchlist(WatchlistMovieEntity movie){}

    //TODO: implement method to add Movie (WatchlistMovieEntity) to DB (if !exists)
    void addToWatchlist(WatchlistMovieEntity movie){

    }
    //List<WatchlistMovieEntity> getAll(){}

}
