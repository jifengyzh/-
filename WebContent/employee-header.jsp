  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Carnegie Financial Service</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Employees <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="employee_change_password.do">Change My Password</a></li>
            <li><a href="employee_create_employee_account.do">Create Employee Account</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Customers <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="employee_create_customer_account.do">Create Customer Account</a></li>
            <li><a href="employee_reset_customer_password.do">Reset Customer Password</a></li>
            <li><a href="employee_view_customer_account.do">View Customer Account</a></li>
            <li><a href="employee_view_customer_transaction_history.do">View Customer History</a></li>
            <li><a href="employee_deposit_check.do">Deposit Check</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Fund <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="employee_create_fund.do">Create Fund</a></li>
            <li><a href="employee_transition_day.do">Transition Day</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="employee-index.jsp"><span class="glyphicon glyphicon-user"></span>${firstname} ${lastname}</a></li>
        <li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </nav>