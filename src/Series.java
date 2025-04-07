import java.util.ArrayList;

public class Series extends Media{
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    private String name;
    private String releaseYear;
    private String genre;
    private String imdbRating;
    private String seriesSeasons;

    ArrayList<String> seriesList;

    public Series(String name, String releaseYear, String genre, String imdbRating, String seriesSeasons) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.imdbRating = imdbRating;
        this.seriesSeasons = seriesSeasons;
    }

    public ArrayList<String> getSeries(){
        return this.seriesList;
    }

    @Override
    public String toString() {
        return this.name + ", rated: " + imdbRating + " on IMDB";
    }
}