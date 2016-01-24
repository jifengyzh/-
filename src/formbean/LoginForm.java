package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String userName;
	private String password;
	private String button;

	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getButton() { 
		return button;       
	}
	public void setUserName(String s) {
		userName = s;
	}

	public void setPassword(String s) {
		password = s;
	}

	public void setAction(String s) {
		button = s;
	}

		
	    public List<String> getValidationErrors() {
	        List<String> errors = new ArrayList<String>();

	        if (userName == null || userName.length() == 0) {
	        	errors.add("UserName is required"); 
	        }
	        if (password == null || password.length() == 0) {
	        	errors.add("Password is required"); 
	        }
//	        if (button == null) {
//	        	errors.add("Button is required");
//	        }

	        if (errors.size() > 0) {
	        	return errors;
	        }
	        return errors;
	    }
}


