package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("====== TEST 1: Department insert ======");
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
        Department dp = new Department(null, "Phones");
//        departmentDao.insert(dp);
        System.out.println("Inserted department with id " + dp.getId());

        System.out.println("====== TEST 2: Department update ======");
        dp = new Department(5, "Music");
        departmentDao.update(dp);
        System.out.println("Department updated" + dp.getName());

        System.out.println("====== TEST 2: Department deleteById ======");
        System.out.print("Type the department id for deletion: ");
        int id = scanner.nextInt();
//        departmentDao.deleteById(id);
        System.out.println("Department deletion completed");

        System.out.println("====== TEST 2: Department findById ======");
        System.out.println("Finding department: \n\n" + departmentDao.findById(1));

        System.out.println("====== TEST 2: Department  findAll ======");
        List<Department> departments = departmentDao.findAll();
        System.out.println("Departments list: " + departments);
        
    }
}
