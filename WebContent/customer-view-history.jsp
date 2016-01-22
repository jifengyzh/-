<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Transaction History</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- styles -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/datepicker.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="customer-header.jsp" />

  <div class="container-fluid">
    <div class="row-fluid">
      <!--side bar-->
      <div class="col-sm-3">
        <div class="panel panel-primary">
          <div class="panel-heading">
            <h3 class="panel-title">Manage Fund</h3>
          </div>
          <div class="panel-body">
            <a href="#">Buy Fund</a><br><br>
            <a href="#">Sell Fund</a><br><br>
            <a href="#">Request Check</a><br><br>
            <a href="#">View History</a><br><br>
            <a href="#">Reasearch Fund</a><br><br>
          </div>
      </div>
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">View History</li>
          </ul>
        </div>

        <!--error panel-->
        <c:if test="${not empty errors}">
          <div>
            <div class="panel panel-danger">
              <div class="panel-heading">
                <h3 class="panel-title">Warning!</h3>
              </div>
              <div class="panel-body">
                <p>User name and password do not match</p>
                <a href="#">Return</a>
              </div>
            </div>
          </div>
        </c:if>

        <!--success panel-->
        <c:if test="${not empty success}">
          <div>
            <div class="panel panel-success">
              <div class="panel-heading">
                <h3 class="panel-title">Success!</h3>
              </div>
              <div class="panel-body">
                <p>You have successfully buy</p>
                <a href="#">Return</a>
              </div>
          </div>
        </c:if>
        
        <!--buy fund-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Transaction History</h3>
          </div>
          <div class="panel-body">
            <table class="table">
              <thead>
                <tr>
                  <th>Transaction#</th><th>Fund#</th><th>Execute Date</th><th>Operation</th><th>Shares</th><th>Price</th><th>Amount</th><th>Status</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach var="transaction" items="${transactionBeans}">
                  <!--if trasction.executeDate is not equal to null, the trasaction has been processed-->
                  <c:if test="${not empty transaction.executeDate}">
                    <tr class="success">
                      <td>${transaction.transactionId}</td>
                      <td>${transaction.fundId}</td>
                      <td>${transaction.executeDate}</td>
                      <td>
                        <c:if test=${transaction.transactionType == 1}>Buy Fund</c:if>
                        <c:if test=${transaction.transactionType == 2}>Sell Fund</c:if>
                        <c:if test=${transaction.transactionType == 3}>Request Check</c:if>
                        <c:if test=${transaction.transactionType == 4}>Deposit Check</c:if>
                      </td>
                      <td>${transaction.shares}</td>
                      <td>${transaction.sharePrice}</td>
                      <td>${transaction.amount}</td>
                      <td>Processed</td>
                    </tr>
                  </c:if>
                  <!--if trasction.executeDate is equal to null, the trasaction is pending-->
                  <c:if test="${empty transaction.executeDate}">
                    <tr class="success">
                      <td>${transaction.transactionId}</td>
                      <td>${transaction.fundId}</td>
                      <td>-</td>
                      <td>
                        <c:if test=${transaction.transactionType == 1}>Buy Fund</c:if>
                        <c:if test=${transaction.transactionType == 2}>Sell Fund</c:if>
                        <c:if test=${transaction.transactionType == 3}>Request Check</c:if>
                        <c:if test=${transaction.transactionType == 4}>Deposit Check</c:if>
                      </td>
                      <td>${transaction.shares}</td>
                      <td>${transaction.sharePrice}</td>
                      <td>${transaction.amount}</td>
                      <td>Pending</td>
                    </tr>
                  </c:if>
                </c:forEach>
              </tbody>
            </table>
          </div>  
        </div>
      </div>
    </div>
  </div>

  <!--footer-->
  <jsp:include page="footer.jsp" />

</body>
</html>
