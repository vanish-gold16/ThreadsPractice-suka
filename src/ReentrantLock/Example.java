package ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Example implements Runnable {

    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    /**
     * waiting for certain time and if couldn't lock go away
     */

    public void timeout(){
        boolean locked = false;
        try{
            locked = lock.tryLock(2, TimeUnit.SECONDS);
            if(locked){
                System.out.println(Thread.currentThread().getName() + " got access");
                Thread.sleep(3000);
            } else{
                System.out.println(Thread.currentThread().getName() + " got no access");
            }
        } catch(InterruptedException e){
            e.printStackTrace();
        } finally{
            if(locked){
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked");
            }
        }
    }

    public void increment(){
        lock.lock();
        try{
            counter++;
            System.out.println(Thread.currentThread().getName() + " increment = " + counter);
        } finally {
            lock.unlock();
        }
//        if(lock.tryLock()){
//            try{
//                counter++;
//                System.out.println(Thread.currentThread().getName() + " - increment = " + counter);
//            } finally{
//                lock.unlock();
//            }
//        }
//        else
//            System.out.println(Thread.currentThread().getName() + " - Unabled to lock");
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^
//        Thread-2 - Unabled to lock
//        Thread-3 - Unabled to lock
//        Thread-1 - Unabled to lock
//        Thread-4 - Unabled to lock
//        Thread-0 - increment = 1
    }

    @Override
    public void run() {

    }
}
