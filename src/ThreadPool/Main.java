package ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static ReentrantLock lock = new ReentrantLock();

    static void main() throws ExecutionException, InterruptedException {
    //    ExecutorService executorService = Executors.newFixedThreadPool(2);
        int coreCount = Runtime.getRuntime().availableProcessors(); // amount of cores
        // ExecutorService executorService = Executors.newFixedThreadPool(coreCount);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }

        executorService.shutdown();


//        executorService.submit(new MyRannable());
//        System.out.println(executorService.submit(new MyCallable()).get());
//
//        executorService.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("Thread: " + Thread.currentThread().getName());
            } finally{
                lock.unlock();
            }
            return null;
        }
    }

//    static class MyRannable implements Runnable{
//        @Override
//        public void run() {
//            System.out.println(1);
//        }
//    }

//    static class MyCallable implements Callable<String> {
//
//        @Override
//        public String call() throws Exception {
//            return "2";
//        }
//    }
}
