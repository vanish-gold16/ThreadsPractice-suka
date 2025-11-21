package BlockingQueue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {

        BlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();

        new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println(priorityBlockingQueue.take()); // now the element from queue is blocking
                } catch (InterruptedException e) {                    // , waiting until the second thread will add an element
                    throw new RuntimeException(e);
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                priorityBlockingQueue.add("This is string");
            }
        }.start();

//        Queue<String> queue = new PriorityQueue<>();
//
//        // not safe bc the first one could be done faster than the second (it will remove non-existing element)
//
//        new Thread(){
//            @Override
//            public void run(){
//                System.out.println(queue.remove());
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run(){
//                queue.add("This is string");
//            }
//        }.start();
    }
}
