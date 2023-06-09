package at.ac.fhcampuswien.fhmdb.builders;
import at.ac.fhcampuswien.fhmdb.models.Genre;

public class MovieAPIRequestBuilder {
    private String queryString;
    private String genreString;
    private String releaseYearString;
    private String ratingFromString;
    private final String DELIMITER = "&";
    private final String baseUrl;
    public MovieAPIRequestBuilder(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public MovieAPIRequestBuilder appendQuery(String query){
        if(query==null){return this;}
        this.queryString = "query=" + query;
        return this;
    };
    public MovieAPIRequestBuilder appendGenre(Genre genre){
        if(genre==null){return this;}
        this.genreString = "genre=" + genre.toString();
        return this;
    };
    public MovieAPIRequestBuilder appendReleaseYear(String releaseYear){
        if(releaseYear==null){return this;}
        this.releaseYearString = "releaseYear=" + releaseYear;
        return this;
    };
    public MovieAPIRequestBuilder appendRatingFrom(String ratingFrom){
        if(ratingFrom==null){return this;}
        this.ratingFromString = "ratingFrom=" + ratingFrom;
        return this;
    };
    public String build(){
        StringBuilder url = new StringBuilder(baseUrl);
        if ((queryString != null && !queryString.isEmpty()) || genreString != null || releaseYearString != null || ratingFromString != null) {
            url.append("?");
            if (queryString != null && !queryString.isEmpty()) {
                url.append(queryString).append(DELIMITER);
            }
            if (genreString != null) {
                url.append(genreString).append(DELIMITER);
            }
            if (releaseYearString != null) {
                url.append(releaseYearString).append(DELIMITER);
            }
            if (ratingFromString != null) {
                url.append(ratingFromString).append(DELIMITER);
            }
        }
        return url.toString();
    }
}
