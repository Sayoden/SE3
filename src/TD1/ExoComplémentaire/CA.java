package TD1.ExoComplémentaire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CA {

    static void calculRecapCA(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            PrintWriter printer = new PrintWriter("recap.txt");

            printer.println("Magasin\tAnnée\tCA");

            scanner.useDelimiter("\\n|\\t");

            //Pour passer la ligne du début.
            scanner.nextLine();

            String magasin = scanner.next();
            String anneeCivile = scanner.next();
            scanner.next();
            double CA = Double.parseDouble(scanner.next());

            while (scanner.hasNext()) {
                String magasinAct = scanner.next();
                String anneeAct = scanner.next();
                scanner.next();
                if (!magasinAct.equals(magasin) || !anneeAct.equals(anneeCivile)) {
                    printer.println(magasin + "\t" + anneeCivile + "\t" + CA);
                    magasin = magasinAct;
                    anneeCivile = anneeAct;
                    CA = 0 + Double.parseDouble(scanner.next());
                } else {
                    CA += Double.parseDouble(scanner.next());
                }

            }

            printer.println(magasin + "\t" + anneeCivile + "\t" + CA);

            scanner.close();
            printer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        calculRecapCA("CA.txt");
    }
}
