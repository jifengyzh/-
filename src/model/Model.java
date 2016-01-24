package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import databean.EmployeeBean;
import databean.PositionBean;

public class Model {
	private VisitorDAO visitorDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private LastDateDAO lastDateDAO;
	
	public Model(ServletConfig config) throws Exception {
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
			
			if (employeeDAO.getAllEmployee().length == 0) {
				EmployeeBean employeeBean = new EmployeeBean();
				employeeBean.setFirstName("admin");
				employeeBean.setLastName("admin");
				employeeBean.setUserName("admin");
				employeeBean.setPassword("123");
				employeeDAO.create(employeeBean);
			}
			
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
