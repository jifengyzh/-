package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;



public class TransactionDAO extends BaseDAO{
	public TransactionDAO(String jdbcDriver, String jdbcURL, String tableName)
			throws MyDAOException {
		super(jdbcDriver, jdbcURL, tableName);
	}
	
	public boolean isRequestCheckValid(int customerId, long amount) {
		// check available balance
		
		
		return true;
	}
	
	public void requestCheck(int customerId, int transactionType, long amount) {
		
	}
	
	public void depositCheck(int customerId, long amount) {
		
	}
	
	/**
	 * 
	 * @return a boolean if buy order is valid to make
	 */
	public boolean isBuyOrderValid(int customerId, int fundId, long amount) {
		// check available cash customer has 
		// this data is from the current balance the customer has

		
		return true;
	}
	
	public void buyFund(int customerId, int fundId, long amount) {
		// some data will be stored in the transaction table
		
		// the balance of customer will be updated
	}
	

	
	public boolean isSellOrderValid(int customerId, int fundId, long shares) {
		boolean flag;
		//check the shares of this fund customer has 
		// this data is stored in position table 
		return true;
	}
	
	
	public void sellFund(int customerId, int fundID, long shares) {
		// these data will be stored in transaction table
		
		// the shares costumer has will be updated
	}
	
	/**
	 * 
	 * @param fundId
	 * @param executeDate
	 * @param price
	 */
	public void makeTransaction(int fundId, Date executeDate, long price) {
		//retrieve all buy orders information and update executeDate and shares
		
		//retrieve all sell orders information and update executeDate and amount
		
		
	}
	
	
	
	protected void createTable() throws MyDAOException {
		Connection con = getConnection();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("CREATE TABLE "
					+ tableName
					+ " (transactionId INT NOT NULL AUTO_INCREMENT, customerId INT NOT NULL, fundId INT, "
					+ " executeDate DATE, shares BIGINT(64) UNSIGNED, sharePrice BIGINT(64) UNSIGNED, transactionType INT(1) NOT NULL,amount BIGINT(64) UNSIGNED,"
					+ " PRIMARY KEY(transactionId), FOREIGN KEY (customerId) REFERENCES Customer (customerId))");
			stmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e2) { /* ignore */
			}
			throw new MyDAOException(e);
		}
	}
}
