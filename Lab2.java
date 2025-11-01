public class paralle {
    public static void main(String[] args) throws InterruptedException {
        //System.out.println(Thread.currentThread().getName());
        // Thread thread = Thread.currentThread();
        // System.out.println("current thread: " + thread.getName());
        // Thread.sleep(5000); //  هياخد اكتر من 5 نواني عشان مش انا اللي بحدد هو هيشتغل امتي هو (selected by schedule)
        // System.out.println("current thread: " + thread.getName());
        // extend thread or implement runable interface to make thread

        // extend thread
        // Mytread thread = new Mytread();
        // thread.run();
        //thread play by start not run
        // thread.start(); //every thread have priority --> on start the thread it put in a ready queue but not me is make select
        System.out.println("==== " + Thread.currentThread().getName());

        //implement runable interface
        // Thread thread = new Thread(new Mytread(), "MyThread");
        // thread.start();
        Runnable r1 = new Mytread();
        r1.run();
        Thread thread = new Thread(r1, "MyThread");
        thread.start();
        // thread.sleep(5000);
        Thread thread2 = new Thread(r1, "MyThread2");
        thread2.start();

    }
    // static class Mytread extends Thread{
    //     public void run(){
    //         setName("My Thread");
    //         System.out.println("current thread: " + Thread.currentThread().getName());
    //     }
    //     public int run(int x){
    //         return x+5;
    //     }
    // }
    static class Mytread implements Runnable{
        public void run(){
            // setName("My Thread");
            System.out.println("current thread: " + Thread.currentThread().getName());
        }
        public int run(int x){
            return x+5;
        }
    }
}

