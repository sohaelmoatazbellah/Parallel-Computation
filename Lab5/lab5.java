import java.util.LinkedList;
import java.util.Queue;

public class lab5 {

    // Shared Buffer
    static class Buffer {
        private final Queue<String> queue = new LinkedList<>();
        private final int capacity;

        public Buffer(int capacity) {
            this.capacity = capacity;
        }

        // Producer adds item
        public synchronized void produce(String message) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // wait if buffer is full
            }
            queue.add(message);
            System.out.println("Produced: " + message);
            notifyAll(); // notify consumers
        }

        // Consumer removes item
        public synchronized String consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // wait if buffer is empty
            }
            String message = queue.remove();
            System.out.println("Consumed: " + message);
            notifyAll(); // notify producers
            return message;
        }
    }

    // Producer Thread
    static class Producer extends Thread {
        private final Buffer buffer;
        private final int producerId;

        public Producer(Buffer buffer, int id) {
            this.buffer = buffer;
            this.producerId = id;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    String message = "Message " + i + " from Producer " + producerId;
                    buffer.produce(message);
                    Thread.sleep(500); // simulate work
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Consumer Thread
    static class Consumer extends Thread {
        private final Buffer buffer;
        private final int consumerId;

        public Consumer(Buffer buffer, int id) {
            this.buffer = buffer;
            this.consumerId = id;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i <= 5; i++) {
                    String message = buffer.consume();
                    System.out.println("Consumer " + consumerId + " processed: " + message);
                    Thread.sleep(700); // simulate work
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        Buffer buffer = new Buffer(3); // fixed capacity

        Producer p1 = new Producer(buffer, 1);
        Producer p2 = new Producer(buffer, 2);

        Consumer c1 = new Consumer(buffer, 1);
        Consumer c2 = new Consumer(buffer, 2);

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
