import java.util.ArrayList;

public class User {
    public String name;
    private ArrayList<String> haveWatched;
    private ArrayList<String> myList;

    public User(String name){
        this.name = name;
        haveWatched = new ArrayList<>();
        myList = new ArrayList<>();
    }

    public void addToHaveWatched(String mediaName) {
        this.haveWatched.add(mediaName);
    }

    public void addMyList(String mediaName) {
        this.myList.add(mediaName);
    }

    public ArrayList<String> getHaveWatched() {
        return this.haveWatched;
    }

    public ArrayList<String> getMyList() {
        return this.myList;
    }

    @Override
    public String toString() {
        return this.name+ ",";
    }

    public String getName() {
        return this.name;
    }
}
