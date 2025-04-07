import java.util.ArrayList;

public class MainMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();


    public void displayOptions(Account acc, User user) {
        ui.displayMessage("** Hovedmenu **");
        int userChoice = ui.promptNumeric("1. Søg Film & Serier.\n2. Min Liste.\n3. Sete Film.\n4. Log ud.");
        switch (userChoice) {
            case 1:
                Search s = new Search();
                s.searchOptions(user);
                displayOptions(acc, user);
                break;

            case 2:
                myList(acc,user);
                displayOptions(acc, user);
                break;

            case 3:
                haveWatchedList(acc, user);
                displayOptions(acc, user);
                break;

            case 4:
                //Log ud..
                break;

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                displayOptions(acc, user);
        }
    }

    private void myList(Account acc, User user){
        ui.displayMessage("Din Liste: ");
        for (String s : user.getMyList()){
            System.out.println(s);
        }

    }

    private void haveWatchedList(Account acc, User user){
        ui.displayMessage("Din Sete Film: ");
        for (String s : user.getHaveWatched()){
            System.out.println(s);
        }
    }

    private void logout(Account acc, User user){
        ArrayList<String> userDetails = io.readData("data/userDetails.csv");

        for (int i = 0; i < userDetails.size(); i++){
            //Gennemløbe array her og træk  værdier ud til variabler
            //Derefter bland dem sammen med værdier fra myList og HaveWatchedList hvor end de skal hen og derefter lav en io.write
        }
    }



}

