package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run()); // waiting until all (3) threads new Run() will run

        new Sportsmen(cyclicBarrier);
        new Sportsmen(cyclicBarrier);
        new Sportsmen(cyclicBarrier);

        // waiting until all three will run Run
    }

    static class Run extends Thread{
        CyclicBarrier cyclicBarrier;

        @Override
        public void run() {
            System.out.println("Run has begun");
            return null;
        }
    }

    static class Sportsmen extends Thread{
        CyclicBarrier cyclicBarrier;

        public Sportsmen(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            start();
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
