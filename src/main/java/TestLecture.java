import java.io.*;
import java.util.Scanner;

public class TestLecture {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("donnee.txt"));
        int line = 1;

        while (scan.hasNextLine()) {
            String lines = scan.nextLine();
            if (lines == )
                break;
            System.out.println("Ligne " + line + ": " + lines);
            line++;
        }
    }
}
