<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Transition Day</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="employee-header.jsp" />

  <div class="container-fluid">
    <div class="row-fluid">
      <!--side bar-->
      <div class="col-sm-3">
        <jsp:include page="employee-manage-fund-sidebar.jsp" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">Transition Day</li>
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
                <p>${error}</p>
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

        <!--transition day-->
        <div class="row">
          <div class="col-sm-6">
            <div class="panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Transition Day</h3>
              </div>
              <div class="panel-body">
                <form method="post" action="employee-transitionday.do">
                  <div class="row-fluid">
                    <div >
                      <h5 class="text-info">Last Transition Day: </h5><b>${lastdate}</b>
                    </div>
                    <div>
                      <h5 class="text-info">Select Transition Date: </h5>
                      <input type="date" class="form-control" name="date" />
                    </div>
                  </div><br>

                  <table class="table">
                    <thead>
                      <tr>
                        <th>Fund ID</th><th>Name</th><th>Symbol</th><th>Closing Price</th>
                      </tr>
                    </thead>
                    <form method="post" action="employee_transition_day.do" >
                      <tbody>
                        <c:forEach var="fund" items="${fundList}">
                          <input type="hidden" name="fundId" value="${fund.fundId}" />
                          <tr>
                            <td>${ fund.fundId }</td>
                            <td>${ fund.name }</td>
                            <td>${ fund.symbol }</td>
                            <td><input type="text" name="price" /></td>
                          </tr>
                        </c:forEach>
                      </tbody>
                    </form>
                  </table>
                  <div>
                    <button type="submit" class="btn btn-default">Submit</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="col-sm-6">
          </div>
        </div>
      </div>
    </div>
  </div>

  <!--footer-->
  <jsp:include page="footer.jsp" />

</body>
</html>
