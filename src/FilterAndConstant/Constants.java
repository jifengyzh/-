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
			"admin_new_account.do",
			"logout.do"
	};
	
	public static final String[] visitorJSP  = new String[] {
			"visitor_buy_action.jsp",
			"visitor_comfirmation.jsp",
			"visitor_deposit_check.jsp",
			"visitor_deposit_check_success.jsp",
			"visitor_view_account.jsp",
			"visitor_change_pwd.jsp",
			"visitor-confirmation.jsp",
			"visitor_research_fund.jsp",
			"visitor_sell_fund.jsp"	
	};
	
	public static final String[] employeeJSP = new String[] {
			"admin_new_account.jsp",
			"admin_new_account_confirmation.jsp",
			"admin_main_panel.jsp"
	};
	
	public static final String logoutDo = new String("logout.do");
	public static final String mainPage = new String("index.jsp");
	public static final String errorJsp = new String("errors.jsp");
	
	public static final String visitorLogAction = new String("visitor_login.do");
	
	public static final String visitorBuyAction = new String("visitor_buy_action.do");
	public static final String visitorBuyJsp = new String("visitor_buy_action.jsp");
	public static final String visitorBuyConfirmJsp = new String("visitor_comfirmation.jsp");
	
	public static final String visitorDepositAction = new String("visitor_deposit_check.do");
	public static final String visitorDepositeJsp = new String("visitor_deposit_check.jsp");
	public static final String visitorDepositConfirmJsp = new String("visitor_deposit_check_success.jsp");
	
	
	public static final String visitorViewAccountAction = new String("visitor_view_account.do");
	public static final String visitorViewFundAction = new String("visitor_view_fund_price_history.do");
	public static final String visitorViewAccountJsp = new String("visitor_view_account.jsp");
	
	public static final String visitorChangePwdAction = new String("visitor_change_pwd.do");
	public static final String visitorChangePwdJsp = new String("visitor_change_pwd.jsp");
	public static final String visitorChangePwdConfirmJsp = new String("visitor-confirmation.jsp");
	
	public static final String visitorResearchFundAction = new String("visitor_research_fund.do");
	public static final String visitorResearchFundJsp = new String("visitor_research_fund.jsp");
	
	public static final String visitorSellAction = new String("visitor_sell_fund.do");
	public static final String visitorSellJsp = new String("visitor_sell_fund.jsp");
	
	public static final String visitorViewTransHistoryAction= new String("visitor_view_transaction_history.do");
	public static final String visitorViewTransHistoryJsp = new String("visitor_view_transaction_history.jsp");
	
	
	
	public static final String employeeMainPanelJsp = new String("employee_main_panel.jsp");
	public static final String employeeCreateCustomerAccAction = new String("employee_create_customer_account.do");
	public static final String employeeCreateCustomerAccJsp = new String("employee_create_customer_account.jsp");
	public static final String employeeCreateAccConfirmJsp = new String("employee_create_account_confirmation.jsp");
	public static final String employeeCreateEmployeeAccAction = new String("employee_create_employee_account.do");
	public static final String employeeCreateEmployeeAccJsp = new String("employee_create_employee_account.jsp");
	public static final String employeeLoginAction = new String("employee_login.do");
	public static final String employeeChangePasswordAction = new String("employee_change_password.do");
	public static final String employeeChangePasswordJsp = new String("employee_change_password.jsp");
	
}
