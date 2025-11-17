package ThreadFactory;

import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {

        // setting custom threads (priority, name, etc)

        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("Ivan Thread");
                thread.setPriority(Thread.MAX_PRIORITY);

                return thread;
            }
        };
        threadFactory.newThread(new myRun()).start();
    }

    static class myRun implements Runnable {
        @Override
        public void run() {
            System.out.println("1");
        }
    }

}
