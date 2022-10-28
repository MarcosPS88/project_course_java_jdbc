package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department obj = new Department("Books");
		
		Seller seller = new Seller("Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		
		System.out.println(obj);
		System.out.println(seller);
		
	}

}