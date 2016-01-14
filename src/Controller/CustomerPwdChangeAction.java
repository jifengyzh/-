package Controller;

import org.mybeans.form.FormBeanFactory;

public class CustomerPwdChangeAction {
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = 
			FormBeanFactory<FormBean>.getInstance(ChangePasswordForm.class);
	
	private CustomerDAO customerDAO;
}
