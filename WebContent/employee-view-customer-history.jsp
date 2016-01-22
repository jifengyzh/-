<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Create Customer Account</title>
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
        <jsp:include page="employee-manage-customers-sidebar.jsp" />
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
          </div>
        </c:if>
        
        <div class="row">
          <div class="col-sm-8">
            <!--customer's info-->
            <div class="panel panel-default">
                <div class="panel-heading">
                  <h3 class="panel-title">Customer's Information</h3>
                </div>
                <div class="panel-body">
                  <p>
                    <h5 class="text-info">Name: </h5>
                    <p>   Mike Shamos</p>
                    <h5 class="text-info">Address</h5>
                    <p>   417 South Craig Street<br>    Pittsburgh, PA 15213<br></p>
                  </p>
                </div>
              </div>
            <!--history list-->
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Customer's Transaction History</h3>
              </div>
              <div class="panel-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Date</th><th>Operation</th><th>Fund Name</th><th>Shares</th><th>Price</th><th>Value</th><th>Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr class="success">
                      <td>2016-01-01</td><td>Buy</td><td>Google</td><td>123</td><td>2</td><td>246</td><td>Precessed</td>
                    </tr>
                    <tr class="success">
                      <td>2016-01-01</td><td>Buy</td><td>Yahoo</td><td>123</td><td>2</td><td>246</td><td>Precessed</td>
                    </tr>
                    <tr class="info">
                      <td>2016-01-02</td><td>Buy</td><td>Amazon</td><td>123</td><td>2</td><td>246</td><td>Pending</td>
                    </tr>
                  </tbody>
                </table>
              </div>  
            </div>
          </div>

          <div class="col-sm-4">
            <!--user list-->
              <div class="panel panel-default">
                <div class="panel-heading">
                  <h3 class="panel-title">Customer List</h3>
                </div>
                <div class="panel-body">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>ID</th><th>Username</th><th>Name</th><th></th>
                    </thead>
                    <tbody>
                      <tr>
                        <td>1</td><td>cmudxf</td><td>Xiangfei Dong</td><td><a href="#">View</a></td>
                      </tr>
                      <tr>
                        <td>2</td><td>wthouse</td><td>Barack Obama</td><td><a href="#">View</a></td>
                      </tr>
                      <tr>
                        <td>3</td><td>bjxjp</td><td>Jinping Xi</td><td><a href="#">View</a></td>
                      </tr>
                    </tbody>
                  </table>
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
