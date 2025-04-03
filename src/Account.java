import java.util.ArrayList;

public class Account {
    FileIO io = new FileIO();
    TextUI ui = new TextUI();

    private String username;
    private String password;
    private ArrayList<User> users;
    private ArrayList<Account> accounts = new ArrayList<>();

    Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addAccount(Account a){
        accounts.add(a);
    }

    public User getUsers(){
        for (User u : users){
            return u;
        } return null;
    }
}
