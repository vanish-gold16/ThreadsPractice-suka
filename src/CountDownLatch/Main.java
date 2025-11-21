package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Main {
    static void main() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);

        countDownLatch.await(); // waiting until the counter is 0
        System.out.println("All jobs are done");
    }
}

class Work extends Thread{
    CountDownLatch countDownLatch;

    public Work(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public void run() {
        try{
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("done work");
        countDownLatch.countDown();
        return null;
    }
}