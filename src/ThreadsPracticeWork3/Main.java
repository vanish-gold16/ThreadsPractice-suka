package ThreadsPracticeWork3;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Warehouse warehouse = new Warehouse();

        OrderProcessor orderProcessor = new OrderProcessor(warehouse);

        Item threeXL = new Item("3XL", 100);
        Item rickOwensThirtyfive = new Item("RIck Owen's 35x", 20);
        Item erdBackpack = new Item("Erd Backpack", 10);
        Item saintLaurenJeans = new Item("Saint Lauren Jeans", 50);

        warehouse.addItem(threeXL);
        warehouse.addItem(rickOwensThirtyfive);
        warehouse.addItem(erdBackpack);
        warehouse.addItem(saintLaurenJeans);

        Order order1 = new Order(warehouse);

        order1.addItemToOrder(threeXL);
        order1.checkingItemAvalibility(threeXL);
        order1.addItemToOrder(threeXL);
        order1.checkingItemAvalibility(threeXL);
        order1.addItemToOrder(threeXL);
        order1.checkingItemAvalibility(threeXL);
        order1.addItemToOrder(threeXL);
        order1.checkingItemAvalibility(threeXL);
        order1.addItemToOrder(rickOwensThirtyfive);
        order1.checkingItemAvalibility(rickOwensThirtyfive);
        order1.addItemToOrder(erdBackpack);
        order1.checkingItemAvalibility(erdBackpack);
        order1.addItemToOrder(erdBackpack);
        order1.checkingItemAvalibility(erdBackpack);

        orderProcessor.processingOrder(order1);
    }
}
