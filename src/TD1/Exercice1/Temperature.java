package TD1.Exercice1;

import java.io.File;
import java.io.PrintWriter;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Temperature {

    static void ecrire(String filename) {
        LocalDate date = LocalDate.now();

        try {
            PrintWriter writer = new PrintWriter(filename);
            for (int i = 1; i < 100; i++) {
                date = date.plusDays(1);
                String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                writer.println(dateStr+ " " + new Random().nextInt((30 - 1) + 1));
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void lire(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));


            while (scanner.hasNext()) {
                String date = scanner.next();

                scanner.skip("\\s+");
                int temperature = scanner.nextInt();

                System.out.println(date + " " + temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ecrire("temperatures.txt");
        lire("temperatures.txt");
    }
}
