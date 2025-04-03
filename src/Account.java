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
        ArrayList<String> accountData = new ArrayList<>();

        for(Account b : accounts){
            String s = b.toString();
            accountData.add(s);
        }

        io.saveData(accountData,"data/accountDetails.csv", "username;password;users");
    }

    public User getUsers(){
        for (User u : users){
            return u;
        } return null;
    }


    @Override
    public String toString() {
        return this.username +";"+ this.password +";"+ this.users;
    }
}
