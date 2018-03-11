import java.io.*;
import java.util.Scanner;

public class TestLecture {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("donnee.txt"));
        int line = 1;
        while (scan.hasNextLine()) {
            String lines = scan.nextLine();
            if (lines.equals(""))
                continue; // permet de supprimer les lignes superflues
            System.out.println("Request " + line + ": " + lines);
            Scanner scanString = new Scanner(lines);
            int word = 1;
            System.out.println("==============================");
            System.out.println("===== Affichage des mots =====");
            System.out.println("==============================");
            System.out.println();

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
                word++;
            }
            line++;
        }
    }
}
