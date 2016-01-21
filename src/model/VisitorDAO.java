package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.TransactionBean;
import databean.VisitorBean;

public class VisitorDAO extends GenericDAO<VisitorBean>{
	
	public VisitorDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(VisitorBean.class, tableName, cp);
	}

	public VisitorBean[] getAllCustomers() throws RollbackException {
		VisitorBean[] VisitorBeans = match();
		return VisitorBeans;
	}

	public VisitorBean read(String userName) throws RollbackException{
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
	
	/**
	 * when costumer wants to buy fund or request check, get the available cash to check validity
	 * @param visitorId
	 * @return
	 * @throws RollbackException
	 */
	public long getAvailableCash(int visitorId) throws RollbackException {
		VisitorBean[] vBeans = match(MatchArg.equals("visitorId", visitorId));
		return vBeans[0].getAvailableCash();
	}
	
	/**
	 * when costumer buys fund or request check, his available cash needs to be updated
	 * @param transactionBean
	 * @throws RollbackException 
	 */
	public void updateAvailableCash(TransactionBean transactionBean) throws RollbackException {
		VisitorBean bean = read(transactionBean.getVisitorId());
		bean.setAvailableCash(bean.getAvailableCash() - transactionBean.getAmount());
		update(bean);
	}
	
	public void updateVisitor(TransactionBean[] transactionBeans) throws RollbackException {
		for (TransactionBean bean : transactionBeans) {
			VisitorBean visitorBean = read(bean.getVisitorId());
			long cash =visitorBean.getCash();
			long availableCash = visitorBean.getAvailableCash();
			long amount = bean.getAmount();
			//for buy fund transaction cash ++
			if(bean.getTransactionId() == 1) {
				visitorBean.setCash(cash - amount );
			}
			//for sell fund transaction cash-- available cash --
			if (bean.getTransactionType() == 2) {
				visitorBean.setAvailableCash(availableCash - amount);;
				visitorBean.setCash(cash - amount);
			}
			//for request check transaction cash--
			if (bean.getTransactionId() == 3) {
				visitorBean.setCash(cash - amount);
			}
			//for deposit check transaction cash ++ available cash ++
			if (bean.getTransactionType() == 4) {
				visitorBean.setAvailableCash(availableCash + amount);
				visitorBean.setCash(cash + amount);
			}
			
			update(visitorBean);
				
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
