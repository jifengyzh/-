package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import Model.CustomerDAO;
import Model.Model;
import databean.CustomerBean;
import formbean.ChangePasswordForm;

public class VisitorPwdChangeAction extends Action{



	private FormBeanFactory<ChangePasswordForm> formBeanFactory = 
			FormBeanFactory<FormBean>.getInstance(ChangePasswordForm.class);
	
	private CustomerDAO customerDAO;


	public VisitorPwdChangeAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "visitor_changepwd.do";
	}


	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			ChangePasswordForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
				return "visitor_changepwd.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "visitor_changepwd.jsp";
			}
			
			synchronized(this) {
				CustomerBean customer = customerDAO.read((Integer) session.getAttribute("customerId"));
				
				if(!customer.checkPassword(form.getOldPassword())){
					errors.add("Incorrect Password!! Please re-enter your current password.");
					return "visitor_changepwd.jsp";
				}
				
				customerDAO.changePassword(customer.getCustomerId(), form.getNewPassword());
				
				
			}
			request.setAttribute("message", "Password changed!!!!");
			return "visitor-confirmation.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
