import java.util.ArrayList;

public class Search {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    Media movie = new Movie();
    Media series = new Series();

    ArrayList<Series> seriesList = new ArrayList<>();
    ArrayList<Movie> moviesList = new ArrayList<>();

    public void searchOptions(User user) {
        ui.displayMessage("** Søge menu **");
        int userChoice = ui.promptNumeric("1. Søg blandt Film & Serier.\n2. Film Katalog.\n3. Series Katalog.\n4. Søg Genrer.");
        switch (userChoice) {
            case 1:
                String mediaChoice = searchByText();

                ui.displayMessage("** Medie muligheder **");

                int userChoice3 = ui.promptNumeric("1. Se Film/Serie.\n2. Tilføj til Min Liste.\n3. Gå tilbage.");
                switch (userChoice3) {
                    case 1:
                        series.playMedia(user, mediaChoice);
                        break;

                    case 2:
                        user.addMyList(mediaChoice);
                        break;

                    case 3:
                        searchOptions(user);
                        break;

                    default:
                        ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                        searchOptions(user);
                }
                break;

            case 2:

                //Switch-ception

                String movieChoice = searchMovie();

                ui.displayMessage("** Medie muligheder **");

                int userChoice1 = ui.promptNumeric("1. Se Film.\n2. Tilføj til Min Liste.\n3. Gå tilbage.");
                switch (userChoice1) {
                    case 1:
                        movie.playMedia(user, movieChoice);
                        break;

                    case 2:
                        user.addMyList(movieChoice);
                        break;

                    case 3:
                        searchOptions(user);
                        break;

                    default:
                        ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                        searchOptions(user);
                }

                //End of switch-ception

                break;

            case 3:
                String seriesChoice = searchSeries();

                //Start of Switch-ception.. AGAIN!

                ui.displayMessage("** Medie muligheder **");

                int userChoice2 = ui.promptNumeric("1. Se Serie.\n2. Tilføj til Min Liste.\n3. Gå tilbage.");
                switch (userChoice2) {
                    case 1:
                        series.playMedia(user, seriesChoice);
                        break;

                    case 2:
                        user.addMyList(seriesChoice);
                        break;

                    case 3:
                        searchOptions(user);
                        break;

                    default:
                        ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                        searchOptions(user);
                }


                //End of switch-ception.. AGAIN! :P

                break;

            case 4:
                //I case 4 laves søgefunktionen til genre.

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                searchOptions(user);

        }
    }
    private String searchByText(){
            String textSearch =  ui.promptText("Indtast Søgeord");
            ui.displayMessage("Søger efter: " + textSearch);
            ArrayList<String> results = new ArrayList<>();

            for (Movie m : moviesLoad()) {
                if (m.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                    results.add("Film: " + m.toString());
                }
            }

            for (Series s : seriesLoad()) {
                if (s.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                    results.add("Serie: " + s.toString());
                }
            }

            if (results.isEmpty()) {
                ui.displayMessage("Ingen resultater fundet.");
            } else {
                ui.displayList(results, "Resultater:");
            }
            int userChoice = ui.promptNumeric("Vælg en Film eller Serie på listen: ");
            return results.get(userChoice-1);
    }

    private String searchMovie(){

        int count = 1;
        for (Movie m : this.moviesLoad()){
            System.out.println(count + ". " + m.toString());
            count++;
        }

        int userChoice = ui.promptNumeric("Vælg en film på listen: ");
        return moviesList.get(userChoice-1).getName();

    }
    private String searchSeries(){

        int count = 1;
        for (Series s : this.seriesLoad()){
            System.out.println(count + ". " + s.toString());
            count++;
        }
        int userChoice = ui.promptNumeric("Vælg en serie på listen: ");
        return seriesList.get(userChoice-1).getName();

    }
    private void searchGenre(){

    }

    public ArrayList<Series> seriesLoad(){
        ArrayList<String> seriesData = io.readData("data/series.txt");
        seriesList.clear();

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

    private ArrayList<Movie> moviesLoad(){
        ArrayList<String> movieData = io.readData("data/movies.txt");
        moviesList.clear();

        for (int i = 0; i < movieData.size(); i++){
            String[] movies = movieData.get(i).split(";");

            String movieName = movies[0];
            String releaseYear = movies[1].trim();
            String genre = movies[2].trim();
            String imdbRating = movies[3].trim();

            Movie m = new Movie(movieName,releaseYear,genre,imdbRating);
            moviesList.add(m);
        }
        return moviesList;


    }

}
