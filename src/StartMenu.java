import java.io.File;
import java.util.ArrayList;

public class StartMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    private ArrayList<String> accData;


    public void runChill(){
        ui.displayMessage("Velkommen til Chill");
        int userChoice = ui.promptNumeric("1. Login\n2. Opret Konto");
        String username;
        String password;

        switch (userChoice){
            case 1:
                username = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Kodeord: ");
                accountLogin(username,password);
                break;
            case 2:
                username = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Kodeord: ");
                createAccount(username, password);
                break;

        }
    }

    private void createAccount(String username, String password) {
        accData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < accData.size(); i++) {
            String[] values = accData.get(i).split(";");
            String newUserName = values[0];
            if (newUserName.equalsIgnoreCase(username)) {
                ui.displayMessage("Brugernavn eksisterer allerede, prøv igen.");
                username = ui.promptText("Skriv nyt Brugernavn: ");
                this.createAccount(username, password);
                return;
            }
        }
        Account a = new Account(username, password);
        ui.displayMessage("Konto oprettet!");
        a.addAccount(a);

    }

    public boolean accountLogin(String username, String password){
        accData = io.readData("data/accountDetails.csv");

        while (true) {
            for (String line : accData) {
                String[] values = line.split(";");
                if (values.length < 2) continue; // Safety check to avoid index errors if csv file is missing values on line ( username;password, where one could be missing, then it skips that line and moves on to the next)

                String searchUsername = values[0];
                String searchPassword = values[1];

                if (searchUsername.equalsIgnoreCase(username) && searchPassword.equals(password)) {
                    ui.displayMessage("Korrekt! Logger ind...");
                    return true;
                }
            }

            // If no match was found, prompt error message and run the while loop until accountLogin returns true
            ui.displayMessage("Brugernavn eller kodeord er forkert! .. Prøv igen");

            // while true, creates infinite loop, until a return is made (return true;)

            username = ui.promptText("Brugernavn: ");
            password = ui.promptText("Kodeord: ");

            if (accountLogin(username, password)) {
                return true;
            }
        }


    }

}

