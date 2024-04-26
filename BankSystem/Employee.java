package BankSystem;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Employee extends Thread {
    private String name;
    private Store store;
    private BankAccount salaryAccount;
    private BankAccount investmentAccount;
    private Bank bank;
    private final Lock lock = new ReentrantLock();

    public Employee(String name, Store store, Bank bank) {
        this.name = name;
        this.store = store;
        this.salaryAccount = new BankAccount("\n" + name + " Salary", 0);
        this.investmentAccount = new BankAccount("\n" + name + " Investment", 0);
        this.bank = bank;
    }

    public void run() {
        receiveSalary();
        fromSalaryAccountToInvestmentAccount();
    }

    private void receiveSalary() {
        double salary = 1400.0;
        lock.lock();
        try {
            synchronized (store) {
                if (store.getStoreAccount().getBalance() >= salary) {
                    bank.transfer(store.getStoreAccount(), getSalaryAccount(), salary);
                    System.out.println("\n Salary of " + name + " paid by " + store.getName());
                } else {
                    System.out.println("\n" + store.getName() + " does not have enough balance to pay the salary.");
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void fromSalaryAccountToInvestmentAccount() {
        lock.lock();
        try {
            double investmentValue = getSalaryAccount().getBalance() * 0.2; 
            if (investmentValue == 0) {
                System.out.println("\n" + name + " does not have enough balance to invest.");
            } else {
                bank.transfer(salaryAccount, investmentAccount, investmentValue);
                System.out.println("\n" + name + " invested $" + investmentValue);
            }
        } finally {
            lock.unlock();
        }
    }
    
    public String getEmployeeName() {
        return name;
    }

    public BankAccount getInvestmentAccount() {
        return investmentAccount;
    }

    public BankAccount getSalaryAccount() {
        return salaryAccount;
    }
}