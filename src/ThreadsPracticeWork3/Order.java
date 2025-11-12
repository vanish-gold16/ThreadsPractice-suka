package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Order {

    Warehouse warehouse = new Warehouse();

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition hasProduct = lock.newCondition();

        @Override
        public void awaitUninterruptibly() {

        }

        @Override
        public long awaitNanos(long nanosTimeout) throws InterruptedException {
            return 0;
        }

        @Override
        public boolean await(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public boolean awaitUntil(Date deadline) throws InterruptedException {
            return false;
        }

        @Override
        public void signal() {

        }

        @Override
        public void signalAll() {

        }
    }

    private final List<Item> order = new ArrayList<>();

    public void addItemToOrder(Item item) {
        order.add(item);
    }

    public void createOrder(){
        boolean allAvailible = true;
        for (int i = 0; i < order.size(); i++) {
            if(warehouse.returnItemByName(order.get(i).getName()).equals(-1)){
                allAvailible = false;
                System.out.println(order.get(i).getName() + " is not enough in the warehouse to complete the order");

            }
        }
    }

}
