package TD4;

public class Exercice2 {

    public static int x = 0;

    static class ThreadIncrement {
        synchronized static void increment() {
            for (int i = 0; i < 50000; i++) {
                x++;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(ThreadIncrement::increment);

        Thread t2 = new Thread(ThreadIncrement::increment);

        System.out.println(x);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(x);
    }

}
