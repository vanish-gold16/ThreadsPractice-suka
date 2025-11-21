package FastPizzaFinalProject;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Kitchen kitchen = new Kitchen();

        kitchen.startProgramm();
    }

    static void result() {
        System.out.println(new Kitchen().getOrders().size());
    }

}
