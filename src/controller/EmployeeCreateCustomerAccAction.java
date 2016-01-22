package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import formbean.EmployeeCreateCustomerAccForm;
import model.VisitorDAO;
import model.Model;
import databean.VisitorBean;

public class EmployeeCreateCustomerAccAction extends Action{
	private FormBeanFactory<EmployeeCreateCustomerAccForm> createAccFormFactory = FormBeanFactory.getInstance(EmployeeCreateCustomerAccForm.class);
	private VisitorDAO visitorDAO;
	
	public EmployeeCreateCustomerAccAction(Model model) {
		visitorDAO = model.getVisitorDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.employeeCreateCustomerAccAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("success", null);
		
		try {
			EmployeeCreateCustomerAccForm form = createAccFormFactory.create(request);
			request.setAttribute("form", form);
			if (!form.isPresent()) return Constants.employeeCreateCustomerAccJsp;
			errors.addAll(form.getValidationErrors());
			if (!errors.isEmpty()) return Constants.employeeCreateCustomerAccJsp;
			
			VisitorBean customer = visitorDAO.read(form.getUserName());
			if  (customer != null) {
				errors.add("User already exists");
				return Constants.employeeCreateCustomerAccJsp;
			}
			
			synchronized(this) {
				customer = new VisitorBean();
				customer.setUserName(form.getUserName());
				customer.setFirstName(form.getFirstName());
				customer.setLastName(form.getLastName());
		       	customer.setPassword(form.getPassword());
		       	customer.setAddrLine1(form.getAddrLine1());
		       	customer.setAddrLine2(form.getAddrLine2());
		       	customer.setCity(form.getCity());
		       	customer.setState(form.getState());
		       	customer.setZip(form.getZip());
		       	visitorDAO.create(customer);
			}
			
			request.setAttribute("success","Create customer success!");
			//need to wait for confirmation?
			return Constants.employeeCreateCustomerAccJsp;
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.employeeCreateCustomerAccJsp;
		}catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.employeeCreateCustomerAccJsp;
			
		}
	}
}
