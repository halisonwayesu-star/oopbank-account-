package bank.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_PATH = "database/FirstBank.accdb";
    private static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    private static final String URL = "jdbc:ucanaccess://" + DB_PATH + ";memory=false";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("UCanAccess Driver not found.", e);
        }
    }

    public static void createTableIfNotExists() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Accounts (\n" +
                                    "ID COUNTER PRIMARY KEY,\n" +
                                    "AccountNumber VARCHAR(255) NOT NULL UNIQUE,\n" +
                                    "FirstName VARCHAR(255),\n" +
                                    "LastName VARCHAR(255),\n" +
                                    "NIN VARCHAR(255),\n" +
                                    "SecondNIN VARCHAR(255),\n" +
                                    "Email VARCHAR(255),\n" +
                                    "Phone VARCHAR(255),\n" +
                                    "DOB DATE,\n" +
                                    "Age INTEGER,\n" +
                                    "AccountType VARCHAR(255),\n" +
                                    "Branch VARCHAR(255),\n" +
                                    "Deposit DOUBLE,\n" +
                                    "PIN VARCHAR(255),\n" +
                                    "DateCreated DATE\n" +
                                    ")";
            stmt.execute(createTableSQL);
            System.out.println("Accounts table checked/created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
