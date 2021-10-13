package TD1.exams;

import java.io.File;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Interro1 {

    static void calculCA(String file) {
        try {
            Scanner in = new Scanner(new File(file));
            PrintWriter out = new PrintWriter("cumul.txt");

            in.useLocale(Locale.US);
            in.useDelimiter("\\;|\\n");

            out.println("Magasin;CA global");

            in.nextLine();

            String magasin  = "";
            double CA = 0;

            if (in.hasNextLine()) {
                in.next();
                magasin = in.next();
                for (int i = 0; i < 12; i++) {
                    CA += in.nextDouble();
                }
            }

            while (in.hasNextLine()) {
                in.next();
                String magasinAct = in.next();
                double CAAct = 0;
                for (int i = 0; i < 12; i++) {
                    CAAct += in.nextDouble();
                }

                if (!magasin.equalsIgnoreCase(magasinAct)) {
                    out.println(magasin + ";" + CA);
                    magasin = magasinAct;
                    CA = 0 + CAAct;
                } else {
                    CA += CAAct;
                }
            }

            out.println(magasin + ";" + CA);

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        calculCA("ca.txt");
    }
}
