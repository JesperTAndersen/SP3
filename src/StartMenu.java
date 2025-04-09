import java.util.ArrayList;

public class StartMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();


    private ArrayList<String> accData;


    public void runChill() {
        ui.displayMessage("Velkommen til Chill");
        int userChoice = ui.promptNumeric("1. Login\n2. Opret Konto");

        String accountName;
        String password;

        switch (userChoice) {
            case 1:
                accountName = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Kodeord: ");
                loginSucces(accountLogin(accountName, password), password);
                break;

            case 2:
                accountName = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Kodeord: ");
                loginSucces(createAccount(accountName, password));
                break;

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                runChill();
        }

    }

    private Account createAccount(String accountName, String password) {
        accData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < accData.size(); i++) {
            String[] values = accData.get(i).split(";");
            String newUserName = values[0];
            if (newUserName.equalsIgnoreCase(accountName)) {
                ui.displayMessage("Brugernavn eksisterer allerede, prøv igen.");
                String tmpaccountName = ui.promptText("Skriv nyt Brugernavn: ");
                return this.createAccount(tmpaccountName, password);
            }
        }
        Account a = new Account(accountName, password);
        ui.displayMessage("Konto oprettet!");

        a.createUser(ui.promptText("Ingen brugere fundet, opret ny med følgende navn: "));

        boolean multipleUser = true;

        while(multipleUser) {

            if (ui.promptBinary("Vil du tilføje flere brugere? Y/N: ")) {
                a.createUser(ui.promptText("Indtast Brugernavn: "));

            } else {
                multipleUser = false;
            }

        }
        a.addAccount(a);
        return a;
    }

    public String accountLogin(String accountName, String password) {
        accData = io.readData("data/accountDetails.csv");

        while (true) {
            for (String line : accData) {
                String[] values = line.split(";");
                if (values.length < 2)
                    continue; // Safety check to avoid index errors if csv file is missing values on line ( accountName;password, where one could be missing, then it skips that line and moves on to the next)

                String searchUsername = values[0];
                String searchPassword = values[1];

                if (searchUsername.equalsIgnoreCase(accountName) && searchPassword.equals(password)) {
                    ui.displayMessage("Korrekt! Logger ind...");
                    accountName = searchUsername;
                    return accountName;
                }
            }

            // If no match was found, prompt error message and run the while loop until accountLogin returns true..
            ui.displayMessage("Brugernavn eller kodeord er forkert! .. Prøv igen");
            //Prompts the user for accountname and password again to run back into the while loop
            accountName = ui.promptText("Brugernavn: ");
            password = ui.promptText("Kodeord: ");

        }

    }

    public void loginSucces(Account acc) {
        MainMenu m = new MainMenu();
        User user = acc.chooseUser(acc);
        m.readUserDetails(acc,user);
        m.displayOptions(acc, user);
    }

    public void loginSucces(String accountName, String password) {
        Account acc = new Account(accountName, password);
        MainMenu m = new MainMenu();
        User user = acc.chooseUser(acc);
        m.readUserDetails(acc, user);
        m.displayOptions(acc, user);
    }

}
