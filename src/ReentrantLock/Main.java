package ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Example example = new Example();
        Runnable task = example::increment; // ссылка на метод
        Runnable task2 = example::timeout;

//        Thread thread1 = new Thread(task);
//        Thread thread2 = new Thread(example::increment); // it is the same

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task2); // example::increment
            thread.start();
        }

    }
}
