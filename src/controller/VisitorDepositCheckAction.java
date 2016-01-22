package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.VisitorBean;
import formbean.EmployeeDepositCheckForm;
import model.Model;
import model.TransactionDAO;
import model.VisitorDAO;

public class VisitorDepositCheckAction extends Action {
	private FormBeanFactory<EmployeeDepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(EmployeeDepositCheckForm.class);

	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	
	public VisitorDepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		visitorDAO = model.getVisitorDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorRequestAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
				
		
		try {
			int visitorId = (Integer)session.getAttribute("visitorId");		
			VisitorBean visitor = visitorDAO.read(visitorId);
			request.setAttribute("visitor", visitor);
			
			double balance = visitor.getCash();
			request.setAttribute("balance", formatter.format(balance));
			
			EmployeeDepositCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return Constants.visitorRequestJsp;
			}
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return Constants.visitorRequestJsp;
			}
			
			long amount = Long.valueOf(form.getAmount());
			
			if (amount <= 0 ) {
				errors.add("Amount you input is not illegal");
				return Constants.visitorRequestJsp;
			}
			
			long currentCash = visitorDAO.getAvailableCash(visitorId);
			if (amount > currentCash) {
				errors.add("No enough cash");
				return Constants.visitorRequestJsp;
			}
			//create pending transaction (transaction without execute date)
			transactionDAO.requestCheck(visitorId, amount);
			//update available cash of visitor according amount
			visitorDAO.updateAvailableCash(visitorId, amount);
			
			long cashBalance = visitorDAO.getAvailableCash(visitorId);
			
			request.setAttribute("cashBalance", cashBalance);
			request.setAttribute("alert", "Your deposit request for $ " + 
										formatter.format(amount) + " has been waited for transaction");
			
			return Constants.visitorRequestJsp;
			
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.visitorRequestJsp;
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.visitorRequestJsp;
		}
		
	}
}
