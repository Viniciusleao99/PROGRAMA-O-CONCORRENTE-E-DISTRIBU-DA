package packageClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Store store1 = new Store("Store 1");
        Store store2 = new Store("Store 2");

        Store[] availableStores = {store1, store2};

        Employee employee1 = new Employee("Employee1", store1, bank);
        Employee employee2 = new Employee("Employee2", store1, bank);
        Employee employee3 = new Employee("Employee3", store2, bank);
        Employee employee4 = new Employee("Employee4", store2, bank);

        List<Employee> employeesStore1 = new ArrayList<>();
        employeesStore1.add(employee1);
        employeesStore1.add(employee2);

        List<Employee> employeesStore2 = new ArrayList<>();
        employeesStore2.add(employee3);
        employeesStore2.add(employee4);

        Client client1 = new Client("Client 1", availableStores, bank);
        client1.start();
        Client client2 = new Client("Client 2", availableStores, bank);
        client2.start();
        Client client3 = new Client("Client 3", availableStores, bank);
        client3.start();
        Client client4 = new Client("Client 4", availableStores, bank);
        client4.start();
        Client client5 = new Client("Client 5", availableStores, bank);
        client5.start();

        try {
            client1.join();
            client2.join();
            client3.join();
            client4.join();
            client5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        employee1.start();
        employee2.start();
        employee3.start();
        employee4.start();

        try {
            employee1.join();
            employee2.join();
            employee3.join();
            employee4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Employee employee : employeesStore1) {
            System.out.println("\nSalary account from " + employee.getEmployeeName() + " has: $" + employee.getSalaryAccount().getBalance());
            System.out.println("\nInvestment account from " + employee.getEmployeeName() + " has: $" + employee.getInvestmentAccount().getBalance());
        }
   
        for (Employee employee : employeesStore2) {
            System.out.println("\nSalary account from " + employee.getEmployeeName() + " has: $" + employee.getSalaryAccount().getBalance());
            System.out.println("\nInvestment account from " + employee.getEmployeeName() + " has: $" + employee.getInvestmentAccount().getBalance());
        }

        System.out.println("\n" + store1.getName() + ": $" + store1.getStoreAccount().getBalance());
        System.out.println("\n" + store2.getName() + ": $" + store2.getStoreAccount().getBalance());

        System.out.println("\n" + client1.getClientName() + ": $" + client1.getClientAccount().getBalance());
        System.out.println("\n" + client2.getClientName() + ": $" + client2.getClientAccount().getBalance());
        System.out.println("\n" + client3.getClientName() + ": $" + client3.getClientAccount().getBalance());
        System.out.println("\n" + client4.getClientName() + ": $" + client4.getClientAccount().getBalance());
        System.out.println("\n" + client5.getClientName() + ": $" + client5.getClientAccount().getBalance());
    }
}