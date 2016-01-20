package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.CustomerBean;
import databean.VisitorBean;
import formbean.EmployeeResetCustomerPasswordForm;
import formbean.ResetCustomerPwdForm;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.VisitorDAO;

public class EmployeeResetCustomerPasswordAction extends Action {
	private FormBeanFactory<EmployeeResetCustomerPasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeResetCustomerPasswordForm.class);

	private VisitorDAO visitorDAO;

	public EmployeeResetCustomerPasswordAction(Model model) {
		visitorDAO = model.getVisitorDAO();
	}

	@Override
	public String getName() {
		return Constants.employeeResetCustomerPasswordAction;
	}

	@Override
	public String perform(HttpServletRequest request) {

		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);


		try {
			EmployeeResetCustomerPasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return Constants.employeeResetCustomerPasswordJsp;
			}

			
			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeResetCustomerPasswordJsp;
			}
			
			synchronized (this) {
				VisitorBean visitor = visitorDAO.read(form.getUserName());
				if (visitor == null) {
					errors.add("Customer does not exist");
					return Constants.employeeResetCustomerPasswordJsp;
				}
				
				visitorDAO.setPassword(visitor.getVisitorId(), form.getNewPassword());
			}
			
			// Success
			request.setAttribute("message", "Customer's password updated Successfully!");
			return Constants.employeeConfirmJsp;
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
