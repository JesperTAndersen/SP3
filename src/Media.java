import java.util.ArrayList;

public abstract class Media {
    protected String name;
    protected int releaseYear;
    protected ArrayList<String> genre;
    protected double imdbRating;

    public void playMedia(){

    }
    private void exitOptions(){

    }

    @Override
    public String toString() {
        return name + ", Rated: " + imdbRating + " on IMDB.";
    }
}
