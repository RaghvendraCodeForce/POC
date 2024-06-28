package expenseTracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    private Connection connection;

    public ExpenseDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO expenses (date, description, category, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(expense.getDate().getTime()));
            statement.setString(2, expense.getDescription());
            statement.setString(3, expense.getCategory());
            statement.setDouble(4, expense.getAmount());

            statement.executeUpdate();
        }
    }

    public void updateExpense(Expense expense) throws SQLException {
        String sql = "UPDATE expenses SET date = ?, description = ?, category = ?, amount = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(expense.getDate().getTime()));
            statement.setString(2, expense.getDescription());
            statement.setString(3, expense.getCategory());
            statement.setDouble(4, expense.getAmount());
            statement.setInt(5, expense.getId());

            statement.executeUpdate();
        }
    }

    public void deleteExpense(int expenseId) throws SQLException {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, expenseId);
            statement.executeUpdate();
        }
    }

    public List<Expense> findAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getInt("id"));
                expense.setDate(resultSet.getDate("date"));
                expense.setDescription(resultSet.getString("description"));
                expense.setCategory(resultSet.getString("category"));
                expense.setAmount(resultSet.getDouble("amount"));

                expenses.add(expense);
            }
        }
        return expenses;
    }
}
