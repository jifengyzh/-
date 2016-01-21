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

public class VisitorViewAccountAction extends Action {
	private TransactionDAO transactionDAO;

	public VisitorViewAccountAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return Constants.visitorViewAccountAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);

		try {
			// Get the transaction history from visitorId
			int visitorId = (Integer) session.getAttribute("visitorId");
			TransactionBean[] transactionBeans = transactionDAO.getTransactionHistory(visitorId);

			if (transactionBeans == null) {
				errors.add("No such visitor");
				return Constants.visitorViewAccountJsp;
			}
			// JSP pick up the shares >= 0
			request.setAttribute("transactionBeans", transactionBeans);
			return Constants.visitorViewAccountJsp;
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
	}
}
