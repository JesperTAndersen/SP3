import java.util.ArrayList;

public class Search {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    ArrayList<Series> seriesList = new ArrayList<>();

    public void searchOptions(){

    }
    private void searchByText(String searchByText){
        this.seriesLoad();



    }
    private void searchMovie(){

    }
    public void searchSeries(){

        this.seriesLoad();

        int count = 1;
        for (Series s : seriesList){
            System.out.println(count + ". " + s.toString());
            count++;
        }

    }
    private void searchGenre(){

    }

    public ArrayList<Series> seriesLoad(){

        ArrayList<String> seriesData = io.readData("data/series.txt");

        for (int i = 0; i < seriesData.size(); i++){

            String[] series = seriesData.get(i).split(";");

            String serieName = series[0];
            String releaseYear = series[1].trim();
            String genre = series[2].trim();
            String imdbRating = series[3].trim();
            String seasonsEpisodes = series[4].trim();

            Series s = new Series(serieName,releaseYear,genre,imdbRating,seasonsEpisodes);
            seriesList.add(s);
        }
        return seriesList;
    }

    private ArrayList<String> moviesLoad(){
        io.readData("data/movies.txt");

        return null;
    }

}
