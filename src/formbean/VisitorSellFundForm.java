package formbean;

import org.mybeans.form.FormBean;

public class VisitorSellFundForm extends FormBean{
	
	private String name;
	private long shares;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getShares() {
		return shares;
	}

	public void setShares(long shares) {
		this.shares = shares;
	}
}
