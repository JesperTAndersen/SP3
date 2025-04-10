import java.util.ArrayList;

public class MainMenu {
    private static  FileIO io = new FileIO();
    private static TextUI ui = new TextUI();
    private Media m = new Movie();
    
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
                myList(user);
                displayOptions(acc, user);
                break;

            case 3:
                haveWatchedList(user);
                displayOptions(acc, user);
                break;

            case 4:
                logout(acc,user);
                break;

            default:
                ui.displayMessage("Vælg venligst en gyldig valgmulighed: ");
                displayOptions(acc, user);
        }
    }

    private void myList(User user) {
        for (String s : user.getMyList()) {
            System.out.println(s);
        }
        boolean userChoiceYN = ui.promptBinary("Vil du se en film/serie fra listen Y/N: ");
        if (userChoiceYN) {
            ui.displayMessage("Her får du din liste igen: ");
            int count = 0;
            //we use a sublist to avoid the header in userDetails which is in the first index of the users myList
            for (String s : user.getMyList().subList(1,user.getMyList().size())) {
                count++;
                System.out.println(count +". "+ s);
            }
            int userChoiceNum = ui.promptNumeric("Vælg hvilken film/serie du vil se fra din liste: ");

            //checks if userChoiceNum is bigger than list.size() or if userChoiceNum is smaller than 0
            userChoiceNum = ui.promptIfNumCheck(userChoiceNum,user.getMyList().size());
            m.playMedia(user, user.getMyList().get(userChoiceNum));
            user.getMyList().remove(userChoiceNum);
        }
    }

    private void haveWatchedList(User user) {
        for (String s : user.getHaveWatched()) {
            System.out.println(s);
        }
    }

    private void logout(Account acc, User user) {
        ArrayList<String> userDetails = io.readData("data/userDetails.csv");

        int count = 0;
        String csvLine = "";
        boolean userFound = false;

        for (int i = 0; i < userDetails.size(); i++) {
            String[] values = userDetails.get(i).split(";");
            //check if existing account/user is the same as the one on values[0] and values[1]
            if (acc.getAccountName().equals(values[0]) && user.getName().equals(values[1])) {

                //Takes haveWatched and makes to a string, joined by ","
                String haveWatchedStrTmp = String.join(",", user.getHaveWatched());

                String myListStrTmp = String.join(",", user.getMyList());

                String tmpCsvLine = acc.getAccountName() + ";" + user.getName() + ";" + haveWatchedStrTmp + ";" + myListStrTmp;
                count = i;
                csvLine = tmpCsvLine;

                userFound = true;
                break;

            }else { //if no account/user was found

                String haveWatchedStrTmp = String.join(",", user.getHaveWatched());

                String myListStrTmp = String.join(",", user.getMyList());

                String tmpCsvLine = acc.getAccountName() + ";" + user.getName() + ";" + haveWatchedStrTmp + ";" + myListStrTmp;

                csvLine = tmpCsvLine;
            }
        }

        if (userFound){ //replaces existing line in .csv
            userDetails.set(count, csvLine);
        } else { //add new line
            userDetails.add(csvLine);
        }

        io.saveData(userDetails, "data/userDetails.csv", "accountName;userName;HaveWatchedList;myList", false);
    }

        public void readUserDetails (Account acc, User user){ //reads previous userdata from csv
            ArrayList<String> userDetails = io.readData("data/userDetails.csv");
            //runs through the entire list
            for (int i = 0; i < userDetails.size(); i++) {
                //splits the indexes from userDetails list to a string array
                String[] values = userDetails.get(i).split(";");
                //checks if the account name is the same as the one in values and same with user anf if true
                //then we set the users list to be the next indexes in values
                if (acc.getAccountName().equals(values[0]) && user.getName().equals(values[1])) {

                    String haveWatchedList = values[2];
                    String myList = values[3];
                    //splits the content and puts it in new arrays
                    String[] haveWatchedSeperated = haveWatchedList.split(",");
                    String[] myListSeperated = myList.split(",");
                    //adds the splitted content to the users lists
                    for (String s : haveWatchedSeperated) {
                        user.addToHaveWatched(s);
                    }
                    for (String s : myListSeperated) {
                        user.addMyList(s);
                    }
                }
            }
        }
}





