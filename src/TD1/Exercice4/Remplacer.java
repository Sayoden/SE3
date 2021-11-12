package TD1.Exercice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Remplacer {

    static HashMap<String, String> keywords = new HashMap<>();

    public static void readKeywords(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));

            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split("\\s");

                keywords.put(args[0], args[1]);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void remplacer(String file, String printerFile) {
        try {
            Scanner scanner = new Scanner(new File(file));
            PrintWriter printer = new PrintWriter(printerFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (Map.Entry<String, String> entry : keywords.entrySet()) {
                    if (line.contains(entry.getKey())) {
                        line = line.replace(entry.getKey(), entry.getValue());
                    }
                }

                printer.println(line);
            }

            scanner.close();
            printer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        if (args.length == 3) {
            readKeywords(args[0]);
            remplacer(args[1], args[2]);
        } else {
            System.out.println("Nbre d'arguments manquants");
            System.exit(-1);
        }
    }

}
