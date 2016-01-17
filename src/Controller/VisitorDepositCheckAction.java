package Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import FormBean.DepositCheckForm;
import Model.Model;
import Model.TransactionDAO;
import Model.VisitorDAO;
import databean.VisitorBean;

public class VisitorDepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(RequestCheckFormBean.class);

	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	
	public VisitorDepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		visitorDAO = model.getvisitorDAO();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorDepositAction;
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
				return Constants.visitorDepositeJsp;
			}
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return Constants.visitorDepositeJsp;
			}
			
			double amount = form.getAmountAsDouble();
			transactionDAO.requestDeposit(visitorId, amount);
			
			request.setAttribute("alert", "Your deposit request for $ " + 
										formatter.format(amount) + " has been waited for transaction");
			
			return Constants.visitorDepositConfirm;
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
	}
}
