import java.util.ArrayList;

public class Movie extends Media{
    private ArrayList<String> moviesList;

    public Movie(){}
    public Movie(String name, String releaseYear, String genre, String imdbRating) {
        super(name, releaseYear, genre, imdbRating);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
