package FastPizzaFinalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Kitchen {

    private List<Order> orders =  new ArrayList<Order>();

    private ExecutorService chefs = Executors.newFixedThreadPool(3);


    public void newOrder(Order order) {
        orders.add(order);
    }

}

