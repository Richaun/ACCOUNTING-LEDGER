package org.example;

import java.util.Scanner;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TransactionManager manager = new TransactionManager();

        try {
            manager.loadTransactions();
        } catch (IOException e) {
            System.out.println("Error loading transactions.");
        }

        boolean running = true;

        while (running) {

            System.out.println("Welcome to the Accounting Ledger!");
            System.out.println();
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {

                case "D":
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter vendor: ");
                    String vendor = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());

                    Transaction deposit = new Transaction();

                    deposit.setDate(LocalDate.now());
                    deposit.setTime(LocalTime.now());
                    deposit.setDescription(description);
                    deposit.setVendor(vendor);
                    deposit.setAmount(amount);

                    try {
                        manager.addTransaction(deposit);
                        System.out.println("Deposit saved!");
                    } catch (IOException e) {
                        System.out.println("Error saving deposit.");
                    }

                    break;

                case "P":
                    System.out.print("Enter description: ");
                    String paymentDescription = scanner.nextLine();

                    System.out.print("Enter vendor: ");
                    String paymentVendor = scanner.nextLine();

                    System.out.print("Enter amount: ");
                    BigDecimal paymentAmount = new BigDecimal(scanner.nextLine());

                    Transaction payment = new Transaction();

                    payment.setDate(LocalDate.now());
                    payment.setTime(LocalTime.now());
                    payment.setDescription(paymentDescription);
                    payment.setVendor(paymentVendor);
                    payment.setAmount(paymentAmount.negate());

                    try {
                        manager.addTransaction(payment);
                        System.out.println("Payment saved!");
                    } catch (IOException e) {
                        System.out.println("Error saving payment.");
                    }

                    break;

                case "L":
                    // Make a sorted copy so the newest transactions appear first.
                    ArrayList<Transaction> sortedTransactions =
                            new ArrayList<>(manager.getTransactions());

                    sortedTransactions.sort(
                            Comparator.comparing(Transaction::getDate)
                                    .thenComparing(Transaction::getTime)
                                    .reversed()
                    );

                    boolean inLedger = true;

                    while (inLedger) {
                        System.out.println("\n--- LEDGER ---");
                        System.out.println("A) All Transactions");
                        System.out.println("D) Deposits");
                        System.out.println("P) Payments");
                        System.out.println("R) Reports");
                        System.out.println("H) Home");

                        System.out.print("Choose an option: ");
                        String ledgerChoice = scanner.nextLine().toUpperCase();

                        switch (ledgerChoice) {

                            case "A":
                                for (Transaction transaction : sortedTransactions) {
                                    System.out.println(
                                            transaction.getDate() + " | " +
                                                    transaction.getTime() + " | " +
                                                    transaction.getDescription() + " | " +
                                                    transaction.getVendor() + " | " +
                                                    transaction.getAmount()
                                    );
                                }
                                break;

                            case "D":
                                for (Transaction transaction : sortedTransactions) {
                                    if (transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                                        System.out.println(
                                                transaction.getDate() + " | " +
                                                        transaction.getTime() + " | " +
                                                        transaction.getDescription() + " | " +
                                                        transaction.getVendor() + " | " +
                                                        transaction.getAmount()
                                        );
                                    }
                                }
                                break;

                            case "P":
                                for (Transaction transaction : sortedTransactions) {
                                    if (transaction.getAmount().compareTo(BigDecimal.ZERO) < 0) {
                                        System.out.println(
                                                transaction.getDate() + " | " +
                                                        transaction.getTime() + " | " +
                                                        transaction.getDescription() + " | " +
                                                        transaction.getVendor() + " | " +
                                                        transaction.getAmount()
                                        );
                                    }
                                }
                                break;

                            case "R":
                                boolean inReports = true;

                                while (inReports) {
                                    System.out.println("\n--- REPORTS ---");
                                    System.out.println("1) Month To Date");
                                    System.out.println("2) Previous Month");
                                    System.out.println("3) Year To Date");
                                    System.out.println("4) Previous Year");
                                    System.out.println("5) Search by Vendor");
                                    System.out.println("0) Back");

                                    System.out.print("Choose an option: ");
                                    String reportChoice = scanner.nextLine();

                                    switch (reportChoice) {

                                        case "0":
                                            inReports = false;
                                            break;

                                        case "1":
                                            LocalDate today = LocalDate.now();

                                            for (Transaction transaction : sortedTransactions) {
                                                if (transaction.getDate().getMonth() == today.getMonth()
                                                        && transaction.getDate().getYear() == today.getYear()) {

                                                    System.out.println(
                                                            transaction.getDate() + " | " +
                                                                    transaction.getTime() + " | " +
                                                                    transaction.getDescription() + " | " +
                                                                    transaction.getVendor() + " | " +
                                                                    transaction.getAmount()
                                                    );
                                                }
                                            }
                                            break;

                                        case "2":
                                            LocalDate previousMonth = LocalDate.now().minusMonths(1);

                                            for (Transaction transaction : sortedTransactions) {
                                                if (transaction.getDate().getMonth() == previousMonth.getMonth()
                                                        && transaction.getDate().getYear() == previousMonth.getYear()) {

                                                    System.out.println(
                                                            transaction.getDate() + " | " +
                                                                    transaction.getTime() + " | " +
                                                                    transaction.getDescription() + " | " +
                                                                    transaction.getVendor() + " | " +
                                                                    transaction.getAmount()
                                                    );
                                                }
                                            }
                                            break;

                                        case "3":
                                            int currentYear = LocalDate.now().getYear();

                                            for (Transaction transaction : sortedTransactions) {
                                                if (transaction.getDate().getYear() == currentYear) {

                                                    System.out.println(
                                                            transaction.getDate() + " | " +
                                                                    transaction.getTime() + " | " +
                                                                    transaction.getDescription() + " | " +
                                                                    transaction.getVendor() + " | " +
                                                                    transaction.getAmount()
                                                    );
                                                }
                                            }
                                            break;

                                        case "4":
                                            int previousYear = LocalDate.now().getYear() - 1;

                                            for (Transaction transaction : sortedTransactions) {
                                                if (transaction.getDate().getYear() == previousYear) {

                                                    System.out.println(
                                                            transaction.getDate() + " | " +
                                                                    transaction.getTime() + " | " +
                                                                    transaction.getDescription() + " | " +
                                                                    transaction.getVendor() + " | " +
                                                                    transaction.getAmount()
                                                    );
                                                }
                                            }
                                            break;

                                        case "5":
                                            System.out.print("Enter vendor name: ");
                                            String searchVendor = scanner.nextLine();

                                            for (Transaction transaction : sortedTransactions) {
                                                if (transaction.getVendor().equalsIgnoreCase(searchVendor)) {

                                                    System.out.println(
                                                            transaction.getDate() + " | " +
                                                                    transaction.getTime() + " | " +
                                                                    transaction.getDescription() + " | " +
                                                                    transaction.getVendor() + " | " +
                                                                    transaction.getAmount()
                                                    );
                                                }
                                            }
                                            break;

                                        default:
                                            System.out.println("Invalid option. Try again.");
                                            break;
                                    }
                                }
                                break;

                            case "H":
                                inLedger = false;
                                break;

                            default:
                                System.out.println("Invalid option. Try again.");
                                break;
                        }
                    }
                    break;

                case "X":
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }
}