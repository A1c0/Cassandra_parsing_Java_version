import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class ImageManipulation {
    private byte[] imageByteArray;
    private String imageDataString;

    public ImageManipulation(){
        imageByteArray = null;
        imageByteArray = null;
    }

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

    public void openJPG(String pathname){
        File file = new File(pathname);

        try {
            // Reading a Image file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            imageDataString = encodeImage(imageData);

            /*
             * Converting a Base64 String into Image byte array
             */
            imageByteArray = decodeImage(imageDataString);

            imageInFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    public void generateJPG(String pathname){

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

    public static void main(String[] args) {

        String photo = "photo.jpg";
        ImageManipulation im = new ImageManipulation();
        im.openJPG(photo);
        String data = im.getImageDataString();
        System.out.println(data);
        im.generateJPG("photo2.jpg");

    }

    private static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    private static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}