package expenseTracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            DBConnector dbConnector = new DBConnector();
            Connection connection = dbConnector.getConnection();
            ExpenseDAO expenseDAO = new ExpenseDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\nExpense Tracker Menu:");
                System.out.println("1. Add Expense");
                System.out.println("2. Update Expense");
                System.out.println("3. Delete Expense");
                System.out.println("4. View All Expenses");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addExpense(scanner, expenseDAO);
                        break;
                    case 2:
                        updateExpense(scanner, expenseDAO);
                        break;
                    case 3:
                        deleteExpense(scanner, expenseDAO);
                        break;
                    case 4:
                        viewAllExpenses(expenseDAO);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

            } while (choice != 5);

            dbConnector.closeConnection();
            scanner.close();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addExpense(Scanner scanner, ExpenseDAO expenseDAO) throws SQLException {
        System.out.println("\nEnter Expense Details:");
        System.out.print("Date (yyyy-MM-dd): ");
        String dateString = scanner.next();
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Category: ");
        String category = scanner.next();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        Expense expense = new Expense();
        expense.setDate(java.sql.Date.valueOf(dateString));
        expense.setDescription(description);
        expense.setCategory(category);
        expense.setAmount(amount);

        expenseDAO.insertExpense(expense);
        System.out.println("Expense added successfully.");
    }

    private static void updateExpense(Scanner scanner, ExpenseDAO expenseDAO) throws SQLException {
        System.out.print("\nEnter Expense ID to Update: ");
        int id = scanner.nextInt();

        Expense existingExpense = null;
        List<Expense> expenses = expenseDAO.findAllExpenses();
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                existingExpense = expense;
                break;
            }
        }

        if (existingExpense == null) {
            System.out.println("Expense not found with ID " + id);
            return;
        }

        System.out.println("\nEnter New Expense Details:");
        System.out.print("Date (yyyy-MM-dd): ");
        String dateString = scanner.next();
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Category: ");
        String category = scanner.next();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        existingExpense.setDate(java.sql.Date.valueOf(dateString));
        existingExpense.setDescription(description);
        existingExpense.setCategory(category);
        existingExpense.setAmount(amount);

        expenseDAO.updateExpense(existingExpense);
        System.out.println("Expense updated successfully.");
    }

    private static void deleteExpense(Scanner scanner, ExpenseDAO expenseDAO) throws SQLException {
        System.out.print("\nEnter Expense ID to Delete: ");
        int id = scanner.nextInt();

        expenseDAO.deleteExpense(id);
        System.out.println("Expense deleted successfully.");
    }

    private static void viewAllExpenses(ExpenseDAO expenseDAO) throws SQLException {
        List<Expense> expenses = expenseDAO.findAllExpenses();
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            System.out.println("\nAll Expenses:");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }
}
