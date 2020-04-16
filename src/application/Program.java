package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

        System.out.println("\n\n====== TEST 4: Seller insert ======");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted new id = " + newSeller.getId());

        System.out.println("\n\n====== TEST 5: Seller update ======");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);

        System.out.println("\n\n====== TEST 6: Seller delete ======");
        System.out.println("Enter id for delete test: ");
        int id = new Scanner(System.in).nextInt();
        
    }
}
