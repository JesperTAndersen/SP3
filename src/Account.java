import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private ArrayList<User> users;

    Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void createAccount(){}

    public User getUsers(){
        for (User u : users){
            return u;
        }
    }
}
