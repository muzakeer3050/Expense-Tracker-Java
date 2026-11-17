import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void saveExpenses(
            ArrayList<Expense> expenses, String filename) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(filename))) {

            for (Expense expense : expenses) {

                writer.write(
                        expense.getId() + "|" +
                                expense.getAmount() + "|" +
                                expense.getCategory() + "|" +
                                expense.getDescription()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    public static ArrayList<Expense> loadExpenses(String filename) {

        ArrayList<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", 4);

                if (data.length == 4) {

                    Expense expense = new Expense(
                            Integer.parseInt(data[0]),
                            Double.parseDouble(data[1]),
                            data[2],
                            data[3]
                    );

                    expenses.add(expense);
                }
            }

        } catch (FileNotFoundException e) {
            // First-time use: no saved file yet.
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading expenses.");
        }

        return expenses;
    }
}