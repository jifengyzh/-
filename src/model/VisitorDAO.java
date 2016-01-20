package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.VisitorBean;

public class VisitorDAO extends GenericDAO<VisitorBean>{
	
	public VisitorDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(VisitorBean.class, tableName, cp);
	}

	public VisitorBean[] getAllCustomers() throws RollbackException {
		VisitorBean[] VisitorBeans = match();
		return VisitorBeans;
	}
	
	public VisitorBean readVisitor(String userName) throws RollbackException{
		VisitorBean[] visitor=match(MatchArg.equals("userName", userName));
		return visitor[0];
	}


	public VisitorBean[] readVisitor(int visitorId) throws RollbackException {
		VisitorBean[] customer = match(MatchArg.equals("visitorId",visitorId));
		return customer;
	}
	
	public void setPassword(int id, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	VisitorBean visitor = read(id);
			
			if (visitor == null) {
				throw new RollbackException("User "+ id +" does not exists");
			}
			
			visitor.setPassword(password);
			
			update(visitor);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
