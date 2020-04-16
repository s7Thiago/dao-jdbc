package model.dao.impl;

import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC() {

    }

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st;

        try {

            st = connection.prepareStatement(
                    "insert into department " +
                            "(Name)" +
                            "values (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAfeccted = st.executeUpdate();

            if (rowsAfeccted > 0) {
                ResultSet rs = st.getGeneratedKeys();

                while (rs.next()) {
                    obj.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
