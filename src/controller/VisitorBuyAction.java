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
import formbean.VisitorBuyFundForm;
import model.VisitorDAO;
import databean.FundBean;
import databean.FundInfoBean;
import databean.VisitorBean;
import model.VisitorDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.TransactionDAO;
import model.Model;

public class VisitorBuyAction extends Action{
	
	private FormBeanFactory<VisitorBuyFundForm> formBeanFactory =
			 FormBeanFactory.getInstance(VisitorBuyFundForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	private DecimalFormat formatter;
	
	public VisitorBuyAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		visitorDAO = model.getVisitorDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	@Override
	public String getName() {return Constants.visitorBuyAction;}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			String fundName = request.getParameter("getFundName");
			if (fundName != null) request.setAttribute("getFundName", fundName);
			
			FundInfoBean[] fundGeneralList = fundPriceHistoryDAO.getAllFundsGeneralInfo();
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			int visitorId = (Integer)session.getAttribute("customerId");
			VisitorBean visitor = visitorDAO.read(visitorId);
			formatter = new DecimalFormat("#,##0.00");
			String cash = formatter.format(visitor.getCash());
			request.setAttribute("cash", cash);		
			
			VisitorBuyFundForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
				return Constants.visitorBuyJsp;
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.visitorBuyJsp;
			}
			
			request.setAttribute("form", form);
			
			FundBean fund = fundDAO.read(form.getFundName());
			if (fund == null) {
				errors.add("Fund does not exist!");
				return Constants.visitorBuyJsp;
			}
			
			double amount = Integer.valueOf(form.getAmount());
			
			
			transactionDAO.buyFund(visitorId, fund.getFundId(), amount);
			
			request.setAttribute("alert", "Thank you! your request to buy " + form.getFundName() + 
			"has been queued until transaction day");
			
			return Constants.visitorBuyConfirmJsp;
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return Constants.errorJsp;
		} catch (RollbackException e) {
			errors.add(e.toString());
			return Constants.errorJsp;
		}
	}
}
