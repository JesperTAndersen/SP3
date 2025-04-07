public class MainMenu {
    TextUI ui = new TextUI();


    public void displayOptions(Account acc, User user) {

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
        System.out.println(user.getMyList());
    }

    private void haveWatchedList(Account acc, User user){
        ui.displayMessage("Din Sete Film: ");
        System.out.println(user.getHaveWatched());
        }
/*
    private void logout(Account acc, User user){

    }

 */

    }

