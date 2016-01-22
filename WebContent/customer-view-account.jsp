<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>View Account</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="customer-header.jsp" />

  <div class="container-fluid">
    <div class="row-fluid">
      <!--side bar-->
      <div class="col-sm-3">
        <jsp:include page="customer-account-sidebar" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">View Account</li>
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
          </div>
        </c:if>
        
        <!--account info-->
        <div class="container-fluid">
          <div class="row">
            <div class="col-md-7">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h3 class="panel-title">Your Funds</h3>
                </div>
                <div class="panel-body">
                  <h5 class="text-info">Last Trading Day: </h5><p>${visitor.lastTradingDate}</p>
                  <h5 class="text-info">Cash Balance: $</h5><p>${visitor.cash}<p>
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Fund Name</th><th>Fund Symbol</th><th>Shares</th><th>Price</th><th>Value</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="fund" items="${customerFundBeans}">
                        <tr>
                          <td>${fund.name} </td>
                          <td>${fund.symbol}</td>
                          <td>${fund.shares} </td>
                          <td>${fund.price} </td>
                          <td>${fund.value} </td>
                        </tr>
                     </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>

            <div class="col-md-5">
              <div class="panel panel-default">
                <div class="panel-body">
                  <button type="button" class="btn btn-primary">Buy Fund</button>
                  <button type="button" class="btn btn-primary">Sell Fund</button>
                  <button type="button" class="btn btn-primary">View History</button>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h3 class="panel-title">Personal Information</h3>
                </div>
                <div class="panel-body">
                  <p>
                    <h5 class="text-info">Name: </h5>
                    <p>&nbsp;&nbsp;${visitor.firstName} ${visitor.lastName}</p>
                    <h5 class="text-info">Address</h5>
                    <p>&nbsp;&nbsp;${visitor.addrLine1}<br>&nbsp;&nbsp;${visitor.addrLine1}<br>&nbsp;&nbsp;${visitor.zip} ${visitor.city}</p>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!--footer-->
  <jsp:include page="footer.jsp" />

</body>
</html>
