package TD4;

import java.util.Arrays;

public class Exercice3 {

    public static final int[] tab = new int[100];

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

        Tableau thread;
        for (int i = 1; i <= 4; i++) {
            int startAt;
            if (i == 1) {
                startAt = tab.length / i;
            } else {
                startAt = (tab.length / i) + tab.length / 4;
            }²²QZS
            thread = new Tableau(startAt - (tab.length / 4), startAt);
            try {
                thread.start();
                thread.join();
                System.out.println(thread.max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void generate() {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = i;
        }
    }

}
