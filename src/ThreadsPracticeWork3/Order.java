package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Order {

    private Warehouse warehouse = new Warehouse();

    private List<Item> myCart = new ArrayList<>();

    public void buyItem(Item item) {
        warehouse.addItemToOrder(item, myCart);
    }

    public Order(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
