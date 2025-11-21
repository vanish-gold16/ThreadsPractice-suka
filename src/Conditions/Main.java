package Conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int account = 0;

    public static void main(String[] args) throws InterruptedException {
//        Warehouse warehouse = new Warehouse();
//
//        new Thread().start();

        // is usually used when using locks

        new accountMinus().start();
        new accountPlus().start();
    }

    static class accountPlus extends Thread{
        @Override
        public void run() {
            lock.lock();
            account += 10;
            System.out.println(account);
            condition.signal();
            lock.unlock();
            return null;
        }
    }

    static class accountMinus extends Thread{
        @Override
        public void run() {
            if(account < 10) {
                try {
                    lock.lock();
                    System.out.println(account);
                    condition.await();
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            account -= 10;
            System.out.println(account);
            return null;
        }
    }
}
