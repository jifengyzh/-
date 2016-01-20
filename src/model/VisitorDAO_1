package Model;

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

	public VisitorBean[] readVisitor(String userName) throws RollbackException{
		VisitorBean[] customer=match(MatchArg.equals("userName", userName));
		return customer;
	}


	public VisitorBean[] readVisitor(int visitorId) throws RollbackException {
		VisitorBean[] customer = match(MatchArg.equals("visitorId",visitorId));
		return customer;
	}
	
	public void setPassword(int id, String password) throws RollbackException {
        try {
        	Transaction.begin();
        	VisitorBean Visitor = read(id);
			
			if (Visitor == null) {
				throw new RollbackException("User "+ id +" does not exists");
			}
			
			Visitor.setPassword(password);
			
			update(Visitor);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
}
