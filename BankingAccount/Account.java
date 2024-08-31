package accountbanking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected String accountNumber;
    protected String accountOwner;
    protected double balance;
    protected boolean isActive;
    protected String accountType; // "student" or "working"
    protected List<String> transactionHistory;

    public Account(String accountNumber, String accountOwner, double balance, boolean isActive, String accountType) {
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.isActive = isActive;
        this.accountType = accountType;
        this.transactionHistory = new ArrayList<>();
        
        // Log initial balance only if the account is newly created and balance is set to 0 initially
        if (balance == 0.0) {
            logTransaction("Account created with initial balance: $0.0");
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountOwner() { return accountOwner; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }
    public String getAccountType() { return accountType; }
    public List<String> getTransactionHistory() { return transactionHistory; }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            logTransaction("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            logTransaction("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    protected void logTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Owner: " + accountOwner);
        System.out.println("Balance: $" + balance);
        System.out.println("Active: " + (isActive ? "Yes" : "No"));
        System.out.println("Account Type: " + accountType);
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(" - " + transaction);
        }
    }

    public abstract void handleMonthlyCharges();
}
