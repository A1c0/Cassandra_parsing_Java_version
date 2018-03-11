import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScanMots{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("donnee.txt"));
        int line = 1;
        System.out.println("==============================");
        System.out.println("===== Affichage des mots =====");
        System.out.println("==============================");
        System.out.println();

        while (scan.hasNext()) {
            String mots = scan.next();
            if (mots.charAt(0) == '\"'){
                while (mots.charAt(mots.length()-1) != '"' && mots.charAt(mots.length()-2) != '"'){
                    mots += " " + scan.next();
                }
            }
            if (mots.charAt(mots.length()-1) == '\n')
                System.out.println("fin de requete");
            if (mots.charAt(mots.length()-1) == ',')
                mots = mots.substring(0, mots.length()-1);
            if (mots.charAt(0) == '"')
                mots = mots.substring(1, mots.length()-1);
            System.out.println("Mot " + line + ": " + mots);
            line++;

        }
    }
}