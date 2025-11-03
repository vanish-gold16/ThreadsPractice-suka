package Counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleCounter {

    private int counter;
    // private AtomicInteger counter; // атомарное - синхронизированное, но одновременно

    public SimpleCounter() {
        this.counter = 0;
        // this.counter = new AtomicInteger();
    }

    public void increment() {
        synchronized(this){ // synchronizing Threads around counter++
            counter++;
        }
        // counter ++ // without synchronizer
    }

//    public void increment(){
//        counter.incrementAndGet(); // increments and returns атомарно
//    }

    public synchronized void incrimentSecondWay(){ // second way
        counter++;
    }

    public int getCounter() {
        return counter;
        // counter.getCounter();
    }
}
