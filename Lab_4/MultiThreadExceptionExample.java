class Worker extends Thread {
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " started.");
            int result = 10 / 0; // This will throw ArithmeticException
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " caught: " + e);
        }
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}

public class MultiThreadExceptionExample {
    public static void main(String[] args) {
        Worker t1 = new Worker();
        Worker t2 = new Worker();
        Worker t3 = new Worker();

        t1.start();
        t2.start();
        t3.start();
    }
}
