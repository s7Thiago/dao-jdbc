package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

//        Updating data on database
        Connection conn;

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "update seller " +
                            "set BaseSalary = BaseSalary + ? " +
                            "where " +
                            "(DepartmentId = ?)");

//            Setting respective values
            st.setDouble(1,200.00);
            st.setInt(2, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done!Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
