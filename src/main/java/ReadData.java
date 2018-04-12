import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadData {

    private ArrayList<String> queriesPrepared = new ArrayList<String>();

    public ReadData(String pathname) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(pathname));
        while (scan.hasNextLine()) {
            String lines = scan.nextLine();
            if (lines.equals("")) {
                continue; // permet de supprimer les lignes superflues
            }
            Scanner scanString = new Scanner(lines);
            ArrayList<String> data = new ArrayList<String>();
            while (scanString.hasNext()) {
                String mots = scanString.next();
                if (mots.charAt(0) == '\"') {
                    while (mots.charAt(mots.length() - 1) != '"' && mots.charAt(mots.length() - 2) != '"') {
                        mots += " " + scanString.next();
                    }
                }
                if (mots.charAt(mots.length() - 1) == ',') {
                    mots = mots.substring(0, mots.length() - 1);
                }/*
                if (mots.charAt(0) == '"') {
                    mots = mots.substring(1, mots.length() - 1);
                }*/
                data.add(mots);
            }
            // ===== CONSTRUCTION DE LA REQUETE A PARTIR DE LA DONNES =====
            {
                ImageManipulation imgManip = new ImageManipulation();
                //data POUR L'HABITAT
                String date = data.get(2).substring(1, 5) + "-" + data.get(2).substring(5, 7) + "-" + data.get(2).substring(7, 9);
                String heure = data.get(2).substring(9, 11) + ":" + data.get(2).substring(11, 13) + ":" + data.get(2).substring(13, 15);
                String milieu = data.get(0);
                String typeHab = data.get(1);
                String imagename = "data\\" + data.get(2).substring(1);
                imgManip.openJPG(imagename);

                String uuid = uuid();
                String requete = "INSERT INTO habitat(id, milieu, typeHab, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr)"
                        + " VALUES(" + uuid + ", '" + data.get(0) + "', '" + data.get(1) + "', textAsBlob('\"" + imgManip.getImageDataString() + "\"'), " + data.get(3) + ", " + data.get(4)
                        + ", " + data.get(5) + ", " + data.get(6) + ", '" + date + "', '" + heure + "');";
                queriesPrepared.add(requete);
                int iterator = 7;
                while (iterator < data.size()) {
                    date = data.get(iterator + 1).substring(1, 5) + "-" + data.get(iterator + 1).substring(5, 7) + "-" + data.get(iterator + 1).substring(7, 9);
                    heure = data.get(iterator + 1).substring(9, 11) + ":" + data.get(iterator + 1).substring(11, 13) + ":" + data.get(iterator + 1).substring(13, 15);
                    imagename = "data\\" + data.get(iterator + 1).substring(1);
                    imgManip.openJPG(imagename);
                    System.out.println("imagename = " + imagename);
                    if (data.get(iterator + 1).charAt(0) == 'i') { //GESTIONS DES ELEMENTS INVASIFS
                        requete = "INSERT INTO element_invasif (id, type, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '"
                                + data.get(iterator) + "', textAsBlob('\"" + imgManip.getImageDataString() + "\"'), " + data.get(iterator + 2) + ", " + data.get(iterator + 3) + ", "
                                + data.get(iterator + 4) + ", " + data.get(iterator + 5) + ", '" + date + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                    }
                    if (data.get(iterator + 1).charAt(0) == 'r') { // GESTIONS DES ELEMENTS REMARCABLE
                        requete = "INSERT INTO element_remarcable (id, idHab, nomMilieu, typeHab, type, photo, GPS_lat, GPS_long, GPS_lat_lam, "
                                + "GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), " + uuid + ", '" + milieu + "', '" + typeHab + "', '" + data.get(iterator) + "', textAsBlob('\"" + imgManip.getImageDataString() + "\"'), "
                                + data.get(iterator + 2) + ", " + data.get(iterator + 3) + ", " + data.get(iterator + 4) + ", " + data.get(iterator + 5) + ", '" + date
                                + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                    }
                    if (data.get(iterator + 1).charAt(0) == 'E') { // GESTIONS DES ESPECES REMARCABLES
                        requete = "INSERT INTO espece (id, nom_esp, photo, GPS_lat, GPS_long, GPS_lat_lam, GPS_long_lam, date_enr, heure_enr) VALUES(uuid(), '"
                                + data.get(iterator) + "', textAsBlob('\"" + imgManip.getImageDataString() + "/\"'), " + data.get(iterator + 2) + ", " + data.get(iterator + 3) + ", "
                                + data.get(iterator + 4) + ", " + data.get(iterator + 5) + ", '" + date + "', '" + heure + "');";
                        queriesPrepared.add(requete);
                    }
                    imgManip = new ImageManipulation();
                    iterator += 6;
                }
            }
        }
    }

    public ArrayList<String> getQueriesPrepared() {
        return queriesPrepared;
    }

    private static String generate4HEX(){
        String str = String.format("%x", (int) (Math.random() * 65535));
        while (str.length() < 4){
            str = "0" + str;
        }
        return str;
    }

    private static String uuid(){
        return generate4HEX() + generate4HEX() + "-" + generate4HEX()
                + "-" + generate4HEX() + "-" + generate4HEX() + "-" + generate4HEX() + generate4HEX() + generate4HEX();
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadData rd = new ReadData("data\\donnee.txt");
        for (String s : rd.getQueriesPrepared()) {
            System.out.println(s);
        }
    }
}
