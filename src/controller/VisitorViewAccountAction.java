package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import FilterAndConstant.Constants;
import databean.CustomerFundBean;
import databean.PositionBean;
import databean.VisitorBean;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.PositionDAO;
import model.VisitorDAO;

public class VisitorViewAccountAction extends Action {
	private VisitorDAO visitorDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	public VisitorViewAccountAction(Model model) {
		visitorDAO = model.getVisitorDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() {
		return Constants.visitorViewAccountAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		DecimalFormat formatter1 = new DecimalFormat("#,##0.00");
		DecimalFormat formatter2 = new DecimalFormat("#0.00");

		try {
			// Get the transaction history from visitorId
			int visitorId = (Integer) session.getAttribute("visitorId");
			VisitorBean visitor = visitorDAO.read(visitorId);
			
			PositionBean[] positionBeans = positionDAO.getPositionList(visitorId);			
			CustomerFundBean[] customerFundBeans = new CustomerFundBean[positionBeans.length];
			
			for (int i = 0; i < positionBeans.length; i++) {
				
				double price = fundPriceHistoryDAO.getFundPrice(positionBeans[i].getFundId(), (Date)session.getAttribute("lastDate"));
				double shares = positionBeans[i].getAvailableShares() / 1000;
				
				customerFundBeans[i].setName(fundDAO.read(positionBeans[i].getFundId()).getName());
				customerFundBeans[i].setShares(formatter1.format(shares));
				customerFundBeans[i].setPrice(formatter1.format(price));
				customerFundBeans[i].setValue(formatter2.format(price * shares));
			}
			
			// JSP pick up the shares >= 0
			request.setAttribute("visitor", visitor);
			request.setAttribute("customerFundBeans", customerFundBeans);
			
			return Constants.visitorViewAccountJsp;
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
		}
	}
}
