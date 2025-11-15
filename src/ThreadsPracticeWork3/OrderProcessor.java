package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessor {

    Warehouse warehouse = new Warehouse();
    Random random = new Random();

    private final List<Item> finalItemsList = new ArrayList<Item>();

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public OrderProcessor(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void processingOrder(Order order){

        int itemAmount = random.nextInt(1, 5);
        for (int i = 0; i < itemAmount; i++) {
            finalItemsList.add(warehouse.getItems().get(random.nextInt(0, warehouse.getItems().size())));
        }
        for (int i = 0; i < finalItemsList.size(); i++) {

            int finalI = i;

            executor.execute(() -> {
                order.addItemToOrder(finalItemsList.get(finalI));
            });

            executor.execute(() -> {
                try {
                    order.checkingItemAvalibility(finalItemsList.get(finalI));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }

}
