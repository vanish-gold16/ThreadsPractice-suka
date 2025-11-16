package ThreadsPracticeWork3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OrderProcessor {

    Warehouse warehouse = new Warehouse();
    Random random = new Random();

    private final List<Item> finalItemsList = new ArrayList<Item>();

    private final ExecutorService buyer = Executors.newFixedThreadPool(5);
    private final ExecutorService producer = Executors.newFixedThreadPool(2);

    public OrderProcessor(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void processingOrder(){

        int itemAmount = random.nextInt(1, 5);
        for (int i = 0; i < itemAmount; i++) {
            finalItemsList.add(warehouse.getItems().get(random.nextInt(0, warehouse.getItems().size())));
        }

        /**
         * generating 20 customers
         */
        for (int i = 0; i < 20; i++) {

            Order order = new Order(this.warehouse);

            buyer.execute(() -> {
                for (int j = 0; j < finalItemsList.size(); j++) {

                    order.buyItem(finalItemsList.get(j));
                }
            });
        }

        /**
         * generating 5 workers
         */
        for (int i = 0; i < 5; i++) {
            producer.execute(() -> {
                for (int j = 0; j < finalItemsList.size(); j++){
                    try {
                        warehouse.checkingItemAvalibility(finalItemsList.get(j));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        buyer.shutdown();
        producer.shutdown();

        try{
            System.out.println("Waiting for buyers to finish...");
            buyer.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("Waiting for producers to finish...");
            producer.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All processing finished");

    }

}
