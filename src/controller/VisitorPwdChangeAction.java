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
import formbean.VisitorChangePwdForm;
import model.Model;
import model.VisitorDAO;

public class VisitorPwdChangeAction extends Action{


	private FormBeanFactory<VisitorChangePwdForm> formBeanFactory =
			FormBeanFactory.getInstance(VisitorChangePwdForm.class);
	
	private VisitorDAO visitorDAO;


	public VisitorPwdChangeAction(Model model) {
		visitorDAO = model.getVisitorDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorChangePwdAction;
	}


	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			VisitorChangePwdForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
				return Constants.visitorChangePwdJsp;
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return  Constants.visitorChangePwdJsp;
			}
			
			synchronized(this) {
				VisitorBean visitor = visitorDAO.read((Integer) session.getAttribute("visitorId"));
				
				if(!visitor.checkPassword(form.getOldPassword())){
					errors.add("Incorrect Password!! Please re-enter your current password.");
					return Constants.visitorChangePwdJsp;
				}
				
				visitorDAO.setPassword(visitor.getVisitorId(), form.getNewPassword());
				
				
			}
			request.setAttribute("alert", "Password changed!!!!");
			return Constants.visitorChangePwdConfirmJsp;
			
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
