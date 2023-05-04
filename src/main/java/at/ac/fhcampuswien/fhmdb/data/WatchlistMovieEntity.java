package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;
@DatabaseTable(tableName = "Movies")
public class WatchlistMovieEntity {

    @DatabaseField
    long id;
    @DatabaseField
    String apiId;
    @DatabaseField
    String title;
    @DatabaseField
    String description;
    @DatabaseField
    String genres;
    @DatabaseField
    int releaseYear;
    @DatabaseField
    String imgUrl;
    @DatabaseField
    int lengthInMinutes;
    @DatabaseField
    double rating;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(long id, String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.id = id;
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    String genresToString(List<Genre> genres){
        return new String();
    }
}
