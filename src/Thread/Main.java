package Thread;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        MyThread myThread1 = new MyThread(1);
//        MyThread myThread2 = new MyThread(2);
//
//
//        myThread1.start();
//        myThread2.start();


        // first way to create a thread
        for (int i = 0; i < 5; i++) {
            // var thread = new MyThread(i);
            var runnable = new MyRunnable(i);
            var thread = new Thread(runnable);
            thread.start();
            // thread.isAlive(); if the thread is still alive
            // thread.join(); is waiting while the first thread will finish
        }

        // second way
        for (int i = 0; i < 5; i++) {
            var thread = new Thread(() -> {
                System.out.println("Hello from thread");
            });
        }
    }
}