import java.util.ArrayList;

public class StartMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    private String accountName;

    private ArrayList<String> accData;


    public void runChill(){
        ui.displayMessage("Velkommen til Chill");
        int userChoice = ui.promptNumeric("1. Login\n2. Opret Konto");

        String accountName = ui.promptText("Indtast Brugernavn: ");
        String password = ui.promptText("Indtast Kodeord: ");

        switch (userChoice){
            case 1:
                if(accountLogin(accountName,password)){
                    this.loginSucces(this.accountName, password);
                 }
                break;

            case 2:
                createAccount(accountName, password);
                this.loginSucces(this.accountName, password);
                break;
        }

    }

    private void createAccount(String accountName, String password) {
        accData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < accData.size(); i++) {
            String[] values = accData.get(i).split(";");
            String newUserName = values[0];
            if (newUserName.equalsIgnoreCase(accountName)) {
                ui.displayMessage("Brugernavn eksisterer allerede, prøv igen.");
                accountName = ui.promptText("Skriv nyt Brugernavn: ");
                this.createAccount(accountName, password);
                return;
            }
        }
        Account a = new Account(accountName, password);
        ui.displayMessage("Konto oprettet!");

        a.createUser(ui.promptText("Ingen brugere fundet, opret ny med følgende navn: "));

        // tilføj flere users før account create?
        a.addAccount(a);

    }

    public boolean accountLogin(String accountName, String password){
        accData = io.readData("data/accountDetails.csv");

        while (true) {
            for (String line : accData) {
                String[] values = line.split(";");
                if (values.length < 2) continue; // Safety check to avoid index errors if csv file is missing values on line ( accountName;password, where one could be missing, then it skips that line and moves on to the next)

                String searchUsername = values[0];
                String searchPassword = values[1];

                if (searchUsername.equalsIgnoreCase(accountName) && searchPassword.equals(password)) {
                    ui.displayMessage("Korrekt! Logger ind...");
                    this.accountName = searchUsername;
                    return true;
                }
            }

            // If no match was found, prompt error message and run the while loop until accountLogin returns true..
            ui.displayMessage("Brugernavn eller kodeord er forkert! .. Prøv igen");
            //Prompts the user for accountname and password again to run back into the while loop
            accountName = ui.promptText("Brugernavn: ");
            password = ui.promptText("Kodeord: ");

        }

    }

    public void loginSucces(String accountName, String password){
        Account a = new Account(accountName, password);
        MainMenu m = new MainMenu();
        m.displayOptions(a);
    }
}

