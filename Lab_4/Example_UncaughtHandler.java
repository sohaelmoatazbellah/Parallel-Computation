class WorkerThread extends Thread {
    public void run() {
        // No try-catch here
        System.out.println("Thread started: " + getName());
        int x = 10 / 0; // This throws ArithmeticException
    }
}

public class Example_UncaughtHandler {
    public static void main(String[] args) {
        WorkerThread t1 = new WorkerThread();

        t1.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("⚠️ Exception in " + thread.getName() + ": " +
                               exception.getMessage());
        });

        t1.start();
    }
}