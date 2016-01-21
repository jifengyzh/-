package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.CustomerBean;
import databean.TransactionHistoryBean;
import databean.VisitorBean;
import formbean.CustomerIdForm;
import formbean.CustomerUserNameForm;
import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionHistoryDAO;
import model.VisitorDAO;

public class EmployeeViewTransactionHistoryAction extends Action {
	private FormBeanFactory<CustomerUserNameForm> formBeanFactory = FormBeanFactory.getInstance(CustomerUserNameForm.class);
	
	private TransactionHistoryDAO transactionHistoryDAO;
	private VisitorDAO customerDAO;
	
	public EmployeeViewTransactionHistoryAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
		customerDAO = model.getVisitorDAO();
	}
	
	public String getName() { return Constants.employeeViewCustomerTransactionHistoryAction; }
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			TransactionHistoryBean[] historyList;
			if (request.getParameter("username") != null) {
				CustomerUserNameForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				if (!form.isPresent()) {
		            return "create-account.jsp";
		        }
				
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return "employee-viewtransaction.jsp";
				}
				
				VisitorBean customer = visitorDAO.read(form.getUsername());
				if (customer == null) {
					errors.add("User does not exist!");
					return "employee-viewtransaction.jsp";
				}
				
				historyList = transactionHistoryDAO.getTransactions(customer.getVisitorId());
				for (int i=0; i<historyList.length; i++) {
					historyList[i].setUsername(customer.getUserName());
				}
			}
			else {
				historyList = transactionHistoryDAO.getTransactions();
				for (int i=0; i<historyList.length; i++) {
					int uid = historyList[i].getCustomerId();
					VisitorBean customer = customerDAO.read(uid);
					historyList[i].setUsername(customer.getUserName());
				}
			}
			
			request.setAttribute("transactionHistory", historyList);
			return "employee-viewtransaction.jsp";
			
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee-viewtransaction.jsp";
		}
		
	}
	
}
