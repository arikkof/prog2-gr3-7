package at.ac.fhcampuswien.fhmdb.data;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

// Mapper Klasse: Von Movie zu WatchlistMovieEntity (keine Listen)
// Representation of table entry - One single entry in the database
// naming convention: ...Entity
// create Tables and Fields with Annotations
@DatabaseTable(tableName = "watchlistMovie")
public class WatchlistMovieEntity {

    @DatabaseField(generatedId = true) // generate ID automagically!
    private long id; // Long ?
    @DatabaseField()
    private String apiId;
    @DatabaseField()
    private String title;
    @DatabaseField()
    private String description;
    @DatabaseField()
    private String genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private String imgUrl;
    @DatabaseField()
    private int lengthInMinutes;
    @DatabaseField()
    private double rating;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    /*private String genresToString(List<Genre> genres){
        return new String();
    }*/
}
