package FilterAndConstant;

public class Constants {
	public static final String[] visitorActions = new String[] {
			"visitor_buy_action.do",
			"visitor_deposit_check.do",
			"visitor_login.do",
			"visitor_view_account.do",
			"visitor_view_fund_price_history.do",
			"visitor_change_pwd.do",
			"visitor_research_fund.do",
			"visitor_sell_fund.do",
			"visitor_view_transaction_history.do",
			"logout.do"
			
	};
	public static final String[] employeeActions = new String[] {
			"employee_create_employee.do",
			"logout.do",
			"employee_create_customer_account.do",
			"employee_login.do",
			"employee_change_password.do",
			"employee_reset_customer_password.do",
			"employee_view_customer_account.do",
			"employee_view_customer_transaction_history.do",
			"employee_deposit_check.do",
			"employee_create_fund.do",
			"employee_transition_day.do"
			
	};
	
	public static final String[] visitorJSP  = new String[] {
			"customer-buy-fund.jsp",
			"customer-request-check.jsp",
			"customer-view-account.jsp",
			"customer-change-password.jsp",
			"customer-research-fund.jsp",
			"customer-sell-fund.jsp",
			"customer-view-history.jsp"
	};
	
	public static final String[] employeeJSP = new String[] {
			"employee-change-password.jsp",
			"employee-create-customer-account.jsp",
			"employee-create-employee-account.jsp",
			"employee-create-fund.jsp",
			"employee-deposit-check.jsp",
			"employee-index.jsp",
			"employee-reset-customer-password.jsp",
			"employee-transition-day.jsp",
			"employee-view-customer-account",
			"employee-view-customer-history"
	};
	
	public static final String logoutDo = new String("logout.do");
	public static final String mainPage = new String("index.jsp");
	
	public static final String visitorLogAction = new String("visitor_login.do");
	
	public static final String visitorBuyAction = new String("visitor_buy_action.do");
	public static final String visitorBuyJsp = new String("customer-buy-fund.jsp");
	
	public static final String visitorRequestAction = new String("visitor_deposit_check.do");
	public static final String visitorRequestJsp = new String("customer-request-check.jsp");
	
	public static final String visitorViewAccountAction = new String("visitor_view_account.do");
	public static final String visitorViewFundAction = new String("visitor_view_fund_price_history.do");
	public static final String visitorViewAccountJsp = new String("customer-view-account.jsp");
	
	public static final String visitorChangePwdAction = new String("visitor_change_pwd.do");
	public static final String visitorChangePwdJsp = new String("customer-change-password.jsp");
	
	public static final String visitorResearchFundAction = new String("visitor_research_fund.do");
	public static final String visitorResearchFundJsp = new String("customer-research-fund.jsp");
	
	public static final String visitorSellAction = new String("visitor_sell_fund.do");
	public static final String visitorSellJsp = new String("customer-sell-fund.jsp");
	
	public static final String visitorViewTransHistoryAction= new String("visitor_view_transaction_history.do");
	public static final String visitorViewTransHistoryJsp = new String("customer-view-history.jsp");
	
	public static final String employeeMainPanelJsp = new String("employee-index.jsp");
	public static final String employeeCreateCustomerAccAction = new String("employee_create_customer_account.do");
	public static final String employeeCreateCustomerAccJsp = new String("employee-create-customer-account.jsp");
	public static final String employeeCreateEmployeeAccAction = new String("employee_create_employee_account.do");
	public static final String employeeCreateEmployeeAccJsp = new String("employee-create-employee-account.jsp");
	public static final String employeeLoginAction = new String("employee_login.do");
	public static final String employeeChangePwdAction = new String("employee_change_password.do");
	public static final String employeeChangePwdJsp = new String("employee-change-password.jsp");
	public static final String employeeResetCustPwdAction = new String("employee_reset_customer_password.do");
	public static final String employeeResetCustPwdJsp = new String("employee-reset-customer-password.jsp");
	public static final String employeeViewCustAccAction = new String("employee_view_customer_account.do");
	public static final String employeeViewCustAccJsp = new String("employee-view-customer-account.jsp");
	public static final String employeeViewCustTransHistoryAction = new String("employee_view_customer_transaction_history.do");
	public static final String employeeViewCustTransHistoryJsp = new String("employee-view-customer-history.jsp");
	public static final String employeeDepositCheckAction = new String("employee_deposit_check.do");
	public static final String employeeDepositCheckJsp = new String("employee-deposit-check.jsp");
	public static final String employeeCreateFundAction = new String("employee_create_fund.do");
	public static final String employeeCreateFundJsp = new String("employee-create-fund.jsp");
	public static final String employeeTransitionDayAction = new String("employee_transition_day.do");
	public static final String employeeTransitionDayJsp = new String("employee-transition-day.jsp");
}
