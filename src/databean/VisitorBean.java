package databean;

import org.genericdao.PrimaryKey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

@PrimaryKey("visitorId")
public class VisitorBean {
	private int      visitorId;
	private String    userName;
	private String    password;
	private String    firstName;
	private String    lastName;
	private String    addrLine1;
	private String    addrLine2;
	private String    city;
	private String    state;
	private String    zip;
	private long      cash;
	private Date lastTradeDate;
	private long      availableCash;
	private int 		salt = 0;
	private String  hashedPassword = "*";
	
	public int      getVisitorId()      { return visitorId;      }
	public String    getUserName()        { return userName;        }
	public String    getPassword()        { return password;        }
	public String    getFirstName()       { return firstName;       }
	public String    getLastName()        { return lastName;        }
	public String    getAddrLine1()       { return addrLine1;       }
	public String    getAddrLine2()       { return addrLine2;       }
	public String    getCity()            { return city;            }
	public String    getState()           { return state;           }
	public String    getZip()             { return zip;             }
	public long      getCash()            { return cash;            }
	public Date getLastTradeDate() {return lastTradeDate;}
	public long      getAvailableCash()   { return availableCash;   }
	public int  getSalt()           { return salt;           }
	public String getHashedPassword() { return hashedPassword; }
	
	
	public int     hashCode()          { return userName.hashCode(); }	
	
	public void   setVisitorId(int l)       { visitorId = l;       }
	public void   setUserName(String s)       { userName = s;         }
	public void setPassword(String s)        { salt = newSalt(); hashedPassword = hash(s); }
	public void   setFirstName(String s)      { firstName = s;        }
	public void   setLastName(String s)       { lastName = s;         }
	public void   setAddrLine1(String s)      { addrLine1 = s;        }
	public void   setAddrLine2(String s)      { addrLine2 = s;        }
	public void   setCity(String s)           { city = s;             }
	public void   setState(String s)          { state = s;            }
	public void   setZip(String s)            { zip = s;              }
	public void   setCash(long l)             { cash = l;             }
	public void setLastTradeDate(Date d) {  lastTradeDate = d;}
	public void   setAvailableCash(long l)    { availableCash = l;    }
	public void setSalt(int x) 				{salt = x;}
	public void setHashedPassword(String s) {hashedPassword = s;}
 	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	private String hash(String clearPassword) {
		if (salt == 0) return null;
		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}
		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();
		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();
		return digestStr;
	}
	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero
	}
}
