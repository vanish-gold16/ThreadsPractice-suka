package ThreadsPracticeWork1;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount bankAccount1 = new BankAccount(001, 1000);
        BankAccount bankAccount2 = new BankAccount(002, 100);
        BankAccount bankAccount3 = new BankAccount(003, 500);

        bank.addAccount(bankAccount1);
        bank.addAccount(bankAccount2);
        bank.addAccount(bankAccount3);

        System.out.println(bankAccount1.getBallance() + bankAccount2.getBallance() + bankAccount3.getBallance());
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {
            // Thread.sleep(1000);
            service.execute(new Clients());
        }

        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println(bankAccount1.getBallance() + bankAccount2.getBallance() + bankAccount3.getBallance());

    }

    static class Clients implements Runnable{
        Random rand = new Random();
        @Override
        public void run() {
            int randomAccount1 = rand.nextInt(1, Bank.numberOfAccounts());
            int randomAccount2 = rand.nextInt(1, Bank.numberOfAccounts());
            int randomAmount = rand.nextInt(1, 100);

            try {
                Bank.transfer(Bank.accounts.get(randomAccount1), Bank.accounts.get(randomAccount2), randomAmount);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}
