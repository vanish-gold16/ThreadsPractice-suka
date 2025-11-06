package ThreadsPracticeWork1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    static List<BankAccount> accounts = new ArrayList<BankAccount>();
    static private ReentrantLock lock = new ReentrantLock();

    public static void transfer(BankAccount from, BankAccount to, int amount) throws InterruptedException {

        BankAccount first = from.getID() < to.getID() ? from : to;
        BankAccount second = from.getID() < to.getID() ? to : from;
//                  | |
//        BankAccount first;
//        if (from.getID() < to.getID()) {
//            first = from;
//        } else {
//            first = to;
//        }

        boolean firstLocked = false;
        boolean secondLocked = false;

        try{
            if(first.getLock().tryLock(1, TimeUnit.SECONDS) && second.getLock().tryLock(1, TimeUnit.SECONDS)){
                firstLocked = true;
                secondLocked = true;
                if(from.getBallance() >= amount){
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        } finally{
            if(firstLocked)
                first.getLock().unlock();
            if(secondLocked)
                second.getLock().unlock();
        }
    }

    public static int numberOfAccounts(){
        return accounts.size();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }
}
