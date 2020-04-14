package application;

import db.DB;

import java.sql.*;
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
                            "(?, ?, ?, ?, ?)", // 'Placeholders' for the values to be inserted later
                    Statement.RETURN_GENERATED_KEYS
            );

            //                    Inserting the respective data according the prepared statement
            st.setString(1, "Carl Yellow");
            st.setString(2, "carl@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime()));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1); // We hava just 1 register being added to database. Because of that we are only taking the starting position
                    System.out.println("Done! the generated id was " + id);
                }
            } else {
                System.out.println("No rows affected :( ");
            }


        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        } finally {
//            Closing used resources
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}
