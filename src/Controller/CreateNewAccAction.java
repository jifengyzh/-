package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import FormBean.CreateNewAccForm;
import Model.CustomerDAO;
import Model.Model;
import databean.CustomerBean;

public class CreateNewAccAction extends Action{
	private FormBeanFactory<CreateNewAccForm> createAccFormFactory = FormBeanFactory.getInstance(CreateNewAccForm.class);
	private CustomerDAO customerDAO;
	
	public CreateNewAccAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "newaccount.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			CreateNewAccForm form = createAccFormFactory.create(request);
			if (!form.isPresent()) return "newaccount.jsp";
			errors.addAll(form.getValidationErrors());
			if (!errors.isEmpty()) return "newaccount.jsp";
			
			CustomerBean customer = customerDAO.read(form.getUserName());
			if  (customer != null) errors.add("User already exists");
			return "newaccount.jsp";
			
			synchronized(this) {
				customer = new CustomerBean();
				customer.setUserName(form.getUserName());
				customer.setFirstName(form.getFirstName());
				customer.setLastName(form.getLastName());
		       	customer.setPassword(form.getPassword());
		       	customer.setAddrLine1(form.getAddrLine1());
		       	customer.setAddrLine2(form.getAddrLine2());
		       	customer.setCity(form.getCity());
		       	customer.setState(form.getState());
		       	customer.setZip(form.getZipAsInt());
		       	customerDAO.create(customer);
			}
			
			request.setAttribute("message", form.getUserName() + " successfully create the account");
			//need to wait for confirmation?
			return "employee-confirmation.jsp";
		}catch (Exception e) {
			errors.add(e.getMessage());
			return "errors.jsp";
		}
	}
}
