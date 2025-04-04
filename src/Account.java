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
    
    public int chooseUser(){

        ArrayList<String> userData = new ArrayList<>();

        userData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < userData.size(); i++) {
            String[] values = userData.get(i).split(";");
            String searchAccount = values[0];
            if(this.accountName.equalsIgnoreCase(searchAccount)){
                //This part of the code removes the [] from the user array..
                String[] userList;
                String value = values[2];
                userList = value.substring(1, value.length()-1).split(",");

                int count = 1;
                for (String u : userList) {
                    User user = new User(u);
                    System.out.println(count +". " + u);
                    count++;
                }

                return ui.promptNumeric("vælg bruger: ");
            }
        }
        return 0;
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
