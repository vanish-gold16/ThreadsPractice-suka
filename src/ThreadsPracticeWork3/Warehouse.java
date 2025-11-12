package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Warehouse {

    Item threeXL = new Item("3XL", 100);
    Item rickOwensThirtyfive = new Item("RIck Owen's 35x", 20);
    Item erdBackpack = new Item("Erd Backpack", 10);
    Item saintLaurenJeans = new Item("Saint Lauren Jeans", 50);

    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void newBuying(Item item){
        AtomicInteger index = returnItemByName(item.getName());
        if(index.get() >= 0)
        items.get(index.get()).getQuantity().decrementAndGet();
    }

    public AtomicInteger returnItemByName(String name){
        AtomicInteger index = new AtomicInteger(-1);
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name))
                index.set(i);
        }
        return index;
    }

}
