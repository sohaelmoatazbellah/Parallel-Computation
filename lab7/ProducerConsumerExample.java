import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerExample {

    // Shared Buffer
    private static final int CAPACITY = 5;
    private final Queue<String> buffer = new LinkedList<>();

    // Lock and Conditions
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    // Producer Class
    class Producer implements Runnable {
        private final String name;

        Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int count = 1;
            try {
                while (true) {
                    lock.lock();
                    try {
                        while (buffer.size() == CAPACITY) {
                            System.out.println(name + " waiting, buffer is full...");
                            notFull.await();
                        }

                        String message = name + "-Message-" + count++;
                        buffer.add(message);
                        System.out.println(name + " produced: " + message);

                        notEmpty.signalAll(); // wake up consumers
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(500); // simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Consumer Class
    class Consumer implements Runnable {
        private final String name;

        Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    lock.lock();
                    try {
                        while (buffer.isEmpty()) {
                            System.out.println(name + " waiting, buffer is empty...");
                            notEmpty.await();
                        }

                        String message = buffer.poll();
                        System.out.println(name + " consumed: " + message);

                        notFull.signalAll(); // wake up producers
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(800); // simulate work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        ProducerConsumerExample example = new ProducerConsumerExample();

        Thread p1 = new Thread(example.new Producer("Producer-1"));
        Thread p2 = new Thread(example.new Producer("Producer-2"));

        Thread c1 = new Thread(example.new Consumer("Consumer-1"));
        Thread c2 = new Thread(example.new Consumer("Consumer-2"));

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
