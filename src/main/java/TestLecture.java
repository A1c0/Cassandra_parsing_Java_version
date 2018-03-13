import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TestLecture {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        ArrayList<String> Requete_prete = new ArrayList<String>();


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
        System.out.println("===== requeste =====");
        for (ArrayList<String> als : data) {
            //requete pour l'habitat
            String date = als.get(2).substring(1, 5) + "-" + als.get(2).substring(5, 7) + "-" + als.get(2).substring(7, 9);
            System.out.println("date = " + date);
            String heure = als.get(2).substring(9, 11) + ":" + als.get(2).substring(11, 13) + ":" + als.get(2).substring(13, 15);
            System.out.println("heure = " + heure);
            String requete = "INSERT INTO habitat(id, milieu, typeHab, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '" + als.get(0) + "', '" + als.get(1) + "', '" + als.get(2) + "', " + als.get(3) + ", " + als.get(4) + ", " + als.get(5) + ", " + als.get(6) + ", '" + date + "', '" + heure + "');";
            System.out.println(requete);
            int iterator  = 7;
            while (iterator < als.size()){
                if (als.get(iterator + 1).charAt(0) == 'i'){ //GESTIONS DES ELEMENTS INVASIFS
                    System.out.println("Insertion d'un élément invasifs");
                    date = als.get(iterator + 1).substring(1, 5) + "-" + als.get(iterator + 1).substring(5, 7) + "-" + als.get(iterator + 1).substring(7, 9);
                    System.out.println("date = " + date);
                    heure = als.get(iterator + 1).substring(9, 11) + ":" + als.get(iterator + 1).substring(11, 13) + ":" + als.get(iterator + 1).substring(13, 15);
                    System.out.println("heure = " + heure);
                    requete = "INSERT INTO element_invasif (id, type, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '" + als.get(iterator) + "', '" + als.get(iterator+1) +  "', " + als.get(iterator+2) + ", " + als.get(iterator+3) + ", " + als.get(iterator+4) + ", " + als.get(iterator+5) + ", '" + date + "', '" + heure + "');";
                    System.out.println(requete);
                }
                if (als.get(iterator + 1).charAt(0) == 'r'){ // GESTIONS DES ELEMENTS REMARCABLE
                    System.out.println("Insertion d'un élément remarcable");
                    date = als.get(iterator + 1).substring(1, 5) + "-" + als.get(iterator + 1).substring(5, 7) + "-" + als.get(iterator + 1).substring(7, 9);
                    System.out.println("date = " + date);
                    heure = als.get(iterator + 1).substring(9, 11) + ":" + als.get(iterator + 1).substring(11, 13) + ":" + als.get(iterator + 1).substring(13, 15);
                    System.out.println("heure = " + heure);
                    requete = "INSERT INTO element_remarcable (id, idHab, nomMilieu, typeHab, type, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), uuid(), '" + als.get(iterator) + "', '" + als.get(iterator+1) +  "', " + als.get(iterator+2) + ", " + als.get(iterator+3) + ", " + als.get(iterator+4) + ", " + als.get(iterator+5) + ", '" + date + "', '" + heure + "');";
                    System.out.println(requete);

                }
                if (als.get(iterator + 1).charAt(0) == 'E'){ // GESTIONS DES ESPECES REMARCABLES
                    System.out.println("Insertion d'une espèce");
                    date = als.get(iterator + 1).substring(1, 5) + "-" + als.get(iterator + 1).substring(5, 7) + "-" + als.get(iterator + 1).substring(7, 9);
                    System.out.println("date = " + date);
                    heure = als.get(iterator + 1).substring(9, 11) + ":" + als.get(iterator + 1).substring(11, 13) + ":" + als.get(iterator + 1).substring(13, 15);
                    System.out.println("heure = " + heure);
                    System.out.println("pas encore implementé dans la base");

                }
                iterator += 6;
            }
        }
    }
}
