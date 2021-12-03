package TD2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.StandardOpenOption;

public class Exercice1 {

    class Produit {
        int identifiant;
        float prix;
        int stock;

        static final int BYTES = Integer.BYTES + Float.BYTES + Integer.BYTES;
    }

    FileChannel f;
    ByteBuffer buf;

    public Exercice1(String filename) throws IOException {
        f = FileChannel.open(
                FileSystems.getDefault().getPath(filename),
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE
                );

        buf = ByteBuffer.allocate(Produit.BYTES);
    }

    /**
     * Ecrire un produit
     *
     * @throws IOException
     */
    public void ecrire(Produit produit) throws IOException {
        buf.clear();

        //Ajout des informations du produit
        buf.putInt(produit.identifiant);
        buf.putFloat(produit.prix);
        buf.putInt(produit.stock);

        buf.flip();

        while (buf.hasRemaining()) {
            f.write(buf);
        }
    }

    /**
     * Permet de lire un produit dans l'ordre du tampon
     */
    public Produit lireProduit() throws IOException {
        buf.clear();

        while (buf.hasRemaining()) {
            if (f.read(buf) == -1) {
                return null;
            }
        }

        buf.flip();

        Produit produit = new Produit();
        produit.identifiant = buf.getInt();
        produit.prix = buf.getFloat();
        produit.stock = buf.getInt();
        return produit;
    }

    public void lireTousLesProduits() throws IOException {
        Produit produit;
        f.position(0);

        while ((produit=lireProduit()) != null) {
            System.out.println(produit.identifiant + " " + produit.prix + " " + produit.stock);
        }
    }

    public void lireUnProduit(int identifiant) throws IOException{
        Produit produit;
        f.position(0);

        while((produit=lireProduit()) != null) {
            if (produit.identifiant == identifiant) {
                System.out.println(identifiant + " " + produit.prix + " " + produit.stock);
                return;
            }
        }

        System.out.println("Produit inconnu");
    }

    public void ajouterProduit(Produit produit) throws IOException {
        Produit prod;
        f.position(0);

        while((prod=lireProduit()) != null) {
            if (prod.identifiant == produit.identifiant) {
                System.out.println(buf.getInt());
            }
        }
    }

    public void run() throws IOException{
        Produit produit = new Produit();
        for (int i = 0; i < 100; i++) {
            produit.identifiant = i;
            produit.prix = i * 10;
            produit.stock = i + 5;

            this.ecrire(produit);
        }

        this.lireTousLesProduits();

        System.out.println("-----------------------");

        this.lireUnProduit(2);

        Produit prod = new Produit();
        prod.identifiant = 1;
        prod.prix = 10;
        prod.stock = 100;
        this.ajouterProduit(prod);
    }

    public static void main(String[] args) {
        try {
            Exercice1 bin = new Exercice1("/tmp/produits.bin");
            bin.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
