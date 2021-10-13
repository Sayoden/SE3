package TD1.Exercice3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class CSV {

    private File csvFile;

    private final ArrayList<ArrayList<String>> lines = new ArrayList<>();

    public CSV(String fileName) {
        this.csvFile = new File(fileName);
    }

    public ArrayList<String> decouperLigne(String s) {
        return new ArrayList<>(Arrays.asList(s.split(",+")));
    }

    public void lire() {
        try {
            this.lines.clear();
            Scanner in = new Scanner(new File("csv.csv"));

            in.useLocale(Locale.US);

            while (in.hasNextLine()) {
                String nextLine = in.nextLine();
                lines.add(decouperLigne(nextLine));
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void afficher() {
        for (ArrayList<String> line : this.lines) {
            System.out.println(line.toString() + "\n");
        }
    }

    public void ecrire(String nomFichier) {
        try {
            PrintWriter out = new PrintWriter(nomFichier);

            for (ArrayList<String> line : this.lines) {
                out.print(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public ArrayList<ArrayList<String>> getLines() {
        return lines;
    }

    public static void main(String[] args) {
        //String test = "1,2,3,4,R lol";
        CSV csv = new CSV("cvs.cvs");

        //csv.getLines().add(csv.decouperLigne(test));

        csv.lire();

        csv.afficher();
    }

}
