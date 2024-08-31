package accountbanking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private Map<String, Account> accountsByMobile = new HashMap<>();
    private int accountCounter = 1000; // Simple counter to generate unique account numbers
    private Scanner scanner = new Scanner(System.in);

    public Bank() {
        // Initialize existing accounts
        accountsByMobile.put("existing1", new WorkingAccount("AC" + (accountCounter++), "Naveen", 7000.0, true));
        accountsByMobile.put("existing2", new StudentAccount("AC" + (accountCounter++), "Kalaiyarasi", 5000.0, true));
    }

    public void createAccount() {
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();

        if (accountsByMobile.containsKey(mobileNumber)) {
            System.out.println("Account already exists for mobile number: " + mobileNumber);
            return;
        }

        System.out.print("Enter account owner name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter account type (student/working): ");
        String accountType = scanner.nextLine().toLowerCase();

        Account newAccount;
        if (accountType.equals("student")) {
            newAccount = new StudentAccount("AC" + (accountCounter++), name, 0.0, true);
        } else if (accountType.equals("working")) {
            newAccount = new WorkingAccount("AC" + (accountCounter++), name, 0.0, true);
            System.out.print("Enter initial deposit amount (minimum $5000): ");
            double depositAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (depositAmount < 5000) {
                System.out.println("Initial deposit must be at least $5000 for working accounts.");
                return;
            }
            newAccount.deposit(depositAmount);
        } else {
            System.out.println("Invalid account type.");
            return;
        }

        accountsByMobile.put(mobileNumber, newAccount);
        System.out.println("Account created successfully for mobile number: " + mobileNumber);
    }

    public void depositOrWithdraw() {
        System.out.print("Enter mobile number for transaction: ");
        String mobileNumber = scanner.nextLine();

        Account account = accountsByMobile.get(mobileNumber);
        if (account == null) {
            System.out.println("No account found for mobile number: " + mobileNumber);
            return;
        }

        System.out.print("Enter 'deposit' or 'withdraw': ");
        String operation = scanner.nextLine().toLowerCase();

        if (operation.equals("deposit")) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.deposit(amount);
            System.out.println("Deposited: $" + amount);
        } else if (operation.equals("withdraw")) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.withdraw(amount);
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid operation.");
            return;
        }

        System.out.println("Updated Account Details:");
        account.printAccountDetails();
    }

    public void checkBalance() {
        System.out.print("Enter mobile number to check balance: ");
        String mobileNumber = scanner.nextLine();

        Account account = accountsByMobile.get(mobileNumber);
        if (account != null) {
            System.out.println("Current Balance: $" + account.getBalance());
        } else {
            System.out.println("No account found for mobile number: " + mobileNumber);
        }
    }

    public void handleMonthlyUpdates() {
        if (accountsByMobile.isEmpty()) {
            System.out.println("No accounts to process for monthly updates.");
            return;
        }

        for (Account account : accountsByMobile.values()) {
            account.handleMonthlyCharges();
        }
        System.out.println("Monthly updates processed for all accounts.");
    }

    public void printAccountDetails(String mobileNumber) {
        Account account = accountsByMobile.get(mobileNumber);
        if (account != null) {
            account.printAccountDetails();
        } else {
            System.out.println("No account found for mobile number: " + mobileNumber);
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();

        while (true) {
            System.out.println("\nBank Operations:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit/Withdraw");
            System.out.println("3. Print Account Details");
            System.out.println("4. Handle Monthly Updates");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.depositOrWithdraw();
                    break;
                case 3:
                    System.out.print("Enter mobile number to print details: ");
                    String mobileNumber = scanner.nextLine();
                    bank.printAccountDetails(mobileNumber);
                    break;
                case 4:
                    bank.handleMonthlyUpdates();
                    break;
                case 5:
                    bank.checkBalance();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
