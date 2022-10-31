package application;

import java.util.List;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department dep = departmentDao.findById(13);
		
		System.out.println("=====Test 1 department find by Id======");
		System.out.println(dep);
		
		System.out.println("=====Test 2 department find all======");
		List<Department> listDep = departmentDao.findAll();
		
		listDep.stream().forEach(System.out::println);
		
		System.out.println("=====Test 3 department insert======");
		
		Department newDep =  new Department(null, "Food");
		
		departmentDao.insert(newDep);
		
		System.out.println("ID addeded department is: " + newDep.getId());
		
		System.out.println("=====Test 4 department update======");
		
		dep.setName("Beauty");
		
		departmentDao.update(dep);
		
		System.out.println("=====Test 5 department delete by id======");
		
		departmentDao.deleteById(16);	
		departmentDao.deleteById(15);	
		departmentDao.deleteById(14);	
		departmentDao.deleteById(13);	
		departmentDao.deleteById(12);	
		
		
		
		DB.getConnection();
	}

}
