package Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import FormBean.DepositCheckForm;
import Model.VisitorDAO;
import databean.VisitorBean;

public class VisitorDepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(RequestCheckFormBean.class);

	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	
	public VisitorDepositCheckAction(Mode model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "visitor_deposit_check.do";
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
			
			DepositCheckForm form = formBeanFactory.create(reqeust);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return "visitor_deposit_check.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return "visitor_deposit_check.jsp";
			}
			
			double amount = form.getAmountAsDouble();
			transactionDAO.requestDeposit(visitorId, amount);
			
			request.setAttribute("alert", "Your deposit request for $ " + 
										formatter.format(amount) + " has been waited for transaction");
			
			return "visitor_deposit_check_success.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		}
	}
}
