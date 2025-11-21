package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger i = new AtomicInteger(0);

    public static void main() throws InterruptedException {

        for (int i = 0; i < 10000; i++) {
            new MyThread().start();


        }
        Thread.sleep(2000);
        System.out.println(i);
    }

    static class MyThread extends Thread{

        @Override
        public void run() {
            i.getAndIncrement();
        }
    }
}
