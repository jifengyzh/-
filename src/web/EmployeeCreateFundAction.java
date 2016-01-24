package web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import FilterAndConstant.Constants;
import databean.FundBean;
import formbean.EmployeeCreateFundForm;
import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

public class EmployeeCreateFundAction extends Action {

	private FormBeanFactory<EmployeeCreateFundForm> formBeanFactory = FormBeanFactory
			.getInstance(EmployeeCreateFundForm.class);

	private FundDAO fundDAO;

	public EmployeeCreateFundAction(Model model) {
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return Constants.employeeCreateFundAction;
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		request.setAttribute("success", null);
		try {
			EmployeeCreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			if (!form.isPresent()) {
				return Constants.employeeCreateFundJsp;
			}

			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return Constants.employeeCreateFundJsp;
			}
			// Validation Check
			FundBean fund = fundDAO.readFundName(form.getFundName());
			FundBean symbol = fundDAO.readFundSymbol(form.getSymbol());

			if (fund != null) {
				errors.add("Existing Fund Name");
				return Constants.employeeCreateFundJsp;
			}
			if (symbol != null) {
				errors.add("Existing Symbol");
				return Constants.employeeCreateFundJsp;
			}

			fund = new FundBean();
			fund.setSymbol(form.getSymbol());
			fund.setName(form.getFundName());
			fundDAO.create(fund);

			request.setAttribute("success", "success");
			return Constants.employeeCreateFundJsp;

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return Constants.employeeCreateFundJsp;
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constants.employeeCreateFundJsp;
		}
	}
}
