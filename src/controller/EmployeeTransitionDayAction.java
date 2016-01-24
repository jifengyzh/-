/**
 * @author Arwen & Team7
 * 2016-01-21
 */

package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.FundBean;
import databean.TransactionBean;
import formbean.EmployeeTransitionDayForm;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.LastDateDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;
import model.VisitorDAO;

public class EmployeeTransitionDayAction extends Action {
	private VisitorDAO visitorDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private LastDateDAO lastDateDAO;
	
	public EmployeeTransitionDayAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		lastDateDAO = model.getLastDateDAO();
	}
	
	public String getName() { return Constants.employeeTransitionDayAction; }
	
	public synchronized String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        request.setAttribute("success", null);
        HttpSession session = request.getSession();
        try {
        	synchronized(this) {
	        	EmployeeTransitionDayForm form = new EmployeeTransitionDayForm(request);
	            request.setAttribute("form", form);
	            FundBean[] fund = fundDAO.getAllFund();
	            request.setAttribute("fundList", fund);
	            if (!form.isPresent()) return Constants.employeeTransitionDayJsp;
	            
	        	DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	        	Date lastdate = formatter.parse(form.getDate());
	        	session.setAttribute("lastdate", lastdate);
	        	lastDateDAO.setLastDate(lastdate);
	        	
	        	HashMap<Integer, Long> map = form.getNewFundPrice();
	        	transactionDAO.transitionDay(map, lastdate);
	        	
	        	TransactionBean[] transaction = transactionDAO.getLastDayTransaction((java.sql.Date)lastdate);
	        	positionDAO.updatePositions(transaction);
	        	fundPriceHistoryDAO.updateFundPrice(map,(java.sql.Date)lastdate);
	        	visitorDAO.updateVisitor(transaction);
        	}
        	
	        // Display message
	        request.setAttribute("success","Transition day successful!");
	        return Constants.employeeTransitionDayJsp;
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return Constants.employeeTransitionDayJsp;
        } catch (ParseException e) {
			errors.add("Date format is not the correct type");
			return Constants.employeeTransitionDayJsp;
		}
    }
}
