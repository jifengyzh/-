<!--Author: Xiangfei Dong-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Reasearch Fund</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

  <script type="text/javascript">
  // Load the Visualization API and the piechart package.
  google.load('visualization', '1.0', {
    'packages' : [ 'corechart' ]
  });

  // Set a callback to run when the Google Visualization API is loaded.
  google.setOnLoadCallback(drawColumnChart);
  google.setOnLoadCallback(drawLineChart);


  var dataList = new Array();
  
  <% 
  FundPriceHistoryBean[] fundPriceHistoryList = (FundPriceHistoryBean[]) request.getAttribute("fundPriceList");
  if (fundPriceHistoryList != null){
  %>
    var title_lineChart = new Array();
    var dataList_lineChart = new Array(); 
  <%
    for (int i=0; i< fundPriceHistoryList.length; i++) { 
  %>
  
    title_lineChart[<%= i %>] = "<%= fundPriceHistoryList[i].getPrice_date() %>";
    dataList_lineChart[<%= i %>] = "<%= fundPriceHistoryList[i].getPrice() %>";

  <%  }
  } %>  
   
  function drawLineChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('date', 'Date');
    data.addColumn('number', 'Price');
    for (var i = 0; i < title_lineChart.length; i++){
      if(dataList_lineChart[i] != "null"){
        var tmp = title_lineChart[i].split("-");
        
        var year = parseInt(tmp[0]);
        var month = parseInt(tmp[1]);
        var day = parseInt(tmp[2]);
        
        data.addRow([new Date(year,month - 1,day), parseFloat(dataList_lineChart[i])]); 
        console.log(year + "" + month + day);
        console.log(new Date(year,month,day));
      }
    }
    

    // Set chart options
    var options = {
      'title' : 'Performance History',
      'width' : 800,
      'height' : 600
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.LineChart(document
        .getElementById('linechart_div'));
    chart.draw(data, options);
  }
</script>

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
            <li class="active">Research Fund</li>
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

        <!--research fund-->
        <div class="row">
          <div class="col-sm-8">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Research Fund</h3>
              </div>
              <div class="panel-body">
                <h5 class="text-info">Ticker: </h5><p>GOGL</p>
                <h5 class="text-info">Name: </h5><p>Google<p>

                <div id="linechart_div"></div>

              </div>
            </div>
          </div>

          <div class="col-sm-4">
            <div class="panel panel-default">
              <div class="panel-heading">  
                <h3 class="panel-title">Fund List</h3>
              </div>
              <div class="panel-body">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Ticker</th><th>Name</th><th>Price</th><th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>GOGL</td><td>Google</td><td>250</td><td><a href="#">Research</a></td>
                    </tr>
                    <tr>
                      <td>GOGL</td><td>Google</td><td>250</td><td><a href="#">Research</a></td>
                    </tr>
                    <tr>
                      <td>GOGL</td><td>Google</td><td>250</td><td><a href="#">Research</a></td>
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
