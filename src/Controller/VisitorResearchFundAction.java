package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import FilterAndConstant.Constants;
import databean.FundInfoBean;
import Model.FundDAO;
import Model.FundPriceHistoryDAO;
import Model.Model;
import databean.FundPriceHistoryBean;

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
		
		try {
			FundInfoBean[] fundInfoBeanList = fundPriceHistoryDAO.getAllFundsInfo();
			request.setAttribute("fundInfoBeanList", fundInfoBeanList);
			
			if (fundId != null) {
				FundPriceHistoryBean[] fundPriceList = fundPriceHistoryDAO.getFundPriceHistory(fundId);
				request.setAttribute("fundPriceList", fundPriceList);
				request.setAttribute("currentFundName", request.getParameter("fundName"));
			}
			
			
			return Constants.visitorResearchFundJsp;
		} catch(MyDAOException e) {
			errors.add(e.getMessage());
			return Constants.errorJsp;
					
		}
	}

}
