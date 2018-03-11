import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TestLecture {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();


        Scanner scan = new Scanner(new File("donnee.txt"));
        int line = 0;
        while (scan.hasNextLine()) {
            String lines = scan.nextLine();
            if (lines.equals(""))
                continue; // permet de supprimer les lignes superflues
            System.out.println("Request " + line + ": " + lines);
            int word = 0;
            Scanner scanString = new Scanner(lines);
            System.out.println();
            System.out.println("===== Affichage des mots =====");
            ArrayList<String> requete = new ArrayList<String>();
            while (scanString.hasNext()) {
                String mots = scanString.next();
                if (mots.charAt(0) == '\"') {
                    while (mots.charAt(mots.length() - 1) != '"' && mots.charAt(mots.length() - 2) != '"') {
                        mots += " " + scanString.next();
                    }
                }
                if (mots.charAt(mots.length() - 1) == '\n')
                    System.out.println("fin de requete");
                if (mots.charAt(mots.length() - 1) == ',')
                    mots = mots.substring(0, mots.length() - 1);
                if (mots.charAt(0) == '"')
                    mots = mots.substring(1, mots.length() - 1);
                System.out.println("Mot " + word + ": " + mots);
                requete.add(mots);
                word++;
            }
            data.add(requete);
            line++;
            System.out.println();
        }
        for (ArrayList<String> als : data) {
            System.out.println("===== requeste =====");
            for (String s : als) {
                System.out.println(s);
            }
        }
    }
}
