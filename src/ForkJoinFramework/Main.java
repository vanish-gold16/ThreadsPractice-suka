package ForkJoinFramework;

import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {

    static long numOfOperations = 10000000000L;

    // number of cores of processor
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

        System.out.println(new Date());

        ForkJoinPool pool = new ForkJoinPool();

        System.out.println(pool.invoke(new MyFork(0, numOfOperations)));

        System.out.println(new Date());

        // like 4 seconds
//        System.out.println(new Date());
//
//        long j = 0;
//        for (long i = 0; i < numOfOperations; i++) {
//            j += i;
//        }
//
//        System.out.println(j);
//
//        System.out.println(new Date());

    }

    // takes 1 second

    static class MyFork extends RecursiveTask<Long> {

        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
//            if (если операция разбита на досточное кол-во частей) {
//                выполняем операцию
//            } else{
//                разбиваем на части поменьше
//            }

            if((to - from) <= numOfOperations/numOfThreads) {
                long j = 0;
                for (long i = from; i < to; i++) {
                    j += i;

                }
                return j;

            } else {
                long middle = (to + from) / 2;

                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to);

                long secondValue = secondHalf.compute();

                return firstHalf.join() + secondValue;
            }
        }
    }

}
