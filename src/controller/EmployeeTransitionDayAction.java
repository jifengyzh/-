/**
 * @author Arwen & Team7
 * 2016-01-21
 */

package controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.CustomerBean;
import databean.FundBean;
import databean.FundGeneralInfoBean;
import databean.FundPriceHistoryBean;
import databean.PendingTransactionBean;
import databean.PositionBean;
import databean.TransactionBean;
import formbean.EmployeeCreateFundForm;
import formbean.EmployeeDepositCheckForm;
import formbean.EmployeeTransitionDayForm;
import formbean.TransitionDayForm;

import model.CustomerDAO;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;
import model.VisitorDAO;

public class EmployeeTransitionDayAction extends Action {
	private VisitorDAO visitorDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private FormBeanFactory<EmployeeTransitionDayForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeTransitionDayForm.class);
	
	public EmployeeTransitionDayAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}
	
	public String getName() { return Constants.employeeTransitionDayAction; }
	
	public synchronized String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        request.setAttribute("success", null);
        HttpSession session = request.getSession();
        
        try {
        	EmployeeTransitionDayForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);
            FundBean[] fund = fundDAO.getAllFund();
            request.setAttribute("fundList", fund);
            if (!form.isPresent()) return Constants.employeeTransitionDayJsp;
            
        	DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        	Date lastdate = form.getDate();
        	session.setAttribute("lastdate", lastdate);
        	Map<Integer, Long> map = form.getNewFundPrice();
        	transactionDAO.processTransaction(map, lastdate);
        	
        	TransactionBean[] transaction = transactionDAO.getLastDayTransaction((java.sql.Date)lastdate);
        	positionDAO.processPosition(transaction);
        	fundPriceHistoryDAO.processFundPriceHistory(map,lastdate);
        	visitorDAO.processVisitor(transaction);
        	
	        
	        // Display message
	        request.setAttribute("success","success");
	        return Constants.employeeTransitionDayAction;
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return Constants.employeeTransitionDayAction;
        }
    }
}
