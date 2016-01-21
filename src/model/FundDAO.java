package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.FundBean;

public class FundDAO extends GenericDAO<FundBean>{

	public FundDAO(Class<FundBean> beanClass, String tableName, ConnectionPool connectionPool) throws DAOException {
		super(beanClass, tableName, connectionPool);
		// TODO Auto-generated constructor stub
	}
	
	public String getFundName(int fund_id) throws RollbackException {
		FundBean fund;
		try {
			fund = read(fund_id);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return fund.getName();
	}
	
	public String getFundSymbol(int fund_id) throws RollbackException {
		FundBean fund;
		try {
			fund = read(fund_id);
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		return fund.getSymbol();
	}
	
	public FundBean[] getAllFund() throws RollbackException {
		FundBean[] allFunds = match();
		return allFunds;
	}
}
