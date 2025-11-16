package ThreadsPracticeWork3;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    protected String name;
    protected AtomicInteger quantity;
    private final int maxQuantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = new AtomicInteger(quantity);
        this.maxQuantity = quantity;
    }

    public void decrementAndGet() {
        this.quantity.decrementAndGet();
    }

    @Override
    public String toString() {
        return name + " is " +  quantity.get() + " in the warehouse";
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(AtomicInteger quantity) {
        this.quantity = quantity;
    }
}
