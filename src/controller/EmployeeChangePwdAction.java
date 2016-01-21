package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.EmployeeBean;

import formbean.EmployeeChangePasswordForm;

public class EmployeeChangePwdAction extends Action {
	private FormBeanFactory<EmployeeChangePasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeChangePasswordForm.class);

	private EmployeeDAO employeeDAO;

	public EmployeeChangePwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return Constants.employeeChangePwdAction;
	}

	@Override
	public synchronized String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("success", null);

		try {
			EmployeeChangePasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return Constants.employeeChangePwdJsp;
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeChangePwdJsp;
			}
			
			synchronized(this){
				EmployeeBean employee = employeeDAO.read((String) session
						.getAttribute("employeeUserName"));

				// check old password
				if (!employee.checkPassword(form.getOldPassword())) {
					errors.add("Incorrect Password!! Please re-enter your current password");
					return Constants.employeeChangePwdJsp;
				}
				
				// change password
				employeeDAO.changePassword(employee.getUserName(), form.getNewPassword());
			}

			// success
			request.setAttribute("message", "Password changed successfully!");
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
