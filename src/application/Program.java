package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {

//        Updating data on database
        Connection conn;

        PreparedStatement st = null;

        try {
            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "delete from seller " +
                            "where " +
                            "id = ?");

//            Setting respective id for deletion
            st.setInt(1, 8);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done!Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
