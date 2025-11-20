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
