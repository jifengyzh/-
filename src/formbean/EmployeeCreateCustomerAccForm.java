package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeCreateCustomerAccForm extends FormBean {
	private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String zip;
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public String getAddrLine2() {
		return addrLine2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}
	
	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName, "<>\"");
	}
	
	public void setPassword(String password) {
		this.password = trimAndConvert(password.trim(), "<>\"");
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = trimAndConvert(confirmPassword.trim(), "<>\"");
	}
	public void setFirstName(String firstName) {
		this.firstName = trimAndConvert(firstName,"<>\"");
	}
	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName,"<>\"");
	}
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = trimAndConvert(addrLine1,"<>\"");
	}
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = trimAndConvert(addrLine2,"<>\"");
	}
	public void setCity(String city) {
		this.city = trimAndConvert(city,"<>\"");
	}
	public void setState(String state) {
		this.state = trimAndConvert(state,"<>\"");
	}
	public void setZip(String zip) {
		this.zip = trimAndConvert(zip,"<>\"");
	}	
	
	
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (userName == null || userName.length() == 0) errors.add("Username is required");
		if (password == null || password.length() == 0) errors.add("Password is required");
	    if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm password is required");  
		if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        if (addrLine1 == null || addrLine1.length() == 0) errors.add("Address 1 is required");
        if (addrLine2 == null || addrLine2.length() == 0) errors.add("Address 2 is required");
        if (city == null || city.length() == 0) errors.add("City is required");
        
        if (errors.size()>0) return errors;
        
        
        if (userName.trim().length() > 30) {
        	errors.add("Username should be less then 30 characters");
        }
        if (firstName.trim().length() > 30) {
        	errors.add("First Name should be less then 30 characters");
        }
        if (lastName.trim().length() > 30) {
        	errors.add("Last Name should be less then 30 characters");
        }
        if (addrLine1.trim().length() > 30) {
        	errors.add("AddrLine1 should be less then 30 characters");
        }
        if (addrLine2.trim().length() > 30) {
        	errors.add("AddrLine2 should be less then 30 characters");
        }
        if (city.trim().length() > 30) {
        	errors.add("City should be less then 30 characters");
        }
        if (!password.equals(confirmPassword)) {
        	errors.add("Passwords do not match! Please re-enter");
        }
		return errors;
		
	}	
}
