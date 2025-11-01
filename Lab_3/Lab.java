import java.util.List;
import java.util.ArrayList;

public class Lab {
    public static void main(String[] args) {

        Runnable th1 = () -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        };

        Runnable th2 = () -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
        };

        Runnable th3 = () -> {
            System.out.println("Thread name: " + Thread.currentThread().getName());
            try {
                while (true) { 
                    System.out.println(Thread.currentThread().getName() + " working...");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };

        Thread thread1 = new Thread(th1, "thread 1");
        thread1.setPriority(4);

        Thread thread2 = new Thread(th2, "thread 2");
        thread2.setPriority(7);

        Thread thread3 = new Thread(th3, "thread 3");
        thread3.setPriority(1);
        thread3.setDaemon(true);

        List<Thread> list = new ArrayList<>();
        list.add(thread1);
        list.add(thread2);
        list.add(thread3);

        ThreadMangement threadMangement = new ThreadMangement();
        threadMangement.list = list;
        threadMangement.run();
    }

    static class ThreadMangement {
        List<Thread> list;

        public void run() {
            for (Thread thread : list) {
                thread.start();
            }
        }
    }
}
