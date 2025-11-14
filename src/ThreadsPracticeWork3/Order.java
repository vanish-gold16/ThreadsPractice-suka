package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Order {

    Warehouse warehouse = new Warehouse();

    public boolean availible = false;

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private final List<Item> order = new ArrayList<>();

    public void addItemToOrder(Item item) {
        boolean isLocked = false;

        try{
            AtomicInteger index = warehouse.returnItemByName(item.getName());
            if(warehouse.getItems().get(index.intValue()).getQuantity().intValue()
                    >= item.getQuantity().intValue()) {
                availible = true;
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    isLocked = true;
                    order.add(item);
                }
            }
            else{
                availible = false;
                while(!availible){
                    condition.await();
                }

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            if(isLocked)
                lock.unlock();
        }
    }

}
