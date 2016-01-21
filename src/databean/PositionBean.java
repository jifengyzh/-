/**
 * @author Team 7
 */

package databean;

/**
 * 
 * @author MikeYang
 *
 */
public class PositionBean {
	private int customerId;
	private int fundId;
	private String fundName;
	private String fundSymbol;
	private long shares;
	private long availableShares;
	
	public int getCustomerId() {
		return customerId;
	}
	public int getFundId() {
		return fundId;
	}
	public String getFundName() {
		return fundName;
	}
	public String getFundSymbol() {
		return fundSymbol;
	}
	public long getShares() {
		return shares;
	}
	public long getAvailableShares() {
		return availableShares;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = fundSymbol;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}
	
}
