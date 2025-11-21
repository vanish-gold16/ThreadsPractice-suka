package FastPizzaFinalProject;

import java.util.Random;

public class Order {

    Random random = new Random();

    private String name;
    private int Id;
    private int cookingTime;

    public Order(String name, int id) {
        this.name = name;
        Id = id;
        this.cookingTime = random.nextInt(200, 501);
    }

    @Override
    public String toString() {
        return "Order number " + Id + ", " + name + ", cooking time " + cookingTime + "ms";
    }

    public static String generateRandomOrder(Random random){
        int orderNumber = random.nextInt(1, 4);
        switch(orderNumber){
            case 1:
                return "Pepperoni";
            case 2:
                return "4 Cheese";
            case 3:
                return "Margarita";
            default:
                return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }
}
