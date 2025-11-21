package FastPizzaFinalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Kitchen {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition chefsCondition = lock.newCondition();
    private final Condition waitersCondition = lock.newCondition();

    private List<Order> orders =  new ArrayList<Order>();

    private ExecutorService chefsThreads = Executors.newFixedThreadPool(3);
    private ExecutorService waitersThread = Executors.newFixedThreadPool(3);

    public void startCooking() throws InterruptedException {
        try{
            lock.lock();

            while(orders.isEmpty()){
                chefsCondition.await();
            }
            Thread.sleep(orders.getFirst().getCookingTime());

        }
    }

    public void newOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

}



