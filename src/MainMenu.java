import java.util.ArrayList;

public class MainMenu {
    TextUI ui = new TextUI();
    FileIO io = new FileIO();
    Media m = new Movie();
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
                myList(acc, user);
                displayOptions(acc, user);
                break;

            case 3:
                haveWatchedList(acc, user);
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

    private void myList(Account acc, User user) {
        for (String s : user.getMyList()) {
            System.out.println(s);
        }
        boolean userChoiceYN = ui.promptBinary("Vil du se en film/serie fra listen Y/N: ");
        if (userChoiceYN) {
            ui.displayMessage("Her får du din liste igen: ");
            int count = 0;
            for (String s : user.getMyList().subList(1,user.getMyList().size())) {
                count++;
                System.out.println(count +". "+ s);
            }
            int userChoiceNum = ui.promptNumeric("Vælg hvilken film/serie du vil se fra din liste: ");

            //linjen tjekker om userChoiceNum er større end listen's størrelse eller om userChoiceNum er mindre end 0
            userChoiceNum = ui.promptIfNumCheck(userChoiceNum,user.getMyList().size());
            m.playMedia(user, user.getMyList().get(userChoiceNum));
            user.getMyList().remove(userChoiceNum);
        }
    }

    private void haveWatchedList(Account acc, User user) {
        for (String s : user.getHaveWatched()) {
            System.out.println(s);
        }
    }

    private void logout(Account acc, User user) {
        ArrayList<String> userDetails = io.readData("data/userDetails.csv");

        int count = 0;
        String tekst = "";
        boolean userFound = false;

        for (int i = 0; i < userDetails.size(); i++) {
            String[] values = userDetails.get(i).split(";");
            if (acc.getAccountName().equals(values[0]) && user.getName().equals(values[1])) {

                //Takes haveWatched and makes to a string
                String haveWatchedStrTmp = String.join(",", user.getHaveWatched());

                String myListStrTmp = String.join(",", user.getMyList());

                String test = acc.getAccountName() + ";" + user.getName() + ";" + haveWatchedStrTmp + ";" + myListStrTmp;
                count = i;
                tekst = test;

                userFound = true;
                break;

            }else {
                //Takes haveWatched and makes to a string
                String haveWatchedStrTmp = String.join(",", user.getHaveWatched());

                String myListStrTmp = String.join(",", user.getMyList());

                String test = acc.getAccountName() + ";" + user.getName() + ";" + haveWatchedStrTmp + ";" + myListStrTmp;

                tekst = test;
            }
        }

        if (userFound){
            userDetails.set(count, tekst);
        } else {
            userDetails.add(tekst);
        }

        io.saveData(userDetails, "data/userDetails.csv", "accountName;userName;HaveWatchedList;myList", false);
    }

        public void readUserDetails (Account acc, User user){
            ArrayList<String> userDetails = io.readData("data/userDetails.csv");

            for (int i = 0; i < userDetails.size(); i++) {
                String[] values = userDetails.get(i).split(";");
                if (acc.getAccountName().equals(values[0]) && user.getName().equals(values[1])) {

                    String haveWatchedList = values[2];
                    String myList = values[3];

                    String[] haveWatchedSeperated = haveWatchedList.split(",");
                    String[] myListSeperated = myList.split(",");

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





