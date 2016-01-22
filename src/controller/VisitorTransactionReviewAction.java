package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import FilterAndConstant.Constants;
import databean.TransactionBean;
import model.Model;
import model.TransactionDAO;

public class VisitorTransactionReviewAction extends Action {
	private TransactionDAO transactionDAO;
	
	public VisitorTransactionReviewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorViewTransHistoryAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
		int visitorId = (Integer) session.getAttribute("visitorId");
		TransactionBean[] transactionBeans = transactionDAO.getTransactionHistory(visitorId);
		
		if (transactionBeans == null) {
			errors.add("No such visitor");
			return Constants.errorJsp;
		}
		
		request.setAttribute("transactionBeans", transactionBeans);
		return Constants.visitorViewTransHistoryJsp;
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
	}
	
}
