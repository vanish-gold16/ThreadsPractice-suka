package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Warehouse {

    Random random = new Random();

    private final List<Item> items = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    public Warehouse(){
        items.add(new Item("3XL", 100));
        items.add(new Item("Rick Owen's 35x", 20));
        items.add(new Item("Erd Backpack", 10));
        items.add(new Item("Saint Lauren Jeans", 50));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * a copy to return
     * @return a copy of items list
     */
    public List<Item> getItems() {
        lock.lock();
        try{
            return new ArrayList<>(items);
        } finally{
            lock.unlock();
        }
    }

    public void checkingItemAvalibility(Item item) throws InterruptedException {
        try {
            lock.lock();

            int index = returnItemByName(item.getName());
            while (getItems().get(index).getQuantity().intValue() >= getItems().get(index).getMaxQuantity()) {
                producer.await();
            }
            createItem(item);
            consumer.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void addItemToOrder(Item item, List<Item> customerCart) {

        lock.lock();
        try {

            int index = returnItemByName(item.getName());

            if (getItems().get(index).getQuantity().intValue() >= 1){

                newBuying(item);
                customerCart.add(item);
                producer.signalAll();
            }
            else {

                while (getItems().get(index).getQuantity().intValue() < 1) {
                    consumer.await();
                }
                newBuying(item);
                producer.signalAll();
                customerCart.add(item);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            lock.unlock();
        }
    }

    public void newBuying(Item item){
        int index = returnItemByName(item.getName());
        if(index >= 0)
        items.get(index).getQuantity().decrementAndGet();
    }

    public void createItem(Item item) throws InterruptedException {
        System.out.println("Creating new items");

        Thread.sleep(2000);

        int quantity = random.nextInt(1, 6);

        items.get(returnItemByName(item.getName())).getQuantity().addAndGet(quantity);
    }

    public int returnItemByName(String name){
        AtomicInteger index = new AtomicInteger(-1);
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name))
                index.set(i);
        }
        return index.intValue();
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Condition getProducer() {
        return producer;
    }

    public Condition getConsumer() {
        return consumer;
    }
}
