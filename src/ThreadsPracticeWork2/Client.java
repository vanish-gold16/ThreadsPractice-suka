package ThreadsPracticeWork2;

import java.util.concurrent.locks.ReentrantLock;

public class Client {
    private int id;

    public Client(int id) {
        this.id = id;
    }

    public String task(String message){
        return message;
    }

    @Override
    public String toString() {
        return "Client " + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
