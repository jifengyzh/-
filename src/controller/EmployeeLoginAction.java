package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.LastDateDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.EmployeeBean;

import formbean.LoginForm;

public class EmployeeLoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private EmployeeDAO employeeDAO;
	private LastDateDAO lastDateDAO;
	public EmployeeLoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return Constants.employeeLoginAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// If employee is already logged in, redirect to employee-mainpanel.jsp
		if (session.getAttribute("employeeUserName") != null) {
			return Constants.employeeMainPanelJsp;
		}

		// If customer is already logged in, redirect to customer-mainpanel.jsp
		if (session.getAttribute("customerId") != null) {
			return Constants.visitorViewAccountAction;
		}

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return Constants.mainPage;
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.mainPage;
			}

			EmployeeBean employee = employeeDAO.read(form.getUserName());
			
			if (employee == null) {
	            errors.add("Incorrect/Invalid Employee Username");
	            return Constants.mainPage;
	        }
	        
	        if (!employee.checkPassword(form.getPassword())) {
	            errors.add("Incorrect/Invalid Password");
	            return Constants.mainPage;
	        }

			// Attach (this copy of) the user bean to the session
			session.setAttribute("employeeUserName", employee.getUserName());
			session.setAttribute("firstname", employee.getFirstName());
			session.setAttribute("lastname", employee.getLastName());
			session.setAttribute("lastdate", lastDateDAO.getLastDate());

			// If redirectTo is null, redirect to the "todolist" action
			return Constants.employeeMainPanelJsp;

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
		}
	}
}
