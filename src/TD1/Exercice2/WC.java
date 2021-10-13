package TD1.Exercice2;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class WC {


    public static void commandeWc(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            scanner.useLocale(Locale.US);

            int newLine = 0;
            int octets = 0;
            int words = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                newLine++;
                octets += line.getBytes().length + 1;
                words += line.split("\\s+").length;

                //System.out.println(line);
            }

            System.out.println(newLine + " " + words + " " + octets + " " + filename);
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        commandeWc("wc.txt");
    }
}
