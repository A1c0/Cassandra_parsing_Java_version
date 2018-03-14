import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class TestLecture {

    ArrayList<String> queriesPrepared = new ArrayList<String>();

    public TestLecture(String pathname) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(pathname));
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
            ArrayList<String> data = new ArrayList<String>();
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
                data.add(mots);
                word++;
            }
            // ===== CONSTRUCTION DE LA REQUETE A PARTIR DE LA DONNES =====
            {
                //data POUR L'HABITAT
                String date = data.get(2).substring(1, 5) + "-" + data.get(2).substring(5, 7) + "-" + data.get(2).substring(7, 9);
                System.out.println("date = " + date);
                String heure = data.get(2).substring(9, 11) + ":" + data.get(2).substring(11, 13) + ":" + data.get(2).substring(13, 15);
                System.out.println("heure = " + heure);
                String milieu = data.get(0);
                System.out.println("milieu = " + milieu);
                String typeHab = data.get(1);
                System.out.println("typeHab = " + typeHab);
                String uuid = uuid();
                System.out.println("uuid = " + uuid);
                String requete = "INSERT INTO habitat(id, milieu, typeHab, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr)"
                        + " VALUES(" + uuid + " , '" + data.get(0) + "', '" + data.get(1) + "', '" + data.get(2) + "', " + data.get(3) + ", " + data.get(4)
                        + ", " + data.get(5) + ", " + data.get(6) + ", '" + date + "', '" + heure + "');";
                queriesPrepared.add(requete);
                System.out.println(requete);
                int iterator  = 7;
                while (iterator < data.size()){
                    date = data.get(iterator + 1).substring(1, 5) + "-" + data.get(iterator + 1).substring(5, 7) + "-" + data.get(iterator + 1).substring(7, 9);
                    System.out.println("date = " + date);
                    heure = data.get(iterator + 1).substring(9, 11) + ":" + data.get(iterator + 1).substring(11, 13) + ":" + data.get(iterator + 1).substring(13, 15);
                    System.out.println("heure = " + heure);
                    if (data.get(iterator + 1).charAt(0) == 'i'){ //GESTIONS DES ELEMENTS INVASIFS
                        System.out.println("Insertion d'un élément invasifs");
                        requete = "INSERT INTO element_invasif (id, type, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '"
                                + data.get(iterator) + "', '" + data.get(iterator+1) +  "', " + data.get(iterator+2) + ", " + data.get(iterator+3) + ", "
                                + data.get(iterator+4) + ", " + data.get(iterator+5) + ", '" + date + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                        System.out.println(requete);
                    }
                    if (data.get(iterator + 1).charAt(0) == 'r'){ // GESTIONS DES ELEMENTS REMARCABLE
                        System.out.println("Insertion d'un élément remarcable");
                        requete = "INSERT INTO element_remarcable (id, idHab, nomMilieu, typeHab, type, photo, GPS_lat, GPS_long, GPS_lat_lam, "
                                + "GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), " + uuid + ", '" + milieu + ", '" + typeHab + ", '" + data.get(iterator) + "', '" + data.get(iterator+1) + "', "
                                + data.get(iterator+2) + ", " + data.get(iterator+3) + ", " + data.get(iterator+4) + ", " + data.get(iterator+5) + ", '" + date
                                + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                        System.out.println(requete);
                    }
                    if (data.get(iterator + 1).charAt(0) == 'E'){ // GESTIONS DES ESPECES REMARCABLES
                        System.out.println("Insertion d'une espèce");
                        requete = "INSERT INTO espece (id, nom_esp, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '"
                                + data.get(iterator) + "', '" + data.get(iterator+1) +  "', " + data.get(iterator+2) + ", " + data.get(iterator+3) + ", "
                                + data.get(iterator+4) + ", " + data.get(iterator+5) + ", '" + date + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                        System.out.println(requete);
                    }
                    iterator += 6;
                }
            }
            line++;
            System.out.println();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        TestLecture tl1 = new TestLecture("donnee.txt");
    }

    public static String generate4HEX(){
        String str = String.format("%x", (int) (Math.random() * 65535));
        while (str.length() < 4){
            str = "0" + str;
        }
        return str;
    }

    public static String uuid(){
        return generate4HEX() + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + generate4HEX();
    }
}
