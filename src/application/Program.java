package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

//        Inserting data on seller table
        Connection connection;
        PreparedStatement st = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            connection = DB.getConnection(); // Opening database connection

//            Insertion command
            st = connection.prepareStatement(
                    "insert into seller " +
                            "(Name, Email, BirthDate, BaseSalary, DepartmentID) " +
                            "Values " +
                            "(?, ?, ?, ?, ?)" // 'Placeholders' for the values to be inserted later
            );

            //                    Inserting the respective data according the prepared statement
            st.setString(1, "Carl Yellow");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! rows affected: " + rowsAffected);

        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
//            Closing used resources
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}
