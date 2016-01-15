package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FormBean.VisitorChangePwdForm;
import Model.VisitorDAO;
import databean.VisitorBean;
import Model.Model;

public class VisitorPwdChangeAction extends Action{



			FormBeanFactory<FormBean>.getInstance(ChangePasswordForm.class);
	
	private VisitorDAO visitorDAO;


	public VisitorPwdChangeAction(Model model) {
		visitorDAO = model.getVisitorDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "visitor_change_pwd.do";
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
				return "visitor_change_pwd.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "visitor_change_pwd.jsp";
			}
			
			synchronized(this) {
				VisitorBean visitor = VisitorDAO.read((Integer) session.getAttribute("customerId"));
				
				if(!visitor.checkPassword(form.getOldPassword())){
					errors.add("Incorrect Password!! Please re-enter your current password.");
					return "visitor_changepwd.jsp";
				}
				
				VisitorDAO.changePassword(visitor.getCustomerId(), form.getNewPassword());
				
				
			}
			request.setAttribute("alert", "Password changed!!!!");
			return "visitor-confirmation.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
