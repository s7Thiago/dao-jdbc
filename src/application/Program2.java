package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("====== TEST 1: Seller insert ======");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dp = new Department(null, "Phones");
        departmentDao.insert(dp);
        System.out.println("Inserted department with id " + dp.getId());
    }
}
