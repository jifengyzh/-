package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import databean.PositionBean;

public class Model {
	private VisitorDAO visitorDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private LastDateDAO lastDateDAO;
	
	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL    = config.getInitParameter("jdbcURL");
			
			ConnectionPool pool = new ConnectionPool(jdbcDriver,jdbcURL);
			
			visitorDAO = new VisitorDAO(pool, "visitor");
			employeeDAO = new EmployeeDAO(pool, "employee");
			fundDAO = new FundDAO(pool, "fund");
			fundPriceHistoryDAO = new FundPriceHistoryDAO( pool, "fundPriceHistory");
			lastDateDAO = new LastDateDAO(pool, "lastDay");
			positionDAO = new PositionDAO(PositionBean.class, "position", pool);
			transactionDAO = new TransactionDAO(pool, "transaction");
			
		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}
	
	public VisitorDAO getVisitorDAO() { return visitorDAO;}
	public EmployeeDAO getEmployeeDAO() {return employeeDAO;}
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceHistoryDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
	public LastDateDAO getLastDateDAO() { return lastDateDAO;}

	
}
