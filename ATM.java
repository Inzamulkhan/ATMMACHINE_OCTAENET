
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

// Class to represent a bank account
class BankAccount {
    String accountNumber;
    String pin;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(String accountNumber, String pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(String.format("%s: Deposit $%.2f", new Date(), amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(String.format("%s: Withdrawal $%.2f", new Date(), amount));
            return true;
        }
        return false;
    }

    public void changePin(String newPin) {
        pin = newPin;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactions;
    }
}

// ATM Machine class
public class ATM {
    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        // Simulating account creation (in real app, this would be fetched from database)
        BankAccount account = new BankAccount("1234567890", "1234");
        account.deposit(1000); // Initial deposit
        currentAccount = account;

        login(); // Start login process
    }

    private void login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (currentAccount != null && currentAccount.pin.equals(pin) && currentAccount.accountNumber.equals(accountNumber)) {
            showMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
            login();
        }
    }

    private void showMenu() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawCash();
                    break;
                case 3:
                    depositCash();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
                    break;
            }
        } while (choice != 6);
    }

    private void checkBalance() {
        System.out.println("Your balance is: $" + currentAccount.getBalance());
    }

    private void withdrawCash() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        if (currentAccount.withdraw(amount)) {
            System.out.println("Please take your cash.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private void depositCash() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        currentAccount.deposit(amount);
        System.out.println("Deposit successful.");
    }

    private void changePin() {
        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine();

        currentAccount.changePin(newPin);
        System.out.println("PIN changed successfully.");
    }

    private void viewTransactionHistory() {
        ArrayList<String> transactions = currentAccount.getTransactionHistory();
        System.out.println("\nTransaction History:");
        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
