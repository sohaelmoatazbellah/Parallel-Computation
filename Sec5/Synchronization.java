import java.util.ArrayList;
import java.util.List;

public class Synchronization{
    private static int counter = 0;
    private static Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        java.lang.ThreadGroup group = new java.lang.ThreadGroup("ThreadGroup");
        MyThread t2 = new MyThread();
        Object obj = new Object();
        Runnable r = () ->{
            synchronized(obj) {
                counter++;
            }
        };
        for (int i = 0; i < 1000; i++) {
            // Thread t = new Thread(group, t2);
            Thread t = new Thread(group, r);
            t.start();
            threads.add(t);
        }
        group.interrupt();
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        System.out.println("Total Counter: " + counter);
    }
    static class MyThread implements Runnable {
        public void run() {
            try {
                Thread.sleep(1000000000);
            } catch (InterruptedException e) {
                // throw new RuntimeException(e);
            }
            // synchronized(obj) {
            //     counter++;
            // }

            synchronized(this) {
                counter++;
            }

            // synchronized(MyThread.class) {
            //     counter++;
            // }

            // counter++;
            // int local = counter;
            // counter = local + 1;

            // increment();
        }
        // public synchronized static void increment() {
        //     counter++;
        // }
    }
}