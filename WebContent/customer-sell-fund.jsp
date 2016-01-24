<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="databean.PositionBean" %>  

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Sell Fund</title>
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
        <jsp:include page="customer-manage-fund-sidebar.jsp" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">Sell Fund</li>
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
              	<c:forEach var="error" items="${errors}">
                	<p>${error}</p>
                </c:forEach>
              </div>
            </div>
          </div>
        </c:if>

        <!--success panel-->
        <c:if test="${not empty alert}">
          <div>
            <div class="panel panel-success">
              <div class="panel-heading">
                <h3 class="panel-title">Success!</h3>
              </div>
              <div class="panel-body">
                <p>${alert}</p>
              </div>
            </div>
          </div>
        </c:if>
        
        <!--sell fund-->
        <div class="row">
          <div class="col-sm-8">
            <div class="panel panel-default">
              <div class="panel-heading">  
                <h3 class="panel-title">Your Fund List</h3>
              </div>
              <div class="panel-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Fund Name</th><th>Symbol</th><th>Shares</th><th>Available Shares</th><th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="fund" items="${customerFundBeans}">
                      <tr>
                        <td>${fund.name}</td>
                        <td>${fund.symbol} </td>
                        <td>${fund.shares}</td>
                        <td>${fund.AvailableShares} </td>
                        <td><button onclick="document.getElementById('fund-name').value = '${fund.fundName}';">Deposit</button></td>
                      </tr>
                   </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <div class="col-sm-4">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Sell Fund</h3>
              </div>
              <div class="panel-body">
                <form class="form-inline" role="form" method="post" action="visitor_buy_action.do">
                  <div class="form-group"> 
                    <label for="balance">Fund Name</label>
                    <input type="text" class="form-control" id="fund-name" name="fundName" /><br><br>
                  </div>
                  <div class="form-group"> 
                    <label for="balance">Number of Shares </label>
                    <input type="text" class="form-control" name="shares" /><br><br>
                  </div>
                  <div>
                    <button type="submit" class="btn btn-default">Submit</button>
                  </div>
                </form>
                <br><br>
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
