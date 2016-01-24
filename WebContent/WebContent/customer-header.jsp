<nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="visitor_view_account.do">Carnegie Financial Service</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Manage Fund <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="visitor_buy_action.do">Buy Fund</a></li>
            <li><a href="visitor_sell_fund.do">Sell Fund</a></li>
            <li><a href="visitor_deposit_check.do">Request Check</a></li>
            <li><a href="visitor_view_transaction_history.do">View History</a></li>
            <li><a href="visitor_research_fund.do">Reserach Fund</a></li>
          </ul>
        </li>
        <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Account <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="visitor_view_account.do">View Account</a></li>
            <li><a href="visitor_change_pwd.do">Change Password</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="customer-index.jsp"><span class="glyphicon glyphicon-user"></span> ${visitor.firstName} ${visitor.lastName}</a></li>
        <li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
    </div>
  </nav>