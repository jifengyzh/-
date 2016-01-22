/**
 * @author Team 7
 */

package databean;

import org.genericdao.PrimaryKey;

/**
 * 
 * @author MikeYang
 *
 */
@PrimaryKey("visitorId")
public class PositionBean {
	private int visitorId;
	private int fundId;
	private long shares;
	private long availableShares;
	
	public int getVisitorId() {
		return visitorId;
	}
	public int getFundId() {
		return fundId;
	}
	public long getShares() {
		return shares;
	}
	public long getAvailableShares() {
		return availableShares;
	}
	
	public void setVisitorId(int customerId) {
		this.visitorId = customerId;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	public void setAvailableShares(long availableShares) {
		this.availableShares = availableShares;
	}
	
}
