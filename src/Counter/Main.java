package Counter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList = new ArrayList<Thread>();

        var counter = new SimpleCounter();

        for (int i = 0; i < 500; i++) {
            Thread thread = new ThreadThing(counter, i);
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList){
            thread.join(); // is waiting while the other one will finish
        }

        System.out.println("Counter: "+ counter.getCounter());
    }
}
