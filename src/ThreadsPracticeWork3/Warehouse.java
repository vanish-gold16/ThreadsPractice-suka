package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {

    Random random = new Random();

    private static final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
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
        AtomicInteger atomicQuantity = new AtomicInteger();

        atomicQuantity.set(item.getQuantity().intValue() + quantity);
        items.get(returnItemByName(item.getName())).setQuantity(atomicQuantity);

    }

    public int returnItemByName(String name){
        AtomicInteger index = new AtomicInteger(-1);
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name))
                index.set(i);
        }
        return index.intValue();
    }

}
