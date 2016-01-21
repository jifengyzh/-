package controller;

import model.VisitorDAO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import FilterAndConstant.Constants;
import databean.FundBean;
import databean.FundPriceHistoryBean;
import databean.FundValueBean;
import databean.PositionBean;
import databean.VisitorBean;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class VisitorViewAccountAction extends Action {
	private VisitorDAO visitorDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	private FundDAO fundDAO;
	
	public VisitorViewAccountAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() {return "visitor_view_account.do";}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors",errors);
		HttpSession session = request.getSession(false);
		DecimalFormat formatter = new DecimalFormat("#,##0.00");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			int visitorId = (Integer) session.getAttribute("visitorId");
			VisitorBean visitor = visitorDAO.read(visitorId);
			request.setAttribute("visitor", visitor);
			
			//set current balance for visitor
			long cashBalance = Long.valueOf(formatter.format(visitor.getCash()));
			request.setAttribute("cashBalance", cashBalance);
			
			//get the last trading date of this visitor
			Date lastTradeDate = transactionDAO.lastTradingDate(visitorId);
			if (lastTradeDate != null) {
				request.setAttribute("lastTradingDate", sdf.format(lastTradeDate));
				int processed = transactionDAO.getCustomerSuccessTrans(visitorId, 1, lastTradeDate);
				int processing = transactionDAO.getCustomerPendingTrans(visitorId, 0, lastTradeDate);
				request.setAttribute("processedNum", processed);
				request.setAttribute("processingNum", processing);
				
			} else {
				request.setAttribute("lastTradingDate", null);
				request.setAttribute("processedNum", null);
				request.setAttribute("processingNum", null);
			}
			
			PositionBean[] positionBeans = positionDAO.getPositionList(visitorId);
			
			FundValueBean[] fundValue = new FundValueBean[positionBeans.length];
			for (int i = 0; i< positionBeans.length; i++){
				
				PositionBean positionBean = positionBeans[i];
				
				fundValue[i] = new FundValueBean();
				fundValue[i].setFundId(positionBean.getFundId());
				
				long shares = (long)positionBean.getShares();
				
				DecimalFormat formatter1 = new DecimalFormat("#,##0.000");
				fundValue[i].setShares(formatter1.format(shares));
				
				FundBean fundBean = fundDAO.read(positionBean.getFundName());
				
				fundValue[i].setFundName(fundBean.getName());
				
				if(fundPriceHistoryDAO.lastTradingDate(positionBean.getFundId())!= null){
					FundPriceHistoryBean history = fundPriceHistoryDAO.getLastTrading(positionBean.getFundId());
					fundValue[i].setLastTradingDate(history.getPrice_date());
					double price = history.getPrice();
					formatter = new DecimalFormat("#,##0.00");
					fundValue[i].setLastTradingPrice(formatter.format(price));
					double value = positionBean.getShares()*price;
					fundValue[i].setValue(formatter.format(value));
				}
			}
			request.setAttribute("fundvalue", fundValue);
			return "customer-viewaccount.jsp";
		} catch (Exception e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
		return null;
	}
}

