package TD1.Exercice1;

import java.io.File;
import java.io.PrintWriter;
import java.text.spi.DateFormatProvider;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Temperature {

    static void ecrire(String filename) {
        LocalDate date = LocalDate.now();

        try {
            PrintWriter writer = new PrintWriter(filename);
            for (int i = 1; i < 100000; i++) {
                date = date.plusDays(1);
                String dateStr = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                writer.println(dateStr + " " + new Random().nextInt((30 - 1) + 1));
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void lire(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            ArrayList<Integer> temperatures = new ArrayList<>();
            scanner.useDelimiter("/|\\s+");
            while (scanner.hasNext()) {
                int day = scanner.nextInt();
                int month = scanner.nextInt();
                int year = scanner.nextInt();
                int temperature = scanner.nextInt();

                temperatures.add(temperature);

                if (day == LocalDate.of(year, Month.of(month), day).getMonth().maxLength()) {
                    int temperatureCumule = 0;
                    for (Integer integer : temperatures) {
                        temperatureCumule += integer;
                    }

                    System.out.println("Moyenne du (" + month + " " + year + ") " + temperatureCumule / day);
                    temperatures.clear();
                }

            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ecrire("temperatures.txt");
        lire("temperatures.txt");
    }
}
