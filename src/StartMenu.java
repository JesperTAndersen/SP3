import java.io.File;
import java.util.ArrayList;

public class StartMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    private ArrayList<String> accData;


    public void runChill(){
        ui.displayMessage("Velkommen til Chill");
        int userChoice = ui.promptNumeric("1. Login \n2. Opret Konto \n");
        String username;
        String password;

        switch (userChoice){
            case 1:

                username = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Password: ");

                //accountLogin()
                break;

            case 2:
                username = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Password: ");
                createAccount(username, password);
                break;

        }
    }

    private void createAccount(String username, String password){
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
    }

