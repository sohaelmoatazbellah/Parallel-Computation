import java.util.List;
import java.util.ArrayList;

public class MultiExecutor {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            System.out.println("Task 1 running on " + Thread.currentThread().getName());
        };
        
        Runnable task2 = () -> {
            System.out.println("Task 2 running on " + Thread.currentThread().getName());
        };
        
        Runnable task3 = () -> {
            System.out.println("Task 3 running on " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                System.out.println("Task 3 finished on " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("Task 3 interrupted");
            }
        };
        
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        
        MultiExecutor executor = new MultiExecutor(tasks);
        
        executor.run();
        
        System.out.println("All tasks started.");
    }
    List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }
    public void run() {
        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
