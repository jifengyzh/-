/**
 * 
 */
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

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("success", null);

		try {
			EmployeeChangePasswordForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return Constants.employeeChangePwdJsp;
			}

			// Check for any validation errors
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeChangePwdJsp;
			}

			synchronized (this) {
				EmployeeBean employee = employeeDAO.read((String) session.getAttribute("employeeUserName"));

				if (!employee.checkPassword(form.getOldPassword())) {
					errors.add("Incorrect Password!! Please re-enter your current password");
					return Constants.employeeChangePwdJsp;
				}

				// change password
				employeeDAO.setPassword(employee.getUserName(), form.getNewPassword());
			}

			// success
			request.setAttribute("message", "Password changed successfully!");
			return Constants.employeeChangePwdJsp;
		} catch (RollbackException e) {
			errors.add(e.toString());
			return Constants.employeeChangePwdJsp;
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return Constants.employeeChangePwdJsp;
		}
	}

}
