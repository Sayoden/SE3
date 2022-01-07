package EXAMEN;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.StandardOpenOption;

class Camion {
    static final int nbColis=100;
    int id; // identifiant du camion
    float res;
    float[] poids=new float[nbColis]; // poids de chaque colis
    ByteBuffer buf;
    FileChannel f;
    static final int BYTES=Integer.BYTES+(Float.BYTES*101);
    public void init() throws IOException {
        f = FileChannel.open(
                FileSystems.getDefault().getPath("src/Camion.bin"), //mon chemin absolu
                StandardOpenOption.READ,
                StandardOpenOption.WRITE
        );
        buf = ByteBuffer.allocate(Camion.BYTES);
    }
    Camion lireCamion() throws IOException {
        buf.clear();
        while(buf.hasRemaining())
            if(f.read(buf)==-1)
                return null;
        buf.flip();
        Camion camion=new Camion();
        camion.id=buf.getInt();
        camion.res=buf.getFloat();
        for (int i=0;i<nbColis;i++){
            camion.poids[i]=buf.getFloat();
        }
        return camion;
    }
    void ecrireCamion(Camion camion) throws IOException {
        buf.clear();
        buf.putInt(camion.id);
        buf.putFloat(camion.res);
        for (int i=0;i<nbColis;i++){
            buf.putFloat(camion.poids[i]);
        }
        buf.flip();
        while(buf.hasRemaining())
            f.write(buf);
    }
    public void binaryToTxt() throws IOException { //Fonction principale de la question 2
        PrintWriter out = new PrintWriter("src/monCamion.txt"); //Chemin et nom du nouveau fichier crée
        f.position(0);
        Camion cam;
        while ((cam=lireCamion())!=null){
            out.print(cam.id+";"+cam.res+";");
            for (int i=0; i<nbColis;i++){
                out.print(cam.poids[i]+";");
            }
            out.println();
        }
        out.close();
    }
    public void changerResMax(int min, int max) throws IOException { //fonction principale de la question 1
        if (min<0 || max>f.size()){
            System.err.println("Veillez à rentrer des indices cohérentes");
            return;
        }
        f.position(min*Camion.BYTES);
        Camion cam;
        for (int i=min;i<=max;i++){
            cam=lireCamion();
            float maxTmp=cam.poids[0];
            for (int j=1;j<nbColis;j++){
                if (cam.poids[j]>maxTmp)maxTmp=cam.poids[j];
            }
            cam.res=maxTmp;
            f.position(i*Camion.BYTES);
            ecrireCamion(cam);
        }
    }
    void run() throws IOException {
        init();
        changerResMax(0,2);
        binaryToTxt(); // Question 2 (Fonctionnel)
        f.close();
    }
    public static void main(String[] args) {
        Camion c =new Camion();
        try {
            c.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
