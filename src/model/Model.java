package model;

public class Model {
	private VisitorDAO visitorDAO;
	private EmployeeDAO adminDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	
	
	public VisitorDAO getVisitorDAO() { return visitorDAO;}
	public EmployeeDAO getAdminDAO() {return adminDAO;}
	public FundDAO getFundDAO() {return fundDAO;}
	public FundPriceHistoryDAO getFundPriceHistoryDAO() {return fundPriceHistoryDAO;}
	public TransactionDAO getTransactionDAO() {return transactionDAO;}
	public PositionDAO getPositionDAO() {return positionDAO;}
	
}
