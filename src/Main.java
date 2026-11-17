import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Expense> savedExpenses =
                FileHandler.loadExpenses("expenses.txt");

        ExpenseManager manager = new ExpenseManager(savedExpenses);

        int choice;

        do {

            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Total Expenses");
            System.out.println("5. Category-wise Expenses");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(sc.nextLine());

                    System.out.print("Enter category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter description: ");
                    String description = sc.nextLine();

                    int id = 1;

                    for (Expense expense : manager.getExpenses()) {
                        if (expense.getId() >= id) {
                            id = expense.getId() + 1;
                        }
                    }

                    Expense expense =
                            new Expense(id, amount, category, description);

                    manager.addExpense(expense);

                    FileHandler.saveExpenses(
                            manager.getExpenses(), "expenses.txt");

                    System.out.println("Expense added successfully!");
                    break;

                case 2:

                    System.out.println("\n--- ALL EXPENSES ---");

                    if (manager.getExpenses().isEmpty()) {
                        System.out.println("No expenses found.");
                    }

                    for (Expense e : manager.getExpenses()) {

                        System.out.println(
                                "ID: " + e.getId() +
                                        " | Amount: " + e.getAmount() +
                                        " | Category: " + e.getCategory() +
                                        " | Description: " + e.getDescription()
                        );
                    }

                    break;

                case 3:

                    System.out.print("Enter expense ID to delete: ");
                    int deleteId = Integer.parseInt(sc.nextLine());

                    if (manager.deleteExpense(deleteId)) {

                        FileHandler.saveExpenses(
                                manager.getExpenses(), "expenses.txt");

                        System.out.println("Expense deleted successfully!");

                    } else {
                        System.out.println("Expense ID not found.");
                    }

                    break;

                case 4:

                    System.out.println(
                            "Total Expenses: ₹" + manager.getTotalExpenses()
                    );

                    break;

                case 5:

                    System.out.println("\n--- CATEGORY-WISE EXPENSES ---");

                    for (Map.Entry<String, Double> entry :
                            manager.getCategoryTotals().entrySet()) {

                        System.out.println(
                                entry.getKey() + ": ₹" + entry.getValue()
                        );
                    }

                    break;

                case 6:

                    System.out.println("Thank you for using Expense Tracker!");
                    break;

                default:

                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}