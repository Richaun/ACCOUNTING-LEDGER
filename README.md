# ACCOUNTING-LEDGER
About the Project
This is a Java program that I made for my capstone project. The purpose of the program is to keep track of deposits and payments and store them in a CSV file so the information can be accessed later.

The program runs through a menu in the console. Users can add deposits, make payments, and look through their transaction history.

Features
Add deposits to the ledger
Record payments and automatically store them as negative amounts
View all transactions
View deposits and payments separately
View transactions from the current month or previous month
View transactions from the current year or previous year
Search for transactions by vendor
Display transactions with the newest ones first
Save transactions to a CSV file so they are still there when the program is reopened
How It Works
The program uses three Java classes:

Main: Displays the menus and handles user input.
Transaction: Holds the information for each transaction, including the date, time, description, vendor, and amount.
TransactionManager: Handles saving and loading transactions from the CSV file.
Transactions are stored in a file called transactions.csv. Each transaction is saved with its date, time, description, vendor, and amount, separated by a | character.

Technologies Used
Java
IntelliJ IDEA
Git and GitHub
CSV files
Running the Program
Open the project in IntelliJ IDEA.
Find and run Main.java.
Use the menu options to add deposits, make payments, or view the ledger.
Select X to exit the program.
What I Learned
While working on this project, I got more practice with Java classes, objects, methods, ArrayLists, loops, and working with files. I also learned more about how different classes can work together instead of putting everything into one file.

This project helped me understand how to organize a program and how information can be saved and loaded instead of disappearing when the program closes.
