package Task09_3;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuizModel {

    private Connection conn = null;
    private String url = "jdbc:derby:PlayerDB;create=true";
    private String dbusername = "pdc";
    private String dbpassword = "pdc";

    private String currentUsername = null;
    // No need to store password in model after login for this simple app
    private int currentScore = 0;
    private int currentAnswer = 0;
    private int num1 = 0;
    private int num2 = 0;

    public QuizModel() {
        dbsetup();
    }

    private void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate(
                        "CREATE TABLE " + tableName + " (userid VARCHAR(12), password VARCHAR(12), score INT)");
            }
            statement.close();
        } catch (Throwable e) {
            System.out.println("error in dbsetup: " + e.getMessage());
            // Logger.getLogger(QuizModel.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {
            if (conn == null) { // Ensure conn is initialized
                System.out.println("Connection not established in checkTableExisting.");
                return false;
            }
            System.out.println("check existing tables.... ");
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, newTableName.toUpperCase(), null); // Derby stores table
                                                                                               // names in uppercase
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                    break;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            // Logger.getLogger(QuizModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL error in checkTableExisting: " + ex.getMessage());
        }
        return flag;
    }

    public boolean attemptLogin(String username, String password) {
        this.currentUsername = username; // Tentatively set username
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, password, score FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                if (password.compareTo(pass) == 0) {
                    currentScore = rs.getInt("score");
                    rs.close();
                    statement.close();
                    return true;
                } else {
                    rs.close();
                    statement.close();
                    return false; // Wrong password
                }
            } else { // New user
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + username + "', '" + password + "', 0)");
                currentScore = 0;
                rs.close();
                statement.close();
                return true; // New user created and logged in
            }
        } catch (SQLException ex) {
            // Logger.getLogger(QuizModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL error in attemptLogin: " + ex.getMessage());
            return false;
        }
    }

    public void generateNewQuestion() {
        num1 = getRandomNumber();
        num2 = getRandomNumber();
        currentAnswer = num1 + num2;
    }

    private int getRandomNumber() {
        Random generator = new Random();
        return generator.nextInt(100);
    }

    public boolean checkAnswer(String userAnswerString) {
        if (userAnswerString == null || userAnswerString.isEmpty())
            return false;
        try {
            int userAnswer = Integer.parseInt(userAnswerString);
            if (userAnswer == currentAnswer) {
                currentScore += 10;
                return true;
            } else {
                currentScore -= 10;
                return false;
            }
        } catch (NumberFormatException e) {
            currentScore -= 10; // Penalize for non-numeric input
            return false;
        }
    }

    public void saveScore() {
        if (currentUsername == null)
            return; // No user to save score for
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(
                    "UPDATE UserInfo SET score=" + currentScore + " WHERE userid='" + currentUsername + "'");
            System.out.println(currentUsername + " score updated to " + currentScore);
            statement.close();
        } catch (SQLException ex) {
            // Logger.getLogger(QuizModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL error in saveScore: " + ex.getMessage());
        }
    }

    public void closeDbConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // Logger.getLogger(QuizModel.class.getName()).log(Level.SEVERE, "Error closing
            // DB", e);
            System.out.println("SQL error closing DB: " + e.getMessage());
        }
    }

    // Getters
    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}