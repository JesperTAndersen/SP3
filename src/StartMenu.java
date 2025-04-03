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
}
