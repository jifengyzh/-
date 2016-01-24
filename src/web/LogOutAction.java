package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import FilterAndConstant.Constants;

public class LogOutAction extends Action{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Constants.logoutDo;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("visitorId") != null) {
			session.removeAttribute("visitorId");
		}
		if (session.getAttribute("employeeId") != null) {
			session.removeAttribute("employeeId");
		}
			
		return Constants.mainPage;	
	}

}
