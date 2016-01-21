package databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("fundId, priceDate")
public class FundPriceHistoryBean {
	private int fundId;
	private Date priceDate;
	private long price;
	
	public int getFundid() {
		return fundId;
	}
	public Date getPricedate() {
		return priceDate;
	}
	public long getPrice() {
		return price;
	}
	public void setFundId(int fundId) {
		this.fundId = fundId;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	
}
