package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.VisitorBean;
import formbean.LoginForm;
import model.LastDateDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;
import model.VisitorDAO;


public class VisitorLogAction extends Action{
	private FormBeanFactory<LoginForm> formBeanFactory 
		= FormBeanFactory.getInstance(LoginForm.class);
	
	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	private LastDateDAO lastdateDAO;
	
	public VisitorLogAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorLogAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
		
//		if (session.getAttribute("cusomerId") != null) {
//			return Constants.visitorViewAccountAction;
//		}
//		
//		if (session.getAttribute("employeeId") != null) {
//        	return Constants.employeeMainPanelJsp;
//        }
		
//		try {
//			session.setAttribute("lastDate", lastdateDAO.getLastDate());
//			
//			LoginForm form = formBeanFactory.create(request);
//			
//	        if (!form.isPresent()) {
//	            return Constants.mainPage;
//	        }
//	        
//	        errors.addAll(form.getValidationErrors());
//	        if (errors.size() != 0) {
//	            return Constants.mainPage;
//	        }
//	        
//	        VisitorBean visitor = visitorDAO.read(form.getUserName());
//	        
//	        if (visitor == null) {
//	            errors.add("Incorrect/Invalid Customer Username");
//	            return Constants.mainPage;
//	        }
//	        
//	        if (!visitor.checkPassword(form.getPassword())) {
//	            errors.add("Incorrect/Invalid Password");
//	            return Constants.mainPage;
//	        }
//	        //Set a visitor's id firstname lastname in session for JSP
//	        int visitorId = (Integer) visitor.getVisitorId();
//	        session.setAttribute("visitorId", visitorId);
//	        //Get the specific customer lastTradeDate.
//	        Date lastTradeDate = visitorDAO.getLastTradingDate(visitorId);
//	        visitor.setLastTradingDate(lastTradeDate);
//			session.setAttribute("firstname", visitor.getFirstName());
//			session.setAttribute("lastname", visitor.getLastName());
			
			return Constants.employeeMainPanelJsp;
//		} catch (FormBeanException e) {
//			errors.add(e.getMessage());
//			return Constants.visitorViewAccountJsp;
//		} catch (RollbackException e) {
//			errors.add(e.getMessage());
//			return Constants.visitorViewAccountJsp;
//		}
	}

}
