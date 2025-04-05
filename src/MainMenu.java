public class MainMenu {
TextUI ui = new TextUI();


    public void displayOptions(Account a){
        a.chooseUser();
        Search s = new Search();
        s.seriesLoad();
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
