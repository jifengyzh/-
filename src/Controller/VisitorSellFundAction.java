package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanFactory;

import FormBean.VisitorSellFundForm;
import Model.FundDAO;
import Model.Model;
import Model.PositionDAO;
import Model.TransactionDAO;
import databean.FundBean;
import databean.PositionBean;

public class VisitorSellFundAction extends Action{
	private FormBeanFactory<VisitorSellFundForm> formBeanFactory = FormBeanFactory<FormBean>.getInstance(VisitorSellFundForm.class)
	
	private FundDAO fundDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	
	public VisitorSellFundAction(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO =model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}
			
			
	@Override
	public String getName() {
		return "visitor_sell_fund.do";
	}

	@Override
	public synchronized String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		
		try {
			String fundName = request.getParameter("FundName");
			if (fundName != null) request.setAttribute("FundName", fundName);
			
			int customerId = (Integer)session.getAttribute("visitorId");
			PositionBean[] positionList = positionDAO.getCustomerPortfolio(customerId);
			request.setAttribute("positionList", positionList);
			
			VisitorSellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return "visitor_sell_fund.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			
			if(errors.size() > 0) {
				return "visitor_seell_fund.jsp";
			}
			
			String fundNameInput = form.getFundName();
			FundBean fundBean = fundDAO.read(fundNameInput);
			
			if (fundBean == null) {
				errors.add("No such fund in the inventory!");
				return "visitor_sell_fund.jsp";
			}
			
			double shares = form.getSharesAsDouble();
			
			transactionDAO.sellFund(customerId, fundBean.getFundId(), shares);
			
			request.setAttribute("alert", "Your request has been pending to be processed");
			return "visitor_sell_confirmation.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "visitor_sell_fund.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "visitor_sell_fund.jsp";
		}
				
				

		
		
		
		return null;
	}

}
