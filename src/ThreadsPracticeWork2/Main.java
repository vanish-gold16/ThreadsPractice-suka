package ThreadsPracticeWork2;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Client client1 = new Client(1);
        Client client2 = new Client(2);
        Client client3 = new Client(3);
        Client client4 = new Client(4);
        Client client5 = new Client(5);

        ClientsManagement.clients.add(client1);
        ClientsManagement.clients.add(client2);
        ClientsManagement.clients.add(client3);
        ClientsManagement.clients.add(client4);
        ClientsManagement.clients.add(client5);

        service.execute(new Threads());

        service.shutdown();
    }

    static class Threads implements Runnable {
        Random random = new Random();
        ClientsManagement management = new ClientsManagement();

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                int waitingTime = random.nextInt(100, 501);
                int clientID = random.nextInt(0, ClientsManagement.clients.size());


                try {
                    Thread.sleep(waitingTime);
                    management.executeTask(clientID, "Pidor");
                    System.out.println(ClientsManagement.clients.get(clientID).toString());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
