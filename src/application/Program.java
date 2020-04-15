package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("====== TEST 1: Seller findById ======");
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n\n====== TEST 2: Seller findByDepartment ======");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller s : list) {
            System.out.println(s);
        }

        System.out.println("\n\n====== TEST 3: Seller findAll ======");
        list = sellerDao.findAll();

        for (Seller s : list) {
            System.out.println(s);
        }
    }
}
