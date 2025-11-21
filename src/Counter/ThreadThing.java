package Counter;

public class ThreadThing extends Thread {
    private final SimpleCounter simpleCounter;
    private final int threadNum;

    public ThreadThing(SimpleCounter simpleCounter, int threadNum) {
        this.simpleCounter = simpleCounter;
        this.threadNum = threadNum;
    }

    @Override
    public void run() {

        System.out.println("Thread " + threadNum + " is running");
        for (int i = 0; i < 5; i++) {
            simpleCounter.increment();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Thread " + threadNum + " is finished");

        return null;
    }
}
