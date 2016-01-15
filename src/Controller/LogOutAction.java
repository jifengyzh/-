package Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutAction extends Action{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "logout.do";
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
			
		return "index.jsp";	
	}

}
