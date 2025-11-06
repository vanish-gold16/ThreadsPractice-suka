package ThreadsPracticeWork1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int ID;
    private int ballance;
    final ReentrantLock lock = new ReentrantLock();

    @Override
    public String toString() {
        return "Account " + ID + ", ballance: " + ballance;
    }

    public void deposit(int amount) throws InterruptedException {
        boolean isLocked = false;
        try{
            if(lock.tryLock(3, TimeUnit.SECONDS)) {
                isLocked = true;
                if (amount > 0)
                    setBallance(getBallance() + amount);
            }
        }finally{
            if(isLocked)
            lock.unlock();
        }
    }

    public void withdraw(int amount) throws InterruptedException {
        boolean isLocked = false;
        try{
            if(lock.tryLock(3, TimeUnit.SECONDS)) {
                isLocked = true;
                if(getBallance() > amount && amount > 0)
                    setBallance(getBallance() - amount);
                else{

                }
            }
        } finally{
            if(isLocked)
            lock.unlock();
        }
    }

    public BankAccount(int ID, int ballance) {
        this.ID = ID;
        this.ballance = ballance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getBallance() {
        return ballance;
    }

    public void setBallance(int ballance) {
        this.ballance = ballance;
    }
}
