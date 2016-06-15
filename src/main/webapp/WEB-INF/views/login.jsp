<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="context" value="<%= request.getContextPath() %>"></c:set>
	<title>Welcome to Infogain</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">

	<!-- Custom CSS -->
	<link rel="stylesheet" href="resources/css/app-custom.css">
</head>
<body id="appLoginPage">
    <!-- Header Part -->
    <div class="app-header">
      <div class="container">
      	<div class="row">
      		<div class="col-md-2"><img src="resources/images/eDOC-logo.png"></div>
      		<div class="col-md-2 col-md-offset-8"><img src="resources/images/infogain-logo.png" class="pull-right"></div>
      	</div>
      </div>
    </div>

    <!-- Content Area -->
    <div class="app-content-login">
    	<div class="container">
    		<div class="row">
    			<div class="col-md-12">
	    			<div class="app-content-box login-section">

	    			 <div class="row">
	    			 	<div class="col-md-5"><img src="resources/images/welcome-image.jpg"></div>

	    			 	<div class="col-md-6">
		    			 	<div class="login-content">
		    			 	<hgroup class="header-text">
		    			   		<h1 class="title">Welcome to eDOC HR Desk</h1>
		    			   		<h2 class="sub-title">Please enter the information below.</h2>
		    			   	</hgroup>

	 						<form id="loginForm" action="j_spring_security_check" class="form-horizontal" method="POST">

							  <div class="form-group">
							    <div class="col-md-12">
							      <input type="text" class="form-control login-user" id="userName" name="username" placeholder="User ID">
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="col-md-12">
							      <input type="password" class="form-control login-password" id="userPassword" name="password" placeholder="Password">
							    </div>
							  </div>
							  <div class="form-group">
							    <div class="col-md-12">
							    	<c:if test="${param['auth'] == 'fail'}">
							    		<div class="label-text msg">Wrong User ID Or Password</div>
							    	</c:if>
							    	<c:if test="${param['msg'] == 'invalidRole'}">
							    		<div class="label-text msg">Invalid Role</div>
							    	</c:if>
							    </div>
							  </div>
							  
							   
							  <div class="form-group">
							   <div class="col-md-4">
							    	<select class="form-control input-sm" id="selectedRole" name="selectedRole">
			                    		<option value="ROLE_USER">User</option>
			                    		<option value="ROLE_RMG">RMG</option>
			                    		<option value="ROLE_RMG_ADMIN">RMG Admin</option>
			                    		<option value="ROLE_ADMIN">HR</option>
                    				</select>
							    </div>
							    <div class="col-md-4">
							      <input type="button" id="login" class="btn btn-primary uppercase" value="submit">
							    </div>
							  </div>
							</form>
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
    <script src="${context}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/login.js"></script>
</body>
</html>