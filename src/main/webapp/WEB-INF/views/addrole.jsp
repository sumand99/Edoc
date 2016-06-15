<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
var values = ${userList};
  var rmgList = ${rmgList};
  var rmgAdminList = ${rmgAdminList};
  var hrList = ${hrList};
  var role = "${user.roles[0]}";
	<c:set var="role" value="${user.roles[0]}"/>
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
         <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
         <c:if test="${role == 'ROLE_ADMIN'}">
         	 <li class="active"><a href="dashboard">Home</a></li>
         </c:if>
         </sec:authorize>
         <sec:authorize access="hasAnyRole('ROLE_RMG')">
         <c:if test="${role == 'ROLE_RMG'}">
         	 <li class="active"><a href="rmgdashboard">Home</a></li>
         </c:if>
         </sec:authorize>
         <sec:authorize access="hasAnyRole('ROLE_RMG_ADMIN')">
         <c:if test="${role == 'ROLE_RMG_ADMIN'}">
         	 <li class="active"><a href="rmgadmindashboard">Home</a></li>
         </c:if>
         </sec:authorize>
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
    		
       
      		
     

      
      <div class="well well-lg" >
      <div class="row">
             <div class="col-md-4">
            <div class="input-group" >
  <input id="employee-id" type="text" class="form-control" placeholder="Employee's username" aria-describedby="basic-addon2">
  <span class="input-group-addon" id="basic-addon2">@infogain.com</span>
</div>
                 </div>
       <div class="col-md-4">
       <select class="form-control" id="role">
        <option value="4">RMG</option>
        <option value="5">RMG ADMIN</option>
        <option value="1">HR</option>
      </select>
      </div>
      <div class="col-md-2">
      <button id="add-role" type="button" class="btn btn-primary">Add</button>
</div>
</div>
</div>

<div class="well well-lg" >
<div class="row">
<div class="col-md-4">
RMG
</div>
<div class="col-md-4">
RMG Admin
</div>
<div class="col-md-4">
HR
</div>
</div>
<div class="row">
<div class="col-md-4">
<select class="form-control" id="rmg">
        
      </select>
</div>
<div class="col-md-4">
<select class="form-control" id="rmgAdmin">
        
      </select>
</div>
<div class="col-md-4">
<select class="form-control" id="hr">
        
      </select>
</div>
</div>

<div class="row">
<div class="col-md-4">
<button class="btn btn-default btn-sm" type="button" id="remove-rmg">Remove</button>
</div>
<div class="col-md-4">
<button class="btn btn-default btn-sm" type="button" id="remove-rmg-admin">Remove</button>
</div>
<div class="col-md-4">
<button class="btn btn-default btn-sm" type="button" id="remove-hr">Remove</button>

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

    <!-- jQuery -->
    <script src="${context}/resources/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>
      <!-- custom alert and prompt js -->
     <script src="${context}/resources/js/bootbox.min.js"></script>
     <script src="https://code.jquery.com/jquery-1.4.4.min.js"></script>
     
     <script type="text/javascript" src="https://code.jquery.com/ui/1.8.9/jquery-ui.min.js"></script>
    <script src="${context}/resources/js/addrole.js"></script>
</body>
</html>