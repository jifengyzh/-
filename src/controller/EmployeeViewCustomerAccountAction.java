package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;
import model.TransactionHistoryDAO;
import model.VisitorDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.CustomerFundBean;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.PositionBean;
import databean.VisitorBean;
import formbean.CustomerUserNameForm;

public class EmployeeViewCustomerAccountAction extends Action {
	private FormBeanFactory<CustomerUserNameForm> formBeanFactory = FormBeanFactory
			.getInstance(CustomerUserNameForm.class);

	private VisitorDAO visitorDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;

	public EmployeeViewCustomerAccountAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();

	}

	public String getName() {
		return Constants.employeeViewCustAccAction;
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			VisitorBean[] customerlist = visitorDAO.getAllCustomers();
			request.setAttribute("customerlist", customerlist);
			if (request.getParameter("username") != null) {

				CustomerUserNameForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);

				// If no customer is selected, show all the customers in the
				// system.

				if (!form.isPresent()) {
					return Constants.employeeViewCustAccJsp;
				}

				// If there's error with the given username.
				// Return to the list page.
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return Constants.employeeViewCustAccJsp;
				}

				VisitorBean visitor = visitorDAO.read(form.getUsername());
				if (visitor == null) {
					errors.add("User does not exist!");
					return Constants.employeeViewCustAccJsp;
				}

				// Show detail info of specified customer
				request.setAttribute("customerlist", null);
				// Name, Address
				int visitorId = visitor.getVisitorId();
				request.setAttribute("customer", visitor);
				// Cash
				DecimalFormat formatter = new DecimalFormat("#,##0.00");
				request.setAttribute("cash", formatter.format(visitor.getCash()));

				// Fund list and value
				PositionBean[] position = positionDAO.getPositionList(visitorId);
				CustomerFundBean[] customerFund = new CustomerFundBean[position.length];
				for (int i = 0; i < position.length; i++) {
					DecimalFormat formatter1 = new DecimalFormat("#,##0.000");
					DecimalFormat formatter2 = new DecimalFormat("#0.00");
					int fund_id = position[i].getFundId();
					double price = fundPriceHistoryDAO.getFundPrice(fund_id, (Date) session.getAttribute("lastdate"))
							/ 100;
					double shares = position[i].getShares() / 1000;

					customerFund[i].setShares(formatter1.format(shares));
					customerFund[i].setName(fundDAO.read(fund_id).getName());
					customerFund[i].setSymbol(fundDAO.read(fund_id).getSymbol());
					customerFund[i].setPrice(formatter1.format(price));
					customerFund[i].setValue(formatter2.format(price * shares));
				}
				request.setAttribute("customerfund", customerFund);

			}
			return Constants.employeeViewCustAccJsp;
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.employeeViewCustAccJsp;
		} catch (Exception e) {
			errors.add(e.getMessage());
			return Constants.employeeViewCustAccJsp;
		}

	}
}
