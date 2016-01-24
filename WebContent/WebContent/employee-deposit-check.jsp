<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Deposit Check</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="employee-header.jsp" />

  <div class="container-fluid">
    <div class="row-fluid">
      <!--side bar-->
      <div class="col-sm-3">
        <jsp:include page="employee-manage-customers-sidebar.jsp" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">Deposit Check</li>
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
                <p>${errors}</p>
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
                <p>${success}</p>
                <a href="#">Return</a>
              </div>
            </div>
          </div>
        </c:if>

        <!--deposit check-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Deposit Check</h3>
          </div>
          <div class="panel-body">
            <form class="form-inline" role="form" method="post" action="employee_deposit_check.do">
              <h5 class="text-info">Customer Name<p id="customer-name"></p></h5>
              <h5 class="text-info">Balance: $ <p id="customer-balance"></p></h5>
              <label for="balance" id="customer-username">User Name</label>
              <input type="text" class="form-control" name="userName" />
              <label for="balance">Amount $</label>
              <input type="text" class="form-control" name="amount" />
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div>
        </div>

        <!--user list-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Customer List</h3>
          </div>
          <div class="panel-body">
            <table class="table">
              <thead>
                <tr>
                  <th>Customer#</th><th>User Name</th><th>Name</th><th>Balance</th><th></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <c:forEach var="customer" items="${customerlist}">
                    <td>${customer.visitorId} </td>
                    <td>${customer.userName}</td>
                    <td>${customer.firstName} ${customer.lastName}</td>
                    <td>${customer.availableCash} </td>
                    <td><button type="submit" class="btn btn-default" onclick="show()">Deposit</button></td>
                    <script>
                      function show()
                      {
                          document.getElementById("customer-username").value = ${customer.userName};
                          document.getElementById("customer-balance").value = ${customer.availableCash};
                          document.getElementById("customer-name").value = ${customer.firstName} + " " + ${customer.lastName};
                      }
                    </script>
                   </c:forEach>
                </tr>
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
