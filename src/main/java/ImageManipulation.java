import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class ImageManipulation {
    private byte[] imageByteArray; // data qui pourra être écrit directement dans un fichier .jpg
    private String imageDataString; // texte inscrit comme un blob dans la base Cassandra


    // ======================================================
    // ==================== CONSTRUCTEUR ====================
    // ======================================================

    public ImageManipulation(){
        // création de l'objet en initialement à null les deux attributs
        imageByteArray = null;
        imageByteArray = null;
    }


    // ======================================================
    // =============== ASSESSEURS/ MUTATEURS ================
    // ======================================================

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public String getImageDataString() {
        return imageDataString;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
        imageDataString = encodeImage(imageByteArray);
    }

    public void setImageDataString(String imageDataString) {
        this.imageDataString = imageDataString;
        imageByteArray = decodeImage(imageDataString);
    }


    // ======================================================
    // ====================== METHODES ======================
    // ======================================================

    private static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    } // Permet la conversion de Byte[] à String

    private static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    } // Permet la conversion de String à Byte[]

    public void openJPG(String pathname){
        /*methode qui ouvre un fichier .jpg et met à jour les attributs */
        File file = new File(pathname);
        try {
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            imageDataString = encodeImage(imageData);

            imageByteArray = decodeImage(imageDataString);

            imageInFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    public void generateJPG(String pathname){
        /*methodes qui permet de genererer à partir des attribut un fichier .jpg qui à pour nom le parametre de la methode*/
        try {

            FileOutputStream imageOutFile = new FileOutputStream(pathname);
            imageOutFile.write(imageByteArray);

            imageOutFile.close();

            System.out.println("Image Successfully Created!");
        }
        catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        }
        catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }


    // ======================================================
    // =============== MAIN/ TEST DE L'OBJET ================
    // ======================================================

    public static void main(String[] args) {

        String photo = "data\\photo.jpg";
        ImageManipulation im = new ImageManipulation();
        im.openJPG(photo);
        String data = im.getImageDataString();
        System.out.println(data);
        im.generateJPG("data\\test.jpg");
        photo = "data\\20180309151425.jpg";
        im.openJPG(photo);
        data = im.getImageDataString();
        System.out.println(data);
        im.generateJPG("data\\photoyolo.jpg");

    }
}