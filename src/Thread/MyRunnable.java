package Thread;

// the same thing as the Thread is, but implements instead of extending (leaves place to extend something other

public class MyRunnable implements Runnable {

    private final int threadNum;

    public MyRunnable(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 6 ; i++) {
            System.out.println(i + " from thread " + threadNum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
