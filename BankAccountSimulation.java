// File: BankAccountSimulation.java

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Account class
class Account {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + accountHolder + ":");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

// Inheritance example: SavingsAccount
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        super(accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    // Method overriding
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        double interest = amount * interestRate / 100;
        super.deposit(interest);
        System.out.println("Interest added: " + interest);
    }
}

// Main Simulation
public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        Account account = new SavingsAccount(name, 1000, 2.5);

        int choice;
        do {
            System.out.println("Bank Menu ");
            System.out.println("   1. Deposit");
            System.out.println("   2. Withdraw");
            System.out.println("   3. Check Balance");
            System.out.println("   4. Transaction History");
            System.out.println("   5. Exit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    account.deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter withdraw amount: ");
                    double wit = sc.nextDouble();
                    account.withdraw(wit);
                    break;
                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;
                case 4:
                    account.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);

        sc.close();
    }
}
