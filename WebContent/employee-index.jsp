<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Create Fund</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
  <!-- nav bar-->
  <jsp:include page="employee-header.jsp" />

  <br><br><br><br><br><br><br><br>

  <div class="container-fluid">
  <div class="row">
    <div class="col-md-3">
      <div class="panel panel-primary">
        <div class="panel-heading">
        </div>
        <div class="panel-body">
          <br><br>
          <form action="employee_create_customer_account.do">
            <button type="submit" class="btn btn-primary btn-lg btn-block">Create Customer</button>
          </form>
          <br>
        </div>
      </div> 
    </div>
    <div class="col-md-3">
      <div class="panel panel-success">
        <div class="panel-heading">
        </div>
        <div class="panel-body">
          <br><br>
          <form action="employee_create_employee_account.do">
            <button type="submit" class="btn btn-success btn-lg btn-block">Create Employee</button>
          </form>
          <br>
        </div>
      </div> 
    </div>
    <div class="col-md-3">
      <div class="panel panel-info">
        <div class="panel-heading">
        </div>
        <div class="panel-body">
          <br><br>
          <form action="employee_create_fund.do">
            <button type="submit" class="btn btn-info btn-lg btn-block">Create Fund</button>
          </form>
          <br>
        </div>
      </div> 
    </div>
    <div class="col-md-3">
      <div class="panel panel-warning">
        <div class="panel-heading">
        </div>
        <div class="panel-body">
          <br><br>
          <form action="employee_transition_day.do">
            <button type="submit" class="btn btn-warning btn-lg btn-block">Transition Day</button>
          </form>
          <br>
        </div>
      </div> 
    </div>
  </div>
</div>

  <!--footer-->
  <jsp:include page="footer.jsp" />

</body>
</html>
