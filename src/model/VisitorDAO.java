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
		VisitorBean[] a = match(MatchArg.equals("userName", userName));
		VisitorBean visitorBean;
		if (a.length == 0) {
			visitorBean = null;
		} else {
			visitorBean = a[0];
		}
		return visitorBean;
	}

	public VisitorBean readVisitor(int visitorId) throws RollbackException {
		VisitorBean[] a = match(MatchArg.equals("visitorId",visitorId));
		VisitorBean visitorBean;
		if (a.length == 0) {
			visitorBean = null;
		} else {
			visitorBean = a[0];
		}
		return visitorBean;
	}
	
	public void setPassword(long l, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	VisitorBean visitor = read(l);
			
			if (visitor == null) {
				throw new RollbackException("User "+ l +" does not exists");
			}
			
			visitor.setPassword(password);
			
			update(visitor);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
		
	public VisitorBean updateCash(int visitorId, long cash) throws RollbackException {
		// Calls GenericDAO's match() method.
        	try {
			Transaction.begin();
			
			VisitorBean[] a = match(MatchArg.equals("visitorId", visitorId));
			VisitorBean visitorBean;
			if (a.length == 0) {
				visitorBean = null;
			} else {
				visitorBean = a[0];
				visitorBean.setCash(visitorBean.getCash() + cash);
				update(visitorBean);
			}
    			Transaction.commit();
    			return visitorBean;
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
