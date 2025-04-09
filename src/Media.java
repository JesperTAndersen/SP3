public abstract class Media {
    TextUI ui = new TextUI();

    private String name;
    private String releaseYear;
    private String genre;
    private String imdbRating;

    public Media(){}
    public Media(String name, String releaseYear, String genre, String imdbRating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.imdbRating = imdbRating;
    }

    public void playMedia(User u, String mediaName) {
        u.addToHaveWatched(mediaName);
        ui.displayMessage("Afspiller nu: " + mediaName + "\n" + mediaName + " er tilføjet til sete film & serier");

    }

    @Override
    public String toString() {
        return name + ", Rated: " + imdbRating + " on IMDB.";
    }


    public String getName() {
        return this.name;
    }
}
