package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.VisitorSellFundForm;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class VisitorSellFundAction extends Action{
	private FormBeanFactory<VisitorSellFundForm> formBeanFactory 
		= FormBeanFactory.getInstance(VisitorSellFundForm.class);
	
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
		return Constants.visitorSellAction;
	}

	@Override
	public synchronized String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		
		try {
			
			int visitorId = (Integer)session.getAttribute("visitorId");
			
			PositionBean[] positionList = positionDAO.getPositionList(visitorId);
			request.setAttribute("positionList", positionList);
			
			VisitorSellFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return Constants.visitorSellJsp;
			}
			
			errors.addAll(form.getValidationErrors());
			
			if(errors.size() > 0) {
				return Constants.visitorSellJsp;
			}
			
			String fundName = form.getName();
			FundBean fundBean = fundDAO.read(fundName);
			
			if (fundBean == null) {
				errors.add("No such fund in the inventory!");
				return Constants.visitorSellJsp;
			}
			
			long sharesToSell = (Long)form.getShares();
			
			TransactionBean transactionBean = new TransactionBean();
			transactionBean.setVisitorId(visitorId);
			transactionBean.setFundId(fundBean.getFundId());
			
			long currentShares = positionDAO.getAvailableShares(transactionBean);
			
			if (currentShares  < sharesToSell) {
				errors.add("Amount to sell exceeds the limit");
				return Constants.visitorSellJsp;
			}
			//create new transactionBean type = 2 sell action
			transactionBean.setAmount(sharesToSell);
			int transactionType = 2;
			transactionBean.setTransactionType(transactionType);
			
			transactionDAO.sellFund(transactionBean);
			positionDAO.updateAvailableShares(transactionBean);
			long sharesBalance =positionDAO.getAvailableShares(transactionBean);
			
			request.setAttribute("sharesBalance", sharesBalance);
			request.setAttribute("alert", "Your request has been pending to be processed");
			return Constants.visitorBuyConfirmJsp;
			
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.visitorSellJsp;
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.visitorSellJsp;
		}
	}

}
