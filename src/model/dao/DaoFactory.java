package model.dao;

import db.DB;
import model.dao.impl.SellerDaoImplJDBC;

public class DaoFactory {

	public static SellerDaoImplJDBC createSellerDao() {
		return new SellerDaoImplJDBC(DB.getConnection());
	}
}
