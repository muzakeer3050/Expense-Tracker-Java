import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {

    private ArrayList<Expense> expenses;

    public ExpenseManager(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public boolean deleteExpense(int id) {

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).getId() == id) {
                expenses.remove(i);
                return true;
            }
        }

        return false;
    }

    public double getTotalExpenses() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        return total;
    }

    public Map<String, Double> getCategoryTotals() {

        Map<String, Double> totals = new HashMap<>();

        for (Expense expense : expenses) {

            String category = expense.getCategory();

            totals.put(
                    category,
                    totals.getOrDefault(category, 0.0) + expense.getAmount()
            );
        }

        return totals;
    }
}