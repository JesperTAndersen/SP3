import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    public void saveData(ArrayList<String> list, String path, String header){
        try {
            FileWriter writer = new FileWriter(path, true); //append = true makes so it overwrites instead.
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean fileIsEmpty = file.length() == 0;

            if (!fileExists || fileIsEmpty) {
                writer.write(header + "\n"); // Write header if file is new or empty
            }

            for (String s : list) {
                writer.write(s+"\n");
            }
            writer.close();
        }catch (IOException e) {
            System.out.println("problem: "+ e.getMessage());
        }
    }

    public ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();   //  "tess, 0"
                data.add(line);
            }
        } catch (FileNotFoundException e) {
        }
        return data;
    }

    public String[] readData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        try{
            //new scaner created
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header;

            int i = 0;  //counter
            while (scan.hasNextLine()) {
                String line = scan.nextLine();  //String line bliver instansieret som det scaneren har læst
                data[i]=line;                    //information tilføjes til et array
                i++;                             //counter går op
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found: "+ e.getMessage());
        }
        return data;
    }
}
