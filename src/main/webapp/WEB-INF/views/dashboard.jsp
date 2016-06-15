<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  var values = ${dashboardResponse};
  var totalCount = ${totalCount};
  
  <c:set var="context" value="${pageContext.request.contextPath}" />
  var contextPath = "<%= request.getContextPath() %>";
  </script>
	<title>Welcome to Infogain</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
	<link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${context}/resources/css/app-custom.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
  <![endif]-->
</head>
<body id="appDashPage">
    <!-- Header Part -->
    <div class="app-header">
      <div class="container">
      	<div class="row">
      		<div class="col-md-2"><img src="${context}/resources/images/eDOC-logo.png"></div>
      		<div class="col-md-2 col-md-offset-8"><img src="${context}/resources/images/infogain-logo.png" class="pull-right"></div>
      	</div>
      </div>
    </div>

  <!-- Navigation Bar -->
  <nav class="navbar navbar-default main-menu">
      <div class="container">
      <div class="row">
      <!-- Mobile Menu Toggle Button -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mainMenu">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>

      <!-- Main Menu -->
      <div class="collapse navbar-collapse" id="mainMenu">
        <ul class="nav navbar-nav">
          <li class="active"><a href="dashboard">Home</a></li>
          <li><a href="register">Register New Employee</a></li>
          <li><a href="workflowstats">Work Flow Statistics</a></li>
          <li><a href="addrole">Add New Roles</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><span class="glyphicon glyphicon-user" aria-hidden="true"></span><%= session.getAttribute("userName") %></a></li>
          <li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
        </ul>
       
         
      </div>
      </div>
    </div>
  </nav>

    <!-- Content Area -->
    <div class="app-content">
    	<div class="container">
    		
        <div class="row">
      		<div class="col-md-12">
  	    			<div class="app-content-box">
                  <div class="app-panel-content">
                        
                          <form id="searchForm"  class="form-inline  pull-right">
                            <div class="form-group">
                              <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                                <input type="text" class="form-control" id="filter" name="searchText" placeholder="Employee Name">
                              </div>
                            </div>
                           <button id="search" type="button" class="btn btn-default glyphicon glyphicon-search" aria-label="Left Align">
                          
                          </button>
                          </form>
                     

                  </div>
              </div>
      		</div>
      </div>

      <div class="row">
            <div class="col-md-12">
              <div class="app-content-box recent-doc-list">
                 <div class="app-panel-header">
                  <h1 class="app-panel-title">Recent Docs Requests</h1>
                  
                  <!-- <div class="btn-group app-panel-btn-group">

                  <button type="button" class="btn btn-default">
                      <span class="glyphicon glyphicon-filter"></span>
                  </button>

                  <button type="button" class="btn btn-default">
                      <span class="glyphicon glyphicon-calendar"></span>
                  </button>

      
      </div> -->
                 
                 </div>

                <div class="filters-sections">
                    <div class="row">
                    <div class="col-sm-1">
                    	<div class="label-text">Filter</div>
                    </div>
                    <div class="col-sm-3">
                    	<select class="form-control input-sm filer1" id="employeeFilter">
                    		<option>Future Employees</option>
                    		<option>Existing Employees</option>
                    	</select>
                    </div>
                    <div class="col-sm-3">
                    	<select class="form-control input-sm filer1" id="bgcFilter">
                    		<option>BGC Not Initiated</option>
                    		<option>BGC Initiated</option>
                    		<option>BGC Approved</option>
                    	</select>
                    </div>
                    
                    
                     <!--  <div class="col-md-6">
                          <div class="label-text">Last 30 Days</div>
                      </div>
                      <div class="col-md-6">
                        <form class="form-horizontal">
                         <div class="row">
                          <label class="col-sm-1 control-label text-right" for="fromDate">From</label>
                          <div class="col-md-4">
                            <input  type="date" class="form-control input-sm" id="fromDate" disabled="disabled">
                          </div>
                            <label class="col-sm-1 control-label text-right" for="toDate">To</label>
                          <div class="col-md-4">
                            <input  type="date" class="form-control input-sm" id="toDate" disabled="disabled">
                          </div>
                            <div class="col-md-2">
                            <button class="btn btn-default btn-sm" id="searchByDate" type="button" disabled="disabled">View</button>
                          </div>
                        </div>
                        </form>
                      </div> -->
                    </div>
                 </div>

                 <div class="app-panel-content">


                      <div class="doc-requests">
                        <table class="table table-bordered table-condensed table-hover" id="preEmpTable">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th class='text-center'>Name</th>
                           
                             <th class='text-center'>RMG</th>
                            <th class='text-center'>RMG Admin</th>
                            <th class='text-center'>HR</th>
                            <th class='text-center'>BGC</th>
                            <th class='text-center'>View/Edit</th>
                            <th class='text-center'>Download</th>
                            <th class='text-center'>BGC Email</th>
                            <th class='text-center'>Employee ID</th>
                            <th class='text-center'>Employee Email</th>
                            <th class="text-center" >Select All &nbsp <input type="checkbox" id="selectAll"></th>
                          
                          </tr>
                        </thead>
                        <tbody>
                         <%--  <tr>
                            <td>1</td>
                            <td>Chetan Singla</td>
                            <td>2121002</td>
                            <td>Assistant Consultant</td>
                            <td>01/09/2014</td>
                            <td>Ravinder Dua</td>
                            <td class="text-center">05/10</td>
                            <td class="text-center"><img src="${context}/resources/images/valid-icon.png"></td>
                            <td class="text-center"><img src="${context}/resources/images/valid-icon.png"></td>
                            <td>
                            <button type="button" class="btn  btn-link btn-xs" onclick="window.location.href='landingpage'">
                              <span class="glyphicon glyphicon-pencil"></span>
                            </button>
                            </td>
                          </tr> --%>
                          
                        </tbody>
                      </table>
                  </div>

                  <div class="row">
                    <div class="col-md-2">
                      <button id="previous" class="btn btn-default btn-sm" type="button">Previous</button>

                      <div id="pages" class="btn-group" role="group">
                       
                        
                      </div>

                      <button id="next"  class="btn btn-default btn-sm" type="button">Next</button>
                    </div>

                    <div class="col-md-5">
                      <ul class="list-inline icon-notation pull-right">
                          <li class="approve"><img src="${context}/resources/images/3.png"> Approved</li>
                          <li class="pending"><img src="${context}/resources/images/2.png"> Initiated</li>
                          <li class="rejected"><img src="${context}/resources/images/1.png"> Rejected</li>
                          <li class="not-uploaded"><img src="${context}/resources/images/0.png"> Not Initiated</li>
                      </ul>
                    </div>
                  	<div class="col-md-5 text-right">
                  	    <button class="btn btn-default btn-sm" type="button" data-toggle="modal" id="rmgAdminReject">Reject RMG Admin</button>
                        <button class="btn btn-default btn-sm" type="button" data-toggle="modal" id="initiateBgc">Initiate BGC</button>
                        <button class="btn btn-default btn-sm" type="button" data-toggle="modal" id="approveBgc">Approve BGC</button>
                        <button class="btn btn-default btn-sm" type="button" data-toggle="modal" id="makeEmployee">Make Employee</button>
                    	<button class="btn btn-default btn-sm" type="button" data-toggle="modal" id="sendWelcomeMail">Send Welcome Mail</button>
                    </div>
                     
                  </div>
                  
                 </div>  
                 </div>

              </div>
            </div>
      </div>
    </div>

    <!-- Footer Part -->
    <div class="app-footer">
      <div class="container">
      	<div class="row">
      		<div class="col-md-12">Copyright © 2015 eDOC. All rights reserved by infogain</div>
      	</div>
      </div>
    </div>
   
    
 <!-- Model : mail -->
    <div class="modal fade"  id="mailData" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Send Mail</h4>
          </div>
          <div class="modal-body">
          <h3 class ="text-center"> Multiple Emails must be separated with a semicolon(;).</h3>
              
              <form class="form-horizontal"  id="mailForm"  method="post" >
                <div class="form-group">
                  <div class="col-sm-12">
                   <input type="email" name="to" class="form-control input-sm" placeholder="To" required="required">
                    </div>
                  </div>
                   <div class="form-group">
                   <div class="col-sm-12">
                   <input type="text" name="subject" class="form-control input-sm" placeholder="Subject" required="required">
                    <input type="hidden" name="applicationId" value="0">
                   </div>
                   </div>
                   <div class="form-group">
                     <div class="col-sm-12">
                    <input class="btn btn-sm btn-default" id="sendMail" type="button"  value="Send Mail"/>
                  </div>
                  </div>
                   </form>
                </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <!-- Model : Rejection Reason -->
    <div class="modal fade"  id="rejectionReason" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Reason for Rejection</h4>
          </div>
          <div class="modal-body">
          <!-- <h3 class ="text-center"> Multiple Emails must be separated with a semicolon(;).</h3> -->
              
              <form class="form-horizontal"  id="reasonForm"  method="post" >
                <div class="form-group">
                  <div class="col-sm-12">
                   <!-- <input type="email" name="to" class="form-control input-sm" placeholder="To" required="required"> -->
                   <textarea class="form-control" rows="3" id="reason" name="reason"></textarea>
                    </div>
                  </div>
                   <!-- <div class="form-group">
                   <div class="col-sm-12">
                   <input type="text" name="subject" class="form-control input-sm" placeholder="Subject" required="required">
                    <input type="hidden" name="applicationId" value="0">
                   </div>
                   </div> -->
                   <div class="form-group">
                     <div class="col-sm-12">
                    <input class="btn btn-sm btn-default" id="rmgAdminRejectButton" type="button"  value="RMG Admin Reject"/>
                  </div>
                  </div>
                   </form>
                </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- jQuery -->
    <script src="${context}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>
      <!-- custom alert and prompt js -->
     <script src="${context}/resources/js/bootbox.min.js"></script>
    
    <script src="${context}/resources/js/dashboard.js"></script>
</body>
</html>