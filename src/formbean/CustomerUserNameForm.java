package formbean;


import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class CustomerUserNameForm extends FormBean {
	private String username;

	public String getUsername() {
		return username;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("UserName is required");
			return errors;
		}
		return errors;
	}
}

