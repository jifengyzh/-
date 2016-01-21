package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeResetCustomerPasswordForm extends FormBean {
	private String userName;
	private String newPassword;
	private String confirmedPassword;
	
	public String getUserName() {
		return userName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setUserName(String userName) {
		this.userName = trimAndConvert(userName, "<>\"");
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}
	public void setConfirmedNewPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword.trim();
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		
		if (userName == null || userName.trim().length() == 0) {
			errors.add("UserName is required");
		}
		if (newPassword == null || newPassword.trim().length() == 0) {
			errors.add("New Password is required");
		}
		if (confirmedPassword == null || confirmedPassword.trim().length() == 0) {
			errors.add("Confirm New Password is required");
		}
		
		if (errors.size() > 0) {
			return errors;
		}
		
		if (!newPassword.equals(confirmedPassword)) {
			errors.add("Passwords do not match! Please re-enter");
		}
		return errors;
	}

}
