package Conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition hasProduct = lock.newCondition();
    private static boolean availible = false;

    public static void produce() throws InterruptedException {
        lock.lock();
        try{
            while (availible){
                hasProduct.await();
            }
            System.out.println("Creating a product...");
            Thread.sleep(2000);
            availible = true;
            hasProduct.signal();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try{
            while (!availible){
                hasProduct.await();
            }
            System.out.println("Consuming a product...");
            Thread.sleep(2000);
            availible = false;
            hasProduct.signal();
        } finally{
            lock.unlock();
        }
    }

}
