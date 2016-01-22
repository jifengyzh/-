package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeCreateNewEmployeeAccForm extends FormBean {
  private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	
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
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (userName == null || userName.length() == 0) errors.add("Username is required");
		if (password == null || password.length() == 0) errors.add("Password is required");
	    if (confirmPassword == null || confirmPassword.length() == 0) errors.add("Confirm password is required");  
		if (firstName == null || firstName.length() == 0) errors.add("First name is required");
        if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
        
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
        if(!password.equals(confirmPassword)) {
        	errors.add("Passwords do not match! Please re-enter");
        }
		return errors;
		
	}
}
