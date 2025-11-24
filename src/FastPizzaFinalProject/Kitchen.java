    package FastPizzaFinalProject;

    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Random;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.locks.Condition;
    import java.util.concurrent.locks.ReentrantLock;

    public class Kitchen {

        private Random random = new Random();
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition chefsCondition = lock.newCondition();
        private final Condition waitersCondition = lock.newCondition();

        private List<Order> orders =  new LinkedList<>();

        private ExecutorService chefsThreads = Executors.newFixedThreadPool(3);
        private ExecutorService waitersThread = Executors.newFixedThreadPool(3);

        public void startProgramm() throws InterruptedException {
            for (int i = 0; i < 20; i++) {
                waitersThread.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            takeOrder();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            for (int i = 0; i < 5; i++) {
                chefsThreads.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            startCooking();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }

        public void startCooking() throws InterruptedException {
            Order order;

            lock.lock();
            try{

                while(orders.isEmpty()){
                    chefsCondition.await();
                }
                System.out.println("Cooking order...");
                order = orders.removeFirst();
                waitersCondition.signalAll();
            } finally {
                lock.unlock();
            }

            Thread.sleep(order.getCookingTime());
        }

        public void takeOrder() throws InterruptedException {

            Order order = null;

            lock.lock();

            try{

                while(orders.size() >= 20){
                    waitersCondition.await();
                }
                System.out.println("Taking order...");
                newOrder(orders.getFirst());
                chefsCondition.signalAll();
            } finally {
                lock.unlock();
                Thread.sleep(2000);
            }
        }

        public void newOrder(Order order) {
            orders.add(order);
        }

        public List<Order> getOrders() {
            return orders;
        }

    }



