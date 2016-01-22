package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import FilterAndConstant.Constants;
import model.Model;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	public void init() throws ServletException {
		Model model = null;
		try {
			model = new Model(getServletConfig());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Action.add(new VisitorBuyAction(model));
		Action.add(new VisitorDepositCheckAction(model));
		Action.add(new VisitorLogAction(model));
		Action.add(new VisitorPwdChangeAction(model));
		Action.add(new VisitorResearchFundAction(model));
		Action.add(new VisitorSellFundAction(model));
		Action.add(new VisitorTransactionReviewAction(model));
		Action.add(new VisitorViewAccountAction(model));

		Action.add(new EmployeeChangePwdAction(model));
		Action.add(new EmployeeCreateCustomerAccAction(model));
		Action.add(new EmployeeCreateEmployeeAccAction(model));
		Action.add(new EmployeeCreateFundAction(model));
		Action.add(new EmployeeDepositCheckAction(model));
		Action.add(new EmployeeLoginAction(model));
		Action.add(new EmployeeResetCustomerPasswordAction(model));
		Action.add(new EmployeeTransitionDayAction(model));
		Action.add(new EmployeeViewCustomerAccountAction(model));
		Action.add(new EmployeeViewTransactionHistoryAction(model));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	/*
	 * Extracts the requested action and (depending on whether the user is
	 * logged in) perform it (or make the user login).
	 * 
	 * @param request
	 * 
	 * @return the next page (the view)
	 */
	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		String urlName = request.getRequestURL().toString();
		urlName = getURLName(urlName);

		// start
		if (action.equals("start")) {
			if (session.getAttribute("visitorId") != null) {
				return Constants.visitorViewAccountAction;
			}

			if (session.getAttribute("employeeId") != null) {
				return Constants.employeeMainPanelJsp;
			}

			return "index.jsp";
		}

		if (action.endsWith(".do")) {
			if (containsAction(action, Constants.visitorActions)) {
				if (session.getAttribute("customerId") == null) {
					return Action.perform(Constants.logoutDo, request);
				}
			} else if (containsAction(action, Constants.employeeActions)) {
				if (session.getAttribute("employeeId") == null) {
					return Action.perform(Constants.logoutDo, request);
				}
			}

		}

		// Let the logged in user run his chosen action
		return Action.perform(action, request);
	}

	/*
	 * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
	 * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
	 * page (the view) This is the common case
	 */
	private void sendToNextPage(String nextPage, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			response.setHeader("Cache-Control", "no-cache");
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
			return;
		}

		throw new ServletException(
				Controller.class.getName() + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
	}

	/*
	 * Returns the path component after the last slash removing any "extension"
	 * if present.
	 */
	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

	private String getURLName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

	private boolean containsAction(String action, String[] list) {
		for (String item : list) {
			if (item.equals(action)) {
				return true;
			}
		}
		return false;
	}
}
