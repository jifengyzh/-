package formbean;

import org.mybeans.form.FormBean;

public class VisitorChangePwdForm extends FormBean{
	private String oldPassword;
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
