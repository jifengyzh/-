package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transactionId")
public class TransactionBean {
	private int transactionId;
	private int customerId;
	private int fundId;
	private Date executeDate;
	private long shares;
	private long sharePrice;
	private int transactionType;
	private long amount;
	
	public int getTransactionId() {
		return transactionId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public Date getExecuteDate() {
		return executeDate;
	}
	public long getShares() {
		return shares;
	}
	public long getSharePrice() {
		return sharePrice;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public long getAmount() {
		return amount;
	}
	
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setExecuteDate(Date executeDate) {
		this.executeDate = executeDate;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public void setSharePrice(long sharePrice) {
		this.sharePrice = sharePrice;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
}



