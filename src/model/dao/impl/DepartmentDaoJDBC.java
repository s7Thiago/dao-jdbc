package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement st = null;

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
                    rs.close();
                }
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try {

            st = connection.prepareStatement("update department " +
                    "set Name = ? where Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {

            st = connection.prepareStatement("delete from department where id = ? ");

            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = connection.prepareStatement("select * " +
                    "from department " +
                    "where Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

//            Instantiating a department object when exist the first position
            if (rs.next()) {
                Department department = new Department();

                department.setId(rs.getInt("Id"));
                department.setName(rs.getString("Name"));

                return department;
            }


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

        return null;
    }

    @Override
    public List<Department> findAll() {

        List<Department> departments = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {

            st = connection.prepareStatement("select * from department order by Name");

            rs = st.executeQuery();

            while (rs.next()) {
                Department department = new Department(rs.getInt("Id"), rs.getString("Name"));
                departments.add(department);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }   finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return departments;
    }
}