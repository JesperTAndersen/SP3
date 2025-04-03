import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    Scanner sc = new Scanner(System.in);
    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>();  //Lave en beholder til at gemme brugerens valg

        while(choices.size() < limit){             //tjekke om brugeren skal vælge flere drinks
            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    public void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public int promptNumeric(String msg) {
        int numInput = 0;
        boolean valid = false;

        while (!valid) {
            displayMessage(msg);  // Ask the user a question
            String input = sc.nextLine();  // Get user input
            try {
                numInput = Integer.parseInt(input);  // Try converting to number
                valid = true;  // If successful, mark as valid
            } catch (NumberFormatException e) {
                displayMessage("Skriv et gyldigt tal.");  // Error message, loop repeats
            }
        }
        return numInput;  // Return the valid number
    }

    /* Med rekursion i stedet for while. Vi foretrækker den anden fordi metodekald kræver mere ram, da java laver en ny "stack frame" hver gang en metode kaldes
       public int promptNumeric(String msg){
        int numInput=0;
        System.out.println(msg);                      //Stille brugeren et spørgsmål
        String input = sc.nextLine();                 //Give brugere et sted at placere sit svar og vente på svaret
        try {
             numInput = Integer.parseInt(input);       //Konvertere svaret til et tal

        }catch(NumberFormatException e){
             numInput = promptNumeric("Skriv et tal. "+msg); // vigtigt at lave den rekursive kald som en tildeling - ellers returneres numInput fra det oprindelige kald (hvor numInput jo var ugyldigt)
        }
        return numInput;
    }
     */

    public String promptText(String msg){

        displayMessage(msg);                //Stille brugeren et spørgsmål
        String input = sc.nextLine();           //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }

    public boolean promptBinary(String msg) {
        String choice = this.promptText(msg);
        if(choice.equalsIgnoreCase("Y")){
            return true;
        } else if (choice.equalsIgnoreCase("N")) {
            return false;

        }else{
            promptBinary(msg);

        }
        return false;
    }

    public void displayMessage(String msg) {

        System.out.println("\n"+msg);
    }
}
