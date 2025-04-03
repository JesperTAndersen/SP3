import java.io.File;
import java.util.ArrayList;

public class StartMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    private ArrayList<String> accData;


    void login(){
        ui.displayMessage("Velkommen til Chill");
        int userChoice = ui.promptNumeric("1. Login \n2. Opret Konto \n");


        switch (userChoice){
            case 1:

                    String username = ui.promptText("Indtast Brugernavn: ");
                    String password = ui.promptText("Indtast Password: ");



            case 2:
                username = ui.promptText("Indtast Brugernavn: ");
                password = ui.promptText("Indtast Password: ");
                createAccount(username, password);

                break;

        }
    }

    public void createAccount(String username, String password){
        accData = io.readData("data/accountDetails.csv");

        for (int i = 0; i < accData.size(); i++) {
            String[] values = accData.get(i).split(";");
            String newUserName = values[0];
            if (newUserName.equalsIgnoreCase(username)){
                ui.displayMessage("Brugernavn eksisterer allerede, prøv igen.");
                this.createAccount(username, password);
            }
            Account a = new Account(username, password);
            ui.displayMessage("Konto oprettet!");
            a.addAccount(a);
        }
    }
}
