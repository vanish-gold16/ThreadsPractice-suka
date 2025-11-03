package Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition hasProduct = lock.newCondition();
    private boolean availible = false;

    public void produce() throws InterruptedException {
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
