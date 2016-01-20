package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import databean.FundBean;

public class FundDAO extends GenericDAO<FundBean>{

	public FundDAO(Class<FundBean> beanClass, String tableName, ConnectionPool connectionPool) throws DAOException {
		super(beanClass, tableName, connectionPool);
		// TODO Auto-generated constructor stub
	}
	
}
