package ThreadLocalRandom;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main() throws InterruptedException {

        System.out.println(Math.random()); // simple
        System.out.println(ThreadLocalRandom.current().nextInt()); // multithreading

        System.out.println(TimeUnit.DAYS.toSeconds(14));
    }

}
