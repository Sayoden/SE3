package TD4;

import java.util.Arrays;

public class Exercice3 {

    public static final int[] tab = new int[1003];

    static class Tableau extends Thread {

        public int max = 0;

        private final int startAt;

        private final int endAt;

        public Tableau(int startAt, int endAt) {
            this.startAt = startAt;
            this.endAt = endAt;
        }

        @Override
        synchronized public void run() {
            System.out.println(startAt + " " + endAt);
            for (int i = startAt; i < this.endAt; i++) {
                if (tab[i] > max) {
                    max = tab[i];
                }
            }
        }
    }

    public static void main(String[] args) {
        generate();

        int nbThreads = 10;
        int pas = tab.length / nbThreads;
        int posAct = 0;

        int reste = tab.length - (posAct + (pas * nbThreads));

        System.out.println(reste);

        for (int i = 0; i < nbThreads; i++) {
            if (reste != 0) {
                runThread(new Tableau(posAct, (posAct + pas) + reste));
                posAct += pas + reste;
                reste = 0;
            } else {
                runThread(new Tableau(posAct, posAct + pas));
                posAct += pas;
            }
        }
    }

    public static void runThread(Tableau thread) {
        try {
            thread.start();
            thread.join();
            System.out.println(thread.max);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void generate() {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i;
        }
    }

}
