import java.util.ArrayList;

public class Series extends Media{

    private String seriesSeasons;

    ArrayList<String> seriesList;

    public Series(){}

    public Series(String name, String releaseYear, String genre, String imdbRating, String seriesSeasons) {
        super(name, releaseYear, genre, imdbRating);
        this.seriesSeasons = seriesSeasons;
    }

    public ArrayList<String> getSeries(){
        return this.seriesList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}