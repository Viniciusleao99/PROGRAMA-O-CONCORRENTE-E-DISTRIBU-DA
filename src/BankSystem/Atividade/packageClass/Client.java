package packageClass;

import java.util.Random;

public class Client extends Thread {
    private String name;
    private BankAccount clientAccount;
    private Store[] stores;
    private Store lastChosenStore;
    private Bank bank;

    public Client(String name, Store[] stores, Bank bank) {
        this.name = name;
        this.clientAccount = new BankAccount(name, 1000); 
        this.stores = stores;
        this.bank = bank;
    }

    public void run() {
            Random random = new Random();
            while (clientAccount.getBalance() > 0) {
                // Randomly choose between $100.00 or $200.00
                double purchaseValue = random.nextDouble() < 0.5 ? 100.0 : 200.0;

                Store firstChosenStore;
                do{
                    int storeIndex = random.nextInt(stores.length);
                    firstChosenStore = stores[storeIndex];
                }while(firstChosenStore == lastChosenStore); 
                lastChosenStore = firstChosenStore; // Update

                if (clientAccount.getBalance() == 100.0 && purchaseValue == 200.0) {
                    purchaseValue = 100.0;
                }

                synchronized (this) {
                    bank.transfer(clientAccount, lastChosenStore.getStoreAccount(), purchaseValue);

                    System.out.println("\n" + clientAccount.getName()+ " made a purchase of $" + purchaseValue + " at " + lastChosenStore.getName());
                }
            }
    }

    public String getClientName() {
        return name;
    }

    public BankAccount getClientAccount() {
        return clientAccount;
    }
}