package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import formbean.EmployeeCreateNewEmployeeAccForm;


public class EmployeeCreateNewEmployeeAccAction extends Action{

	private FormBeanFactory<EmployeeCreateNewEmployeeAccForm> createEmployeeAccountFormFactory = FormBeanFactory.getInstance(EmployeeCreateNewEmployeeAccForm.class);
	private EmployeeDAO employeeDAO;
	
	public EmployeeCreateNewEmployeeAccAction(Model model){
		employeeDAO = model.getAdminDAO();
	}
	
	@Override
	public String getName() {
		return "create-employee-account.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	EmployeeCreateNewEmployeeAccForm form = createEmployeeAccountFormFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return "create-account-employee.jsp";
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "create-account-employee.jsp";
	        }
	
	        
	        synchronized (this) {
	        	EmployeeBean employee = employeeDAO.lookup(form.getUserName());
		       	if (employee != null) {
		       		errors.add("Existing Username");
	                return "create-account-employee.jsp";
		       	}
		       	
		       	employee = new EmployeeBean();
		       	employee.setUserName(form.getUserName());
		       	employee.setPassword(form.getPassword());
		       	employee.setFirstName(form.getFirstName());
		       	employee.setLastName(form.getLastName());
		       	employeeDAO.create(employee);
	        }
        
	        request.setAttribute("message","Account successfully created for " + "<b>" + form.getUserName() + "</b>");
	       	
			return "employee-confirmation.jsp";

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (Exception e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}
