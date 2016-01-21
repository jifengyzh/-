package model;

public class Model {
	private VisitorDAO visitorDAO;
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private TransactionHistoryDAO transactionHistoryDAO;
	
	
	public VisitorDAO getVisitorDAO() { return visitorDAO;}
	public EmployeeDAO getEmployeeDAO() {return employeeDAO;}
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceHistoryDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
	public TransactionHistoryDAO getTransactionHistoryDAO() {return transactionHistoryDAO;}
	
}
