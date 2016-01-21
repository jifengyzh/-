package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeChangePasswordForm extends FormBean {
//need confirmation!
	private String oldPassword;
	private String newPassword;
	private String confirmedPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public String getConfirmedPassword() {
		return confirmedPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword.trim();
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword.trim();
	}
	public void setConfirmedNewPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword.trim();
	}
	
	public List<String> getValidationErrors(){
		List<String> errors = new ArrayList<String>();
		
		if (oldPassword == null || oldPassword.trim().length() == 0) {
			errors.add("Current Password is Required");
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
		
		if (oldPassword.equals(newPassword)) {
			errors.add("New password cannot be as same as the current password");
		}
		
		
		if (!newPassword.equals(confirmedPassword)) {
			errors.add("Passwords do not match! Please re-enter");
		}
		return errors;
	}
}
