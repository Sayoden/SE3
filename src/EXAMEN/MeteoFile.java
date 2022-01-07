package EXAMEN;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

//Goulois, Lucas B
public class MeteoFile {
    static final int nbJours = 31;

    static class Meteo {
        static final int BYTES = Integer.BYTES + Float.BYTES + (Float.BYTES * nbJours);

        int id; // identifiant de la station météo
        float res;
        float[] pluie = new float[nbJours]; // quantité de pluie pour chaque jour
    }

    FileChannel f; // le fichier binaire
    ByteBuffer buf; // le tampon pour écrire dans le fichier

    public MeteoFile(String filename) throws IOException {
        //ouverture en lecture/écriture, avec création du fichier
        f = FileChannel.open(
                FileSystems.getDefault().getPath(filename),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        // création d'un buffer juste assez grand pour contenir un produit
        buf = ByteBuffer.allocate(Meteo.BYTES);
    }

    public Meteo lireMeteo() throws IOException {
        buf.clear();
        while (buf.hasRemaining())
            if (f.read(buf) == -1)
                return null;
        buf.flip();
        Meteo meteo = new Meteo();

        meteo.id = buf.getInt();
        meteo.res = buf.getFloat();

        for (int i = 0; i < nbJours; i++) {
            meteo.pluie[i] = buf.getFloat();
        }

        return meteo;
    }

    public void definirResMoyenne() {
        try {
            f.position(0);
            Meteo meteo;

            int position = 0;
            while((meteo = lireMeteo()) != null) {
                float moyenneRes = 0;
                for (float v : meteo.pluie) {
                    moyenneRes += v;
                }
                moyenneRes = moyenneRes / meteo.pluie.length;
                meteo.res = moyenneRes;

                f.position(position);
                position += Meteo.BYTES;
                ecrireMeteo(meteo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ecrireMeteo(Meteo meteo) throws IOException {
        buf.clear();
        buf.putInt(meteo.id);
        buf.putFloat(meteo.res);
        for (int i = 0; i < meteo.pluie.length; i++) {
            buf.putFloat(meteo.pluie[i]);
        }

        buf.flip();
        while(buf.hasRemaining()) {
            f.write(buf);
        }
    }

    public void lireTest() {
        try {
            f.position(0);
            Meteo meteo;

            while((meteo = lireMeteo()) != null) {
                System.out.println(meteo.id + " " + meteo.res + " " + Arrays.toString(meteo.pluie));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Partie Texte --> Binaire
     *
     *
     */

    public void initTxtToBinary(String binaryFile) throws IOException {
        f.position(0);
        //ouverture en lecture/écriture, avec création du fichier
        f = FileChannel.open(
                FileSystems.getDefault().getPath(binaryFile),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE);
        // création d'un buffer juste assez grand pour contenir un produit
        buf = ByteBuffer.allocate(Meteo.BYTES);
    }

    public void texteToBinary(String fileName) throws IOException {
        try {
            Scanner in = new Scanner(new File(fileName));

            while (in.hasNextLine()) {
                String[] elements = in.nextLine().split(":");
                Meteo meteo = new Meteo();

                meteo.id = Integer.parseInt(elements[0]);
                meteo.res = Float.parseFloat(elements[1]);

                for (int i = 0; i < meteo.pluie.length; i++) {
                    meteo.pluie[i] = Float.parseFloat(elements[i + 2]);
                }

                ecrireMeteo(meteo);
            }

            in.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        MeteoFile file = new MeteoFile("Meteo.bin");

        file.lireTest();
        System.out.println("-----------------------------------------");
        file.definirResMoyenne();
        file.lireTest();
        System.out.println("-----------------------------------------");
        System.out.println("Exercice 2");
        System.out.println("-----------------------------------------");
        file.initTxtToBinary("NewMeteo.bin");
        file.lireTest();
        System.out.println("-----------------------------------------");
        file.texteToBinary("Meteo.txt");
        file.lireTest();
    }
}
