package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Order {

    Warehouse warehouse = new Warehouse();

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    private final List<Item> order = new ArrayList<>();

    public void checkingItemAvalibility(Item item) throws InterruptedException {
        boolean locked = false;
        try {
            lock.lock();

            int index = warehouse.returnItemByName(item.getName());
            while (warehouse.getItems().get(index).getQuantity().intValue() >= item.getQuantity().intValue()) {
                    producer.await();
            }
            warehouse.createItem(item);
            consumer.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void addItemToOrder(Item item) {

        try {
                lock.lock();

                int index = warehouse.returnItemByName(item.getName());

                if (warehouse.getItems().get(index).getQuantity().intValue()
                        >= item.getQuantity().intValue()){

                    warehouse.newBuying(item);
                    order.add(item);

                }
                else {

                    while (warehouse.getItems().get(index).getQuantity().intValue() < item.getQuantity().intValue()) {
                        consumer.await();
                    }
                    warehouse.newBuying(item);
                    producer.signalAll();
                    order.add(item);
                }
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
        } finally{
                lock.unlock();
        }
    }

    public Order(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Condition getProducer() {
        return producer;
    }
}
