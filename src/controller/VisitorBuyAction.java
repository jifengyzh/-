package controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import FormBean.VisitorBuyFundForm;
import Model.visitorDAO;
import databean.FundBean;
import databean.VisitorBean;
import Model.FundDAO;
import Model.FundPriceHistoryDAO;
import Model.TransactionDAO;
import Model.VisitorDAO;
import model.Model;

public class VisitorBuyAction extends Action{
	
	private FormBeanFactory<VisitorBuyFundForm> formBeanFactory = FormBeanFactory<FormBean>.getInstance(BuyFundForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;
	DecimalFormat formatter;
	
	public VisitorBuyAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		visitorDAO = model.getvisitorDAO();
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
			
			FundGeneralInfoBean[] fundGeneralList = fundPriceHistoryDAO.getAllFundsGeneralInfo();
			request.setAttribute("fundGeneralList", fundGeneralList);
			
			int customerId = (Integer)session.getAttribute("customerId");
			VisitorBean visitor = visitorDAO.read(customerId);
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
			
			double amount = form.getAmountAsDouble();
			
			transactionDAO.buyFund(customerId, fund.getFundId(), amount);
			
			request.setAttribute("alert", "Thank you! your request to buy " + form.getFundName() + 
			"has been queued until transaction day");
			
			return Constants.visitorBuyConfirmJsp;
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return Constants.errorJsp;
		}
	}
}
