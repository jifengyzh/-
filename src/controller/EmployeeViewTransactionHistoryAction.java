/**
 * @author Arwen
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.TransactionBean;
import databean.VisitorBean;
import formbean.CustomerUserNameForm;
import model.Model;
import model.TransactionDAO;
import model.VisitorDAO;

public class EmployeeViewTransactionHistoryAction extends Action {
	private FormBeanFactory<CustomerUserNameForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerUserNameForm.class);

	private TransactionDAO transactionDAO;
	private VisitorDAO visitorDAO;

	public EmployeeViewTransactionHistoryAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		visitorDAO = model.getVisitorDAO();
	}

	public String getName() {
		return Constants.employeeViewCustTransHistoryAction;
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			VisitorBean[] customerlist = visitorDAO.getAllCustomers();
			request.setAttribute("customerlist", customerlist);
			if (request.getParameter("username") == null)
				return Constants.employeeViewCustAccJsp;
			
			CustomerUserNameForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return Constants.employeeViewCustTransHistoryJsp;
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeViewCustTransHistoryJsp;
			}

			VisitorBean customer = visitorDAO.read(form.getUsername());
			if (customer == null) {
				errors.add("User does not exist!");
				return Constants.employeeViewCustTransHistoryJsp;
			}
			TransactionBean[] transactions = transactionDAO.getTransactionHistory(customer.getVisitorId());
			request.setAttribute("transactions", transactions);
			return Constants.employeeViewCustTransHistoryJsp;

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.employeeViewCustTransHistoryJsp;
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.employeeViewCustAccJsp;
		}

	}

}
