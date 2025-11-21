package Exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>(); // enables sending objects between two threads

        // because there's the same exchanger in the parameters, they are synchronized (can work basically)

        new Mike(exchanger);
        new Anket(exchanger);
    }

    static class Mike extends Thread {

        Exchanger<String> exchanger = new Exchanger<>();

        public Mike(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                exchanger.exchange("Hi, my name is Mike"); // sending data
                sleep(2000);
                exchanger.exchange("I'm 17 years old"); // sending second data
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    static class Anket extends Thread{

        Exchanger<String> exchanger = new Exchanger<>();

        public Anket(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            try {
                System.out.println(exchanger.exchange(null)); // writing out data we've sent
                System.out.println(exchanger.exchange(null)); // is waiting until the sender will send a message
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
