package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoImplJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoImplJDBC(Connection conn){
		
		this.conn =conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {

		String sql = "SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.ID "
				+ "WHERE seller.Id = ?";
				
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				
				Department dep = instatiateDepartment(rs);				
				
				Seller sel = instantiateSeller(rs, dep);
				
				return sel;
							
			}
			return null;
			
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
			
		}finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBaseSalary(rs.getDouble("BaseSalary"));
		
		Timestamp tsmt = rs.getTimestamp("BirthDate");
		
		if(tsmt != null) {
			sel.setBirthDate(new Date(tsmt.getTime()));
		}
		
		sel.setDepartment(dep);
		return sel;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		
		Department dep = new Department();
		
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));

		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartement(Department department) {
		
		
		String sql = "SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.ID "
				+ "WHERE DepartmentId = ? "
				+ "ORDER BY Name";
				
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
		
			st = conn.prepareStatement(sql);
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> listSeller = new ArrayList<>();
			
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					
					dep = instatiateDepartment(rs);			
					
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				
				Seller sel = instantiateSeller(rs, dep);
				
				listSeller.add(sel);
				
			}

			return listSeller;
			
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
			
		}finally {
			
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
}
