package BankSystem;

public class Store {
    private final String name;
    private final BankAccount storeAccount;

    public Store(String name) {
        this.name = name;
        this.storeAccount = new BankAccount(name, 0);
    }

    public BankAccount getStoreAccount() {
        return storeAccount;
    }

    public String getName() {
        return name;
    }
}