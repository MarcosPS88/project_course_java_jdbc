package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> list = new ArrayList<>();
		
		System.out.println("===TEST 1: seller findbyid ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
		System.out.println("===TEST 2: seller findByDepartment ===");
		Department dep = new Department (2, null);
		list = sellerDao.findByDepartement(dep);
		
		for(Seller s: list){
		System.out.println(s);
		}
		
		System.out.println("===TEST 3: seller findAll ===");
		list = sellerDao.findAll();
		for(Seller s: list){
			System.out.println(s);
		}
		
		System.out.println("===TEST 4: seller insert ===");
		Seller newseller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep );
		
		sellerDao.insert(newseller);
		System.out.println("Inserted! New Id = " + newseller.getId());
		
	}

}
