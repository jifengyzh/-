package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import FilterAndConstant.Constants;
import Model.Model;
import Model.TransactionHistoryDAO;
import Model.VisitorDAO;
import databean.TransactionHistoryBean;
import databean.VisitorBean;

public class VisitorTransactionReviewAction extends Action {
	private TransactionHistoryDAO transactionHistoryDAO;
	private VisitorDAO visitorDAO;
	
	public VisitorTransactionReviewAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
		visitorDAO = model.getVisitorDAO();
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
		VisitorBean visitor = visitorDAO.read(visitorId);
		TransactionHistoryBean[] historyList = transactionHistoryDAO.getTransactions(visitor.getVisitorId());
		
		request.setAttribute("transactionHistory", historyList);
		return Constants.visitorViewTransHistoryJsp;
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
	}
	
}
