import java.util.ArrayList;

public class Search {
    private static TextUI ui = new TextUI();
    private static FileIO io = new FileIO();
    private static Media movie = new Movie();
    private static Media series = new Series();

    private ArrayList<Series> seriesList = new ArrayList<>();
    private ArrayList<Movie> moviesList = new ArrayList<>();
    private ArrayList<String> allGenres = new ArrayList<>();

    public void searchOptions(User user) {
        ui.displayMessage("** Søge menu **");
        int userChoice = ui.promptNumeric("1. Søg blandt Film & Serier.\n2. Film Katalog.\n3. Series Katalog.\n4. Søg Genrer.");

        switch (userChoice) {
            case 1: //Search Film/Series

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

            case 2: //FilmCatalogue

                String movieChoice = loadFilmCatalogue();
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
                break;

            case 3: //SeriesCatalogue
                String seriesChoice = loadSeriesCatalogue();
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
                break;

            case 4: //SearchGenre
                String genreChoice = searchByGenre();
                ui.displayMessage("** Medie muligheder **");

                int userChoice4 = ui.promptNumeric("1. Se Film/Serie.\n2. Tilføj til Min Liste.\n3. Gå tilbage.");
                switch (userChoice4) {
                    case 1:
                        series.playMedia(user, genreChoice);
                        break;

                    case 2:
                        user.addMyList(genreChoice);
                        break;

                    case 3:
                        searchOptions(user);
                        break;

                    default:
                        ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                        searchOptions(user);
                }

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                searchOptions(user);
        }
    }

    private String searchByText() {
        String textSearch = ui.promptText("Indtast Søgeord");
        ui.displayMessage("Søger efter: " + textSearch);
        ArrayList<Media> results = new ArrayList<>();

        for (Movie m : moviesLoad()) { //Runs through list and adds to results
            if (m.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                results.add(m);
            }
        }

        for (Series s : seriesLoad()) { //Runs through list and adds to results
            if (s.getName().toLowerCase().contains(textSearch.toLowerCase())) {
                results.add(s);
            }
        }

        if (results.isEmpty()) {
            ui.displayMessage("Ingen resultater fundet.");
        } else {
            int count = 1;
            for (Media m : results) {
                if (m instanceof Movie movie) {
                    System.out.println(count + ". " + "Film: " + movie);
                } else if (m instanceof Series s) {
                    System.out.println(count + ". " + "Serie: " + s);
                }
                count++;
            }
        }
        int userChoice = ui.promptNumeric("Vælg en Film eller Serie på listen: ");

        userChoice = ui.promptIfNumCheck(userChoice, results.size());
        return results.get(userChoice - 1).getName();
    }

    private String loadFilmCatalogue() {

        int count = 1;
        for (Movie m : this.moviesLoad()) {
            System.out.println(count + ". " + m.toString());
            count++;
        }

        int userChoice = ui.promptNumeric("Vælg en film på listen: ");

        userChoice = ui.promptIfNumCheck(userChoice, moviesList.size());
        return moviesList.get(userChoice - 1).getName();

    }

    private String loadSeriesCatalogue() {

        int count = 1;
        for (Series s : this.seriesLoad()) {
            System.out.println(count + ". " + s.toString());
            count++;
        }
        int userChoice = ui.promptNumeric("Vælg en serie på listen: ");
        userChoice = ui.promptIfNumCheck(userChoice, seriesList.size());
        return seriesList.get(userChoice - 1).getName();

    }

    private String searchByGenre() {
        if (allGenres.isEmpty()) {
            moviesLoad();
            seriesLoad();
        }

        System.out.println("Vælg en genre:");
        for (int i = 0; i < allGenres.size(); i++) {
            System.out.println((i + 1) + ". " + allGenres.get(i));
        }

        int genreChoice = ui.promptNumeric("Indtast nummeret på den ønskede genre:");
        genreChoice = ui.promptIfNumCheck(genreChoice, allGenres.size());

        String selectedGenre = allGenres.get(genreChoice - 1);
        ui.displayMessage("Søger efter genre: " + selectedGenre);

        ArrayList<Media> results = new ArrayList<>();

        for (Movie m : moviesList) {
            if (m.getGenre().toLowerCase().contains(selectedGenre.toLowerCase())) {
                results.add(m);
            }
        }

        for (Series s : seriesList) {
            if (s.getGenre().toLowerCase().contains(selectedGenre.toLowerCase())) {
                results.add(s);
            }
        }

        if (results.isEmpty()) {
            ui.displayMessage("Ingen resultater fundet.");
            return null;
        } else {
            int count = 1;
            for (Media m : results) {
                if (m instanceof Movie movie) {
                    System.out.println(count + ". Film: " + movie);
                } else if (m instanceof Series series) {
                    System.out.println(count + ". Serie: " + series);
                }
                count++;
            }
        }

        int userChoice = ui.promptNumeric("Vælg en Film eller Serie på listen: ");
        userChoice = ui.promptIfNumCheck(userChoice, results.size());
        return results.get(userChoice - 1).getName();
    }

    public ArrayList<Series> seriesLoad() {
        if (allGenres.isEmpty()) {
            ArrayList<String> seriesData = io.readData("data/series.txt");
            seriesList.clear();

            for (int i = 0; i < seriesData.size(); i++) {
                String[] series = seriesData.get(i).split(";");

                String serieName = series[0];
                String releaseYear = series[1].trim();
                String genre = series[2].trim();
                String imdbRating = series[3].trim();
                String seasonsEpisodes = series[4].trim();

                String[] splitGenres = genre.split(","); //splits and add to genre array
                for (String genres : splitGenres) {
                    String g = genres.trim();
                    if (!allGenres.contains(g)) {
                        allGenres.add(g);
                    }
                }

                // Add the series to the list of series
                Series s = new Series(serieName, releaseYear, genre, imdbRating);
                seriesList.add(s);
            }
        }
        return seriesList;
    }

    private ArrayList<Movie> moviesLoad() {
        if (allGenres.isEmpty()) {
            ArrayList<String> movieData = io.readData("data/movies.txt");
            moviesList.clear();

            for (int i = 0; i < movieData.size(); i++) {
                String[] movies = movieData.get(i).split(";");

                String movieName = movies[0];
                String releaseYear = movies[1].trim();
                String genre = movies[2].trim();
                String imdbRating = movies[3].trim();

                String[] splitGenres = genre.split(","); //splits and add to genre array
                for (String genres : splitGenres) {
                    String g = genres.trim();
                    if (!allGenres.contains(g)) {
                        allGenres.add(g);
                    }
                }

                // Add the movie to the list of movies
                Movie m = new Movie(movieName, releaseYear, genre, imdbRating);
                moviesList.add(m);
            }
        }
        return moviesList;
    }
}


