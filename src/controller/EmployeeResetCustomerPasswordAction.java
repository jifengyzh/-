/**
 * @author Arwen
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.VisitorBean;
import formbean.EmployeeResetCustomerPasswordForm;
import model.Model;
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
		return Constants.employeeResetCustPwdAction;
	}

	@Override
	public String perform(HttpServletRequest request) {

		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("success", null);

		try {
			EmployeeResetCustomerPasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return Constants.employeeResetCustPwdJsp;
			}

			
			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeResetCustPwdJsp;
			}
			
			synchronized (this) {
				VisitorBean visitor = visitorDAO.read(form.getUserName());
				if (visitor == null) {
					errors.add("Customer does not exist");
					return Constants.employeeResetCustPwdJsp;
				}
				visitorDAO.setPassword(visitor.getVisitorId(), form.getNewPassword());
			}
			
			// Success
			request.setAttribute("success","Customer's password has been reset.");
			return Constants.employeeResetCustPwdJsp;
		} catch (RollbackException e) {
			errors.add(e.toString());
			return Constants.employeeResetCustPwdJsp;
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return Constants.employeeResetCustPwdJsp;
		}
	}
}
