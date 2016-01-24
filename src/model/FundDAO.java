package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBean;

import databean.FundBean;

public class FundDAO extends GenericDAO<FundBean>{

	public FundDAO(ConnectionPool connectionPool, String tableName) throws DAOException {
		super(FundBean.class, tableName, connectionPool);
	}
	
	public FundBean readFundName(String fundName) throws RollbackException {
		FundBean[] bean = match(MatchArg.equals("name", fundName));
		if (bean == null || bean.length ==0 ) {
			return null;
		}
		return bean[0];
	}
	
	public FundBean readFundSymbol(String symbol) throws RollbackException {
		FundBean[] beans = match(MatchArg.equals("symbol", symbol));
		if (beans == null || beans.length ==0) {
			return null;
		}
		return beans[0];
	}
	
	
	public FundBean[] getAllFund() throws RollbackException {
		FundBean[] allFunds = match();
		return allFunds;
	}
}
