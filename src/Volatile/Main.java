package Volatile;

public class Main {

    // volatile - the Thread is seeing the last changes of the object

    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("Worker started!");
            while(running){
                int x = 0;
                for(int i = 0; i < 1000; i++){
                    x++;
                }
            }
            System.out.println("Worker finished!");
        });

        worker.start();

        Thread.sleep(2000);

        System.out.println("Main: reques to stop working");

        running = false; // flag

        worker.join();

        System.out.println("Main: finished!");
    }
}
