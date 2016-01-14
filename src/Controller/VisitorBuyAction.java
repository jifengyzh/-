package Controller;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import Model.CustomerDAO;
import Model.FundDAO;
import Model.FundPriceHistoryDAO;
import Model.TransactionDAO;
import databean.CustomerBean;
import model.Model;

public class VisitorBuyAction extends Action{
	
	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory<FormBean>.getInstance(BuyFundForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	DecimalFormat formatter;
	
	public VisitorBuyAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	@Override
	public String getName() {return "visitorbuyaction.do";}

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
			CustomerBean customer = customerDAO.read(customerId);
			formatter = new DecimalFormat("#,##0.00");
			String cash = formatter.format(customer.getCash());
			request.setAttribute("cash", cash);		
			
			BuyFundForm form = formBeanFactory.create(request);
			if (!form.isPresent()) {
				return "visitorbuyaction.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "visitorbuyaction.jsp";
			}
			
			request.setAttribute("form", form);
			
			FunBean fund = fundDAO.read(form.getFundName());
			if (fund == null) {
				errors.add("Fund does not exist!");
				return "visitorbuyaction.jsp";
			}
			
			double amount = form.getAmountAsDouble();
			double cash = customer.getCash();
			
			transactionDAO.buyFund(customerId, fund.getFundId(), amount);
			
			request.setAttribute("message", "Thank you! your request to buy " + form.getFundName() + 
			"has been queued until transaction day");
			
			return "visitorcomfirmation.jsp";
		} catch (MyDAOException e) {
			errors.add(e.getMessage());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.toString());
			return "error.jsp";
		}
	}
}
