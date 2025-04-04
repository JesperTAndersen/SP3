import java.util.ArrayList;

public class User {
    public String name;
    private boolean isKid = false;
    private ArrayList<String> haveWatched;
    private ArrayList<String> myList;

    public User(String name){
        this.name = name;
        haveWatched = new ArrayList<>();
        myList = new ArrayList<>();
    }

    public User(String name, boolean isKid){
        this.name = name;
        this.isKid = isKid;
        haveWatched = new ArrayList<>();
        myList = new ArrayList<>();
    }

    public boolean getIsKid(){
        return this.isKid;
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
}
