package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.VisitorBean;
import formbean.AdminCreateNewAccForm;
import formbean.CreateNewAccForm;
import model.AdminDAO;
import model.Model;
import model.VisitorDAO;

public class AdminCreateNewAccAction extends Action{
	private FormBeanFactory<AdminCreateNewAccForm> createAccFormFactory = FormBeanFactory.getInstance(AdminCreateNewAccForm.class);
	private AdminDAO adminDAO;
	
	public AdminCreateNewAccAction(Model model) {
		adminDAO = model.getAdminDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.adminNewAdminAccAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			AdminCreateNewAccForm form = createAccFormFactory.create(request);
			if (!form.isPresent()) return Constants.adminNewAdminAccJsp;
			errors.addAll(form.getValidationErrors());
			if (!errors.isEmpty()) return Constants.adminNewAdminAccJsp;
			
			VisitorBean admin = adminDAO.read(form.getUserName());
			if  (admin != null) errors.add("User already exists");
			return Constants.adminNewAccJsp;
			
			synchronized(this) {
				admin = new VisitorBean();
				admin.setUserName(form.getUserName());
				admin.setFirstName(form.getFirstName());
				admin.setLastName(form.getLastName());
		       	admin.setPassword(form.getPassword());
		       	adminDAO.create(admin);
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
