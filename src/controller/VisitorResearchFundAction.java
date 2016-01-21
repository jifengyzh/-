package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import FilterAndConstant.Constants;
import databean.CustomerFundBean;
import databean.FundBean;
import databean.FundInfoBean;
import databean.FundPriceHistoryBean;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;
import model.MyDAOException;

public class VisitorResearchFundAction extends Action {
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public VisitorResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.visitorResearchFundAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		Integer fundId = 0;
		if (request.getParameter("fundId") != null) {
			fundId = Integer.parseInt(request.getParameter("fundId"));
		}
		
		//get all the funds, then set a list of CustomerFundBean, then store the list into customerFundBeans
		FundBean[] fundBeans = fundDAO.getAllFunds();
		CustomerFundBean[] customerFundBeans = new CustomerFundBean[fundBeans.length];
		for (int i = 0; i < fundBeans.length; i++) {
			customerFundBeans[i].setName(fundBeans[i].getName());
			customerFundBeans[i].setSymbol(fundBeans[i].getSymbol());
			
			Long fundPrice = fundPriceHistoryDAO.getFundPrice(fundBeans[i].getFundId(), (Date)session.getAttribute("lastDate"));
			customerFundBeans[i].setPrice(String.valueOf(fundPrice));
				
		}
		request.setAttribute("customerFundBeans", customerFundBeans);
		
		
		return Constants.visitorResearchFundJsp;
	}
}
