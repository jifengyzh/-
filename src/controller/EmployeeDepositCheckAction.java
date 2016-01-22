package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.CustomerBean;
import databean.EmployeeBean;
import databean.TransactionBean;
import databean.VisitorBean;
import formbean.DepositCheckFormBean;
import formbean.EmployeeDepositCheckForm;
import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import model.VisitorDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EmployeeDepositCheckAction extends Action {

	private FormBeanFactory<EmployeeDepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeDepositCheckForm.class);

	private VisitorDAO visitorDAO;
	private TransactionDAO transactionDAO;

	public EmployeeDepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		visitorDAO = model.getVisitorDAO();
	}

	@Override
	public String getName() {
		return Constants.employeeDepositCheckAction;
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		if (session.getAttribute("employeeUserName") == null)
			return Constants.mainPage;

		try {
			EmployeeDepositCheckForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			VisitorBean[] customerlist = visitorDAO.getAllCustomers();
			request.setAttribute("customerlist", customerlist);

			if (!form.isPresent()) {
				return Constants.employeeDepositCheckJsp;
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeDepositCheckJsp;
			}

			synchronized (this) {
				VisitorBean visitor = visitorDAO.read(form.getUserName());
				if (visitor == null) {
					errors.add("Customer does not exist");
					return Constants.employeeDepositCheckJsp;
				}
				transactionDAO.depositCheck(visitor.getVisitorId(), form.getAmountAsDouble() * 100);
			}

			request.setAttribute("success", "success");
			return Constants.employeeDepositCheckJsp;

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return Constants.employeeDepositCheckJsp;
		} catch (NumberFormatException e) {
			errors.add(e.getMessage());
			return Constants.employeeDepositCheckJsp;
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.employeeDepositCheckJsp;
		}
	}
}
