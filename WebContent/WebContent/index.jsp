<!--Author: Xiangfei Dong-->
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Carnegie Mutual Service</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script type="text/javascript">
    function OnSubmitForm(form) { 
      form.action = (form.usertype.checked == true)?'employee_login.do':'visitor_login.do'; 
      form.submit(); 
                } 
  </script>
</head>

<body>
  <!--nav bar-->
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">Carnegie Financial Service</a>
      </div>
    </div>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <div class="col-md-1">
      </div>
      <div class="col-md-5">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Login</h3>
          </div>
          <div class="panel-body">
            <form role="form" method="POST" action="visitor_login.do" onSubmit="return false;">
              <div class="form-group">
                <label for="exampleInputEmail1">User Name</label>
                <input type="text" class="form-control" name="userName" />
              </div>
              <div class="form-group"> 
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" name="password" />
              </div>
              <div class="checkbox">
                <label>
                  <input type="checkbox" name="usertype"/>Employee</label>
              </div> 
              <button type="submit" class="btn btn-default" onClick="OnSubmitForm(this.form);">Login</button>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-1">
        <p></p>
      </div>
      <div class="col-md-4">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title">Carnegie Financial Service</h3>
          </div>
          <div class="panel-body">
            <img src="logo.jpg" class="img-thumbnail" alt="Cinque Terre">
          </div>
        </div>
      </div>
      <div class="col-md-1">
      </div>
    </div>
  </div>


</body>
</html>
