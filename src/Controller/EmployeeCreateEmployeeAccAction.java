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
import formbean.EmployeeCreateNewEmployeeAccForm;


public class EmployeeCreateEmployeeAccAction extends Action{

	private FormBeanFactory<EmployeeCreateNewEmployeeAccForm> createEmployeeAccountFormFactory = FormBeanFactory.getInstance(EmployeeCreateNewEmployeeAccForm.class);
	private EmployeeDAO employeeDAO;
	
	public EmployeeCreateEmployeeAccAction(Model model){
		employeeDAO = model.getAdminDAO();
	}
	
	@Override
	public String getName() {
		return Constants.employeeCreateEmployeeAccAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	EmployeeCreateNewEmployeeAccForm form = createEmployeeAccountFormFactory.create(request);
	        request.setAttribute("form",form);

	        if (!form.isPresent()) {
	            return Constants.employeeCreateEmployeeAccJsp;
	        }
	
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return Constants.employeeCreateEmployeeAccJsp;
	        }
	
	        
	        synchronized (this) {
	        	EmployeeBean employee = employeeDAO.lookup(form.getUserName());
		       	if (employee != null) {
		       		errors.add("Existing Username");
	                return Constants.employeeCreateEmployeeAccJsp;
		       	}
		       	
		       	employee = new EmployeeBean();
		       	employee.setUserName(form.getUserName());
		       	employee.setPassword(form.getPassword());
		       	employee.setFirstName(form.getFirstName());
		       	employee.setLastName(form.getLastName());
		       	employeeDAO.create(employee);
	        }
        
	        request.setAttribute("message","Account successfully created for " + "<b>" + form.getUserName() + "</b>");
	       	
			return Constants.employeeConfirmJsp;

        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}
