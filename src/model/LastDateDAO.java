package model;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.LastDateBean;

public class LastDateDAO extends GenericDAO<LastDateBean> {

	public LastDateDAO(ConnectionPool connectionPool, String tableName)
			throws DAOException {
		super(LastDateBean.class, tableName, connectionPool);
		// TODO Auto-generated constructor stub
	}
	public Date getLastDate() throws RollbackException {
		LastDateBean lastdate= read("lastday");
		if (lastdate == null) return null;
		return lastdate.getDate();
	}
	
	public void setLastDate(Date date) {
		try {
			Transaction.begin();
			LastDateBean lastdate = read("lastday");
			if (lastdate == null) {
				lastdate = new LastDateBean();
				lastdate.setName("lastday");
				lastdate.setDate(date);
				create(lastdate);
			} else {
				lastdate.setDate(date);
				update(lastdate);
			}
			Transaction.commit();
		} catch (RollbackException e) {
			
		}
		finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
		
	}

}
