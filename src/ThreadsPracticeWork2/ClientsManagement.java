package ThreadsPracticeWork2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ClientsManagement {
    static List<Client> clients = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();

    public void executeTask(int clientID, String message) throws InterruptedException {
        boolean isLocked = false;
        if(lock.tryLock(1, TimeUnit.SECONDS)){
            isLocked = true;
            try{
                clients.get(clientID).task(message);
            } finally {
                if(isLocked)
                    lock.unlock();
            }
        }
    }

    public void addClient(Client client){
        clients.add(client);
    }
}
