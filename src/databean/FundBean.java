package databean;

import org.genericdao.PrimaryKey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@PrimaryKey("fundId")
public class FundBean {
	private int fundId;
	private String name;
	private String symbol;
	
	public int getFundId()					{ return fundId;		}
	public String getName()					{ return name;			}
	public String getSymbol()				{ return symbol;		}
	
	public void setFundId(int fundId)		{ this.fundId = fundId;	}
	public void setName(String name)		{ this.name = name;		}
	public void setSymbol(String symbol)	{ this.symbol = symbol;	}
}
