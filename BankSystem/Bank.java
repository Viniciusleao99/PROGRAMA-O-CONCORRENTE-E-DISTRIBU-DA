package BankSystem;

public class Bank {

    public synchronized void transfer(BankAccount source, BankAccount destination, double value) {
   
        if (source.getBalance() >= value) {
            source.debit(value);
            destination.credit(value);
        } else {
            System.out.println("\n Transfer could not be completed");
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}