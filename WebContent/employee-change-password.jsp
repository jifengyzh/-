<!--Author: Xiangfei Dong-->
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Change My Password</title>
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
        <jsp:include page="employee-manage-employees-sidebar.jsp" />
      </div>

      <!--content-->
      <div class="col-sm-9">
        <!--current path-->
        <div>
          <ul class="breadcrumb">
            <li><a href="#"> <i class="icon-home"></i>Home</a></li>
            <li class="active">Change My Password</li>
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
        <c:if test="${not empty message}">
          <div>
            <div class="panel panel-success">
              <div class="panel-heading">
                <h3 class="panel-title">Success!</h3>
              </div>
              <div class="panel-body">
                <p>${message}</p>
                <a href="#">Return</a>
              </div>
          </div>
        </c:if>
        
        <!--change password-->
        <div class="row">
          <div class="col-sm-6">
            <div class="panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Change My Password</h3>
              </div>
              <div class="panel-body">
                <form method="post" action="employee_change_password.do" >
                  <label>Current Password</label>
                  <input type="password" class="form-control" name="oldPassword" /><br>
                  <label>New Password</label>
                  <input type="password" class="form-control" name="newPassword" /><br>
                  <label>Confirm</label>
                  <input type="password" class="form-control" name="confirmedPassword" /><br>
                  <button type="submit" class="btn btn-default">Submit</button>
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
