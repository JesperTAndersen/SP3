import java.util.ArrayList;

public class Account {
    FileIO io = new FileIO();
    TextUI ui = new TextUI();

    private String accountName;
    private String password;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    Account(String accountName, String password){
        this.accountName = accountName;
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
    
    public User chooseUser(Account account){

        ArrayList<String> userData = new ArrayList<>();

        userData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < userData.size(); i++) {
            String[] values = userData.get(i).split(";");
            String searchAccount = values[0];
            if(accountName.equalsIgnoreCase(searchAccount)){
                String[] userList;
                String value = values[2];
                //This part of the code removes the [] from the user array..
                userList = value.substring(1, value.length()-1).split(",");

                ui.displayMessage("** Oversigt over brugere **");
                int count = 1;
                for (String u : userList) {
                    User user = new User(u);
                    users.add(user);
                    System.out.println(count +". " + u);
                    count++;
                }
                 int choice = ui.promptNumeric("Vælg bruger: ");
                return users.get(choice-1);
            }
        }
        return null;
    }

    public User getUsers(){
        for (User u : users){
            return u;
        }return null;
    }


    @Override
    public String toString() {
        return this.accountName +";"+ this.password +";"+ this.users.toString();
    }

    public void createUser(String name) {
        User newUser = new User(name);
        users.add(newUser);
        }
    }
