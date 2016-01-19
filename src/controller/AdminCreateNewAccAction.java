package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import FormBean.AdminCreateNewAccForm;
import FormBean.CreateNewAccForm;
import databean.VisitorBean;
import model.Model;
import model.VisitorDAO;

public class AdminCreateNewAccAction extends Action{
	private FormBeanFactory<AdminCreateNewAccForm> createAccFormFactory = FormBeanFactory.getInstance(AdminCreateNewAccForm.class);
	private VisitorDAO visitorDAO;
	
	public AdminCreateNewAccAction(Model model) {
		visitorDAO = model.getVisitorDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.adminNewAccAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			AdminCreateNewAccForm form = createAccFormFactory.create(request);
			if (!form.isPresent()) return Constants.adminNewAccJsp;
			errors.addAll(form.getValidationErrors());
			if (!errors.isEmpty()) return Constants.adminNewAccJsp;
			
			VisitorBean customer = visitorDAO.read(form.getUserName());
			if  (customer != null) errors.add("User already exists");
			return Constants.adminNewAccJsp;
			
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
		       	customer.setZip(form.getZipAsInt());
		       	visitorDAO.create(customer);
			}
			
			request.setAttribute("alert", form.getUserName() + " successfully create the account");
			//need to wait for confirmation?
			return Constants.adminNewAccConfirmJsp;
		}catch (Exception e) {
			errors.add(e.getMessage());
			return "errors.jsp";
		}
	}
}
