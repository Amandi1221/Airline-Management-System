package airlinemanagementsystem;

import java.sql.*;
import javax.swing.JOptionPane;

public class AirlineManagementSystem {

    public Connection connection;
    public Statement stm;

    public AirlineManagementSystem() {
        String url = "jdbc:mysql://localhost:3306/airline_management";
        String user = "root";
        String[] passwords = {"1234", ""};

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            SQLException lastException = null;
            for (String password : passwords) {
                try {
                    connection = DriverManager.getConnection(url, user, password);
                    stm = connection.createStatement();
                    lastException = null;
                    break;
                } catch (SQLException ex) {
                    lastException = ex;
                }
            }
            if (connection == null && lastException != null) {
                throw lastException;
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: MySQL JDBC Driver Not Found. Ensure JAR is in Libraries.", "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Database Connection Failed. Check URL, username, or password: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static PreparedStatement prepareStatement(String query) throws SQLException {
        AirlineManagementSystem ams = new AirlineManagementSystem();
        if (ams.connection != null) {
            return ams.connection.prepareStatement(query);
        }
        throw new SQLException("Database connection not initialized.");
    }

    public void closeResources() {
        try {
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
        }
    }

    public class DBConnection {
        public Connection con;

        public DBConnection() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/airline_management";
            String user = "root";
            String[] passwords = {"1234", ""};
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                SQLException lastEx = null;
                for (String password : passwords) {
                    try {
                        con = DriverManager.getConnection(url, user, password);
                        lastEx = null;
                        break;
                    } catch (SQLException ex) {
                        lastEx = ex;
                    }
                }
                if (con == null && lastEx != null) {
                    throw lastEx;
                }
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
        }
    }

    public static class conn {
        public Connection c;
        public Statement s;

        public conn() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/airline_management";
                String user = "root";
                String[] passwords = {"1234", ""};
                for (String password : passwords) {
                    try {
                        c = DriverManager.getConnection(url, user, password);
                        s = c.createStatement();
                        break;
                    } catch (SQLException ex) {
                        // retry
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static PreparedStatement prepareStatement(String query) throws SQLException {
            conn connectionInstance = new conn();
            if (connectionInstance.c != null) {
                return connectionInstance.c.prepareStatement(query);
            }
            throw new SQLException("Connection failed.");
        }
    }

    public static void main(String[] args) {
        new AirlineManagementSystem();
        System.out.println("Database connection initialized.");
    }
}
