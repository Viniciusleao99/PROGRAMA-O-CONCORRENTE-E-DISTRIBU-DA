package BankSystem;

public class BankAccount {
    private final String name;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
    }
    
    public synchronized void debit(double value) {
        balance -= value;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void credit(double value) {
        balance += value;
    }
}