package Thread;

// basicly the data thread

public class MyThread extends  Thread {

    private final int threadNum;

    public MyThread(int threadNum) {
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
        return null;
    }
}
