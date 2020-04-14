package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        Connection conn;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            Creating the database connection and executing query on department table
            conn = DB.getConnection();
            statement = conn.createStatement();

            resultSet = statement.executeQuery("select * from department"); // Storing query result

//            traversing the department table data while has next row
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + ": " + resultSet.getString("Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
            DB.closeConnection();
        }
    }
}
