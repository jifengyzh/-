package model;

public class Model {
	private VisitorDAO visitorDAO;
	private EmployeeDAO adminDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private LastDateDAO lastDateDAO;
	
	public VisitorDAO getVisitorDAO() { return visitorDAO;}
	public EmployeeDAO getEmployeeDAO() {return adminDAO;}
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceHistoryDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
	public LastDateDAO getLastDateDAO() { return lastDateDAO;}

	
}
