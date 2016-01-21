package model;


import java.beans.beancontext.BeanContextMembershipEvent;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.PrimaryKey;
import org.genericdao.RollbackException;

import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;

public class PositionDAO extends GenericDAO<PositionBean> {
	public PositionDAO(Class<PositionBean> beanClass, String tableName, ConnectionPool connectionPool)
			throws DAOException {
		super(PositionBean.class, tableName, connectionPool);
		// TODO Auto-generated constructor stub
	}

	public PositionBean[] getPositionList(int customerId) throws RollbackException {
		PositionBean[] positionBeans = match(MatchArg.equals("customerId", customerId));
		
		return positionBeans;
	}
	
	/**
	 * before customer makes sellfund order, get available shares to check validity 
	 * @param sellFundTransaction
	 * @return the availableShares of the customer
	 * @throws RollbackException
	 */
	public long getAvailableShares(TransactionBean sellFundTransaction) throws RollbackException {
		PositionBean[] positionBeans = match(MatchArg.equals("visitorId", sellFundTransaction.getCustomerId()), MatchArg.equals("fundId", sellFundTransaction.getFundId()));
		if (positionBeans == null) {
			return 0;
		}
		return positionBeans[0].getAvailableShares();
	}
	
	/**
	 * everytime when sellFundTransaction is made to be pending transaction, available shares should be updated
	 * @param sellFundTransaction
	 * @throws RollbackException
	 */
	public void updateAvailableShares(TransactionBean sellFundTransaction) throws RollbackException {
		PositionBean[] positionBeans = match(MatchArg.equals("customerId", sellFundTransaction.getCustomerId()), MatchArg.equals("fundId", sellFundTransaction.getFundId()));
		long newAvailableShares = positionBeans[0].getAvailableShares() - sellFundTransaction.getAmount();
		if (newAvailableShares == 0) {
			delete(sellFundTransaction.getFundId());
		}
		update(positionBeans[0]);
	}
	
	/**
	 * update position after execute day
	 * @param tbeans
	 * @throws RollbackException
	 */
	public void updatePositions(TransactionBean[] tbeans) throws RollbackException {
		for (TransactionBean bean: tbeans) {
			PositionBean positionBean = read(bean.getCustomerId());
			
			if (positionBean == null) { 
				positionBean = new PositionBean();
				positionBean.setCustomerId(bean.getCustomerId());
				positionBean.setFundId(bean.getFundId());
			}
			
			//positionBean.setFundName;
			
			// for those buy fund transactions, update the shares in position by adding shares bought
			if (bean.getTransactionType() == 1) {
				positionBean.setShares(positionBean.getShares() + bean.getShares());
				update(positionBean);
			}
			
			// for those sell fund transactions, update the shares in position by deducting shares sold
			if (bean.getTransactionType() == 2) {
				positionBean.setShares(positionBean.getShares() - bean.getShares());
				update(positionBean);
			}	
			
			
		}
	}
	
	public String getFundName(int FundId) {
		//FundDAO fundDAO = new FundDAO(FundBean.class, "fund", connectionPool)
		String fundName = null;
		return fundName;
	}
}
