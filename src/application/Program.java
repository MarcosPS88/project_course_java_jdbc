package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.impl.DepartmentDaoImplJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDaoImplJDBC departmentDao = DaoFactory.createDepartmentDao();
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
		
		
	}

}
