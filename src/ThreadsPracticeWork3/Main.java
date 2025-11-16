package ThreadsPracticeWork3;

public class Main {

    static Warehouse warehouse = new Warehouse();

    public static void main(String[] args) throws InterruptedException {


        OrderProcessor orderProcessor = new OrderProcessor(warehouse);

        orderProcessor.processingOrder();

        result();
    }

    public static void result(){
        for (int i = 0; i < warehouse.getItems().size(); i++) {
            System.out.println(warehouse.getItems().get(i));
        }
    }
}
