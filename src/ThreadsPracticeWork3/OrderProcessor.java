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

    private final ExecutorService buyer = Executors.newFixedThreadPool(20);
    private final ExecutorService producer = Executors.newFixedThreadPool(5);

    public OrderProcessor(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void processingOrder(Order order){

        int itemAmount = random.nextInt(1, 5);
        for (int i = 0; i < itemAmount; i++) {
            finalItemsList.add(warehouse.getItems().get(random.nextInt(0, warehouse.getItems().size())));
        }
        for (int i = 0; i < 100; i++) {
            buyer.execute(() -> {
                for (int j = 0; j < finalItemsList.size(); j++) {
                    order.addItemToOrder(finalItemsList.get(j));
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            producer.execute(() -> {
                for (int j = 0; j < finalItemsList.size(); j++){
                    try {
                        order.checkingItemAvalibility(finalItemsList.get(j));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

    }

}
