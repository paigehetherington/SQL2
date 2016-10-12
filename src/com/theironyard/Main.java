package com.theironyard;

import org.h2.tools.Server;


import java.sql.*;

//from ZACH OAKES DATABASE CLASS

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        Server.createWebServer().start();

        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS players (id IDENTITY, name VARCHAR, score INT, health DOUBLE, is_alive BOOLEAN)");
        statement.execute("INSERT INTO players VALUES (NULL, 'Alice', 10, 100, true)");
        statement.execute("UPDATE players set health = 50 WHERE name = 'Alice'");
        statement.execute("DELETE from players WHERE name = 'Alice'");

        String name = "Charlie";

        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO players VALUES (NULL, ?, 10, 100, TRUE)");
        stmt2.setString(1, name);
        stmt2.execute();


        PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM players WHERE name = ?");
        stmt3.setString(1, name);
        ResultSet results = stmt3.executeQuery();
        while (results.next()) {
            int id = results.getInt("id");
            int score = results.getInt("score");
            double health = results.getDouble("health");
            boolean isAlive = results.getBoolean("is_alive");
            System.out.printf("%s, %s, %s, %s, %s/n", name, id, score, health, isAlive);
        }


    }
}
