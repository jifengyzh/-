package controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.PositionBean;
import databean.VisitorBean;
import formbean.CustomerUserNameForm;

public class EmployeeViewCustomerAccountAction extends Action {
	private FormBeanFactory<CustomerUserNameForm> formBeanFactory = FormBeanFactory.getInstance(CustomerUserNameForm.class);
	
	private TransactionHistoryDAO transactionHistoryDAO;
	private VisitorDAO visitorDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private TransactionDAO transactionDAO;
	
	public EmployeeViewCustomerAccountAction(Model model) {
		transactionHistoryDAO = model.getTransactionHistoryDAO();
		visitorDAO = model.getVisitorDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		
	}
	
	public String getName() { return Constants.employeeViewCustomerAccountAction;}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			if (request.getParameter("username") != null) {
				
				CustomerUserNameForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				//get all customer lists, in case of user not found
				VisitorBean[] customerlist = visitorDAO.getAllCustomers();
				if (customerlist != null) {
					request.setAttribute("customerlist", customerlist);
				}
				else {
					request.setAttribute("customerlist", null);
				}
				
				if (!form.isPresent()) {
		            return Constants.employeeViewCustomerAccountJsp;
		        }
				
				// add error validation
				errors.addAll(form.getValidationErrors());
				if (errors.size() != 0) {
					return Constants.employeeViewCustomerAccountJsp;
				}
				

				VisitorBean customer = visitorDAO.read(form.getUsername());
				if (customer == null) {
					errors.add("User does not exist!");
					return Constants.employeeViewCustomerAccountJsp;
				}
				
				//if user found, set customer list empty, show detail info of specified customer
				request.setAttribute("customerlist", null);
				
				int customerId = customer.getCustomerId();

				request.setAttribute("customer", customer);
				DecimalFormat formatter = new DecimalFormat("#,##0.00");
				request.setAttribute("cash",formatter.format(customer.getCash()));
				
				Date lastTradingDate = transactionDAO.getCustomerLastTradeDate(customerId);
				if (lastTradingDate != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					request.setAttribute("lastTradingDate", sdf.format(lastTradingDate));
				} else {
					request.setAttribute("lastTradingDate", null);
				}
				
				// get user's fund list and position
				PositionBean[] position = positionDAO.getPositionList(customerId);
				
				FundValueBean[] fundValue = new FundValueBean[position.length];
				for (int i = 0; i< position.length; i++){
					PositionBean temp = position[i];
					
					fundValue[i] = new FundValueBean();
					fundValue[i].setFundId(position[i].getFundId());
					double shares = position[i].getShares();
					DecimalFormat formatter1 = new DecimalFormat("#,##0.000");
					fundValue[i].setShares(formatter1.format(shares));
					FundBean fundBean = fundDAO.read(temp.getFundName());
					fundValue[i].setFundName(fundBean.getName());
					if(fundPriceHistoryDAO.getLastTrading(position[i].getFundId())!= null){
						FundPriceHistoryBean history = fundPriceHistoryDAO.getLastTrading(position[i].getFundId());
						fundValue[i].setLastTradingDate(history.getPrice_date());
						double price = history.getPrice();
						formatter = new DecimalFormat("#0.00");
						fundValue[i].setLastTradingPrice(formatter.format(price));
						double value = position[i].getShares()*price;
						fundValue[i].setValue(formatter.format(value));
					}
			
				}

				request.setAttribute("fundvalue",fundValue);
				
			}
			else {
				CustomerBean[] customerlist = visitorDAO.getAllCustomers();
				if (customerlist != null) {
					request.setAttribute("customerlist", customerlist);
				}
				else {
					request.setAttribute("customerlist", null);
				}
			}
			
			return "employee-viewcustomer.jsp";		
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "employee-viewcustomer.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "employee-viewcustomer.jsp";
		}
		
	}
}

	

		
