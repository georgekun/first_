package ru.jorj.spring.back.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFirstDb {
    private static final String URL = "jdbc:mysql://localhost:3306/first_db";
    private static final String PASSWORD = "Password159";
    private static final String USER = "root";

    public static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Statement connectToDataBase() throws SQLException {
        Statement stat = connection.createStatement();
        return stat;
    }
}
