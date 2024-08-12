import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {

    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public ATMSimulation(int initialPin, double initialBalance) {
        this.pin = initialPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Main method to run the ATM simulation
    public static void main(String[] args) {
        ATMSimulation atm = new ATMSimulation(1234, 1000.00);
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (true) {
            System.out.println("Welcome to the ATM. Please enter your PIN:");
            int enteredPin = scanner.nextInt();
            
            if (atm.verifyPIN(enteredPin)) {
                loggedIn = true;
                while (loggedIn) {
                    System.out.println("\nATM Menu:");
                    System.out.println("1. Account Balance Inquiry");
                    System.out.println("2. Cash Withdrawal");
                    System.out.println("3. Cash Deposit");
                    System.out.println("4. Change PIN");
                    System.out.println("5. Transaction History");
                    System.out.println("6. Exit");
                    System.out.print("Select an option: ");
                    
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            atm.inquireBalance();
                            break;
                        case 2:
                            atm.withdrawCash(scanner);
                            break;
                        case 3:
                            atm.depositCash(scanner);
                            break;
                        case 4:
                            atm.changePIN(scanner);
                            break;
                        case 5:
                            atm.showTransactionHistory();
                            break;
                        case 6:
                            System.out.println("Thank you for using the ATM. Goodbye!");
                            loggedIn = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                }
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }
    }

    // Verifies if the entered PIN matches the stored PIN
    private boolean verifyPIN(int enteredPin) {
        return this.pin == enteredPin;
    }

    // Displays the current balance
    private void inquireBalance() {
        System.out.printf("Your current balance is: $%.2f%n", this.balance);
    }

    // Withdraws cash from the account
    private void withdrawCash(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
        } else if (amount > this.balance) {
            System.out.println("Insufficient funds.");
        } else {
            this.balance -= amount;
            this.transactionHistory.add("Withdrew: $" + amount);
            System.out.printf("You have withdrawn: $%.2f%n", amount);
            inquireBalance();
        }
    }

    // Deposits cash into the account
    private void depositCash(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
        } else {
            this.balance += amount;
            this.transactionHistory.add("Deposited: $" + amount);
            System.out.printf("You have deposited: $%.2f%n", amount);
            inquireBalance();
        }
    }

    // Changes the PIN of the account
    private void changePIN(Scanner scanner) {
        System.out.print("Enter new PIN: ");
        int newPin = scanner.nextInt();
        if (newPin < 1000 || newPin > 9999) {
            System.out.println("PIN must be a 4-digit number.");
        } else {
            this.pin = newPin;
            System.out.println("PIN successfully changed.");
        }
    }

    // Displays the transaction history
    private void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
