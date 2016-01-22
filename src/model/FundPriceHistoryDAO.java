package model;

import java.beans.BeanDescriptor;
import java.util.Date;
import java.util.HashMap;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.xml.internal.ws.policy.sourcemodel.attach.ExternalAttachmentsUnmarshaller;

import databean.FundPriceHistoryBean;

public class FundPriceHistoryDAO extends GenericDAO<FundPriceHistoryBean> {

	public FundPriceHistoryDAO(ConnectionPool connectionPool, String name)
			throws DAOException {
		super(FundPriceHistoryBean.class, name, connectionPool);
	}


	/**
	 * return the price of the fund on the last trading date
	 * @param fund_id
	 * @param date
	 * @return
	 * @throws RollbackException 
	 */
	public long getFundPrice(int fundId, Date date) throws RollbackException {
		FundPriceHistoryBean[] fundPriceHistoryBeans = match(MatchArg.equals("fundId", fundId), MatchArg.equals("priceDate", date)); 
		if (fundPriceHistoryBeans == null) { 
			return 0;
		}
		
		return fundPriceHistoryBeans[0].getPrice();
	}

	public void updateFundPrice(HashMap<Integer, Long> map, Date date) throws RollbackException {
		for (Integer i : map.keySet()) {
			FundPriceHistoryBean bean = new FundPriceHistoryBean();
			bean.setFundId(i);
			bean.setPrice(map.get(i));
			bean.setPriceDate(date);
			update(bean);
		}
	}
		
}
