public class MainMenu {
TextUI ui = new TextUI();


    public void displayOptions(Account a){
        User currentUser = a.chooseUser(a);

        int userChoice = ui.promptNumeric("1. Søg Film & Serier.\n2. Min Liste.\n3. Sete Film.\n4. Log ud.");
        switch (userChoice) {
            case 1:
                Search s = new Search();
                s.searchOptions();
                break;

            case 2:

                break;

            case 3:

                break;

            case 4:

                break;

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                displayOptions(a);

        /*
        int userChoice = ui.prompnumeric("Vælg menu")
        ui.displaymessage(""):
        1. "søg"

        2 "Min liste"
        3 "Sete film"
        4 "logout"

        switch case:
        case 1:
            searchOptions;
        case 2:
            myList();
         */
    }

    private void myList(){
        //Gennemløber csv fil for account, user og index plads 3 (0,1,2)
        //Som viser listen over brugerens "vil se liste"
    }

    private void haveWatchedList(){
        //Gennemløber csv fil for account, user og index plads 4 (0,1,2,3)
        //Som viser listen over brugerens "har set"
    }

    private void logout(){

    }
}
