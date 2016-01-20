package databean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.genericdao.PrimaryKey;

@PrimaryKey("userName")
public class EmployeeBean {

	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	
	public String getUserName()			{ return userName;		}
	public String getPassWord()			{ return passWord;		}
	public String getFirstName()			{ return firstName;		}
	public String getLastName()			{ return lastName;		}
	
	public void setUserName(String s)		{ this.userName	= s;	}
	public void setPassWord(String s)		{ this.passWord = s;	}
	public void setFirstName(String s)		{ this.firstName = s;	}
	public void setLastName(String s)		{ this.lastName = s;	}
	
}	
	
