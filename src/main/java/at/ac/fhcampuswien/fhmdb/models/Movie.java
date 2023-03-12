package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Life Is Beautiful",
                "When an open-minden Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor and imagination to protect " +
                        "his son from the dangers around their camp.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Usual Suspects",
                "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie("Puss in Boots",
                "An outlaw cat, his childhood egg-friend and seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor and regain the trust of his mother and town.",
                Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)));
        movies.add(new Movie("Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)));
        movies.add(new Movie("The Wolf of Wall Street",
                "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)));
        movies.add(new Movie("March of the Penguins",
                "In the Antarctic, every March since the beginning of time, the quest begins to find the perfect mate and start a family.",
                Arrays.asList(Genre.DOCUMENTARY, Genre.FAMILY)));
        movies.add(new Movie("Braveheart",
                "Scottish warrior William Wallace leads his countrymen in a rebellion to free his homeland from the tyranny of King Edward I of England.",
                Arrays.asList(Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY)));
        movies.add(new Movie("American Psycho",
                "A wealthy New York City investment banking executive, Patrick Bateman, hides his alternate psychopathic ego from his co-workers and friends as he delves deeper into his violent, hedonistic fantasies.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.HORROR)));
        movies.add(new Movie("Chicago",
                "Two death-row murderesses develop a fierce rivalry while competing for publicity, celebrity, and a sleazy lawyer's attention.",
                Arrays.asList(Genre.COMEDY, Genre.CRIME, Genre.MUSICAL)));
        movies.add(new Movie("Star Trek",
                "In the 23rd Century, Captain James T. Kirk and the crew of the U.S.S. Enterprise explore the galaxy and defend the United Federation of Planets.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIFI)));
        movies.add(new Movie("Rocky",
                "A small-time Philadelphia boxer gets a supremely rare chance to fight the world heavyweight champion in a bout in which he strives to go the distance for his self-respect.",
                Arrays.asList(Genre.DRAMA, Genre.SPORT)));
        movies.add(new Movie("The Bourne Identity",
                "A man is picked up by a fishing boat, bullet-riddled and suffering from amnesia, before racing to elude assassins and attempting to regain his memory.",
                Arrays.asList(Genre.ACTION, Genre.MYSTERY, Genre.THRILLER)));
        movies.add(new Movie("The Matrix",
                "When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.",
                Arrays.asList(Genre.ACTION, Genre.SCIFI)));
        movies.add(new Movie("Saving Private Ryan",
                "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.",
                Arrays.asList(Genre.DRAMA, Genre.WAR)));
        movies.add(new Movie("Once Upon a Time in the West",
                "A mysterious stranger with a harmonica joins forces with a notorious desperado to protect a beautiful widow from a ruthless assassin working for the railroad.",
                Arrays.asList(Genre.WESTERN)));

        return movies;
    }

    public List<Genre> getGenres(){
        return genres;
    }

}
