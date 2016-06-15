<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var role = "${user.roles[0]}";
	<c:set var="role" value="${user.roles[0]}" />
	
</script>

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
      		<div class="col-md-2"><img src="${context}/resources/images/eDOC-logo.png"></div>
      		<div class="col-md-2 col-md-offset-8"><img src="${context}/resources/images/infogain-logo.png" class="pull-right"></div>
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
	    			 	<div class="col-md-5"><img src="${context}/resources/images/welcome-image.jpg"></div>

	    			 	<div class="col-md-6">
		    			 	<div class="register-content">
		    			 	 <div class="row">
		    			 	<hgroup class="header-text">
		    			 		<h2 class="title">Registration</h2>
		    			   		<h2 class="sub-title">Please enter the information below. Multiple Emails must be separated with a ";".</h2>
		    			   	</hgroup>
							</div>
							
	 						<form id="registrationForm"  class="form-horizontal">
	 						 <div class="row">
							 <div class="form-group">
							    <!-- <div class="col-md-6">
							      <input type="text" class="form-control" id="username" name="username" placeholder="Username">
							    </div>
							   -->
							
							    <div class="col-md-6">
							      <input type="text" class="form-control" id="email" name="email" placeholder="Email" required="required">
							    </div>
							    </div>
							  </div>
							   <div class="row">
							 <div class="form-group">
							    <div class="col-md-6">
							      <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required="required">
							    </div>
							  
							
							    <div class="col-md-6">
							      <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required="required">
							    </div>
							    </div>
							  </div>
							  <!-- <div class="row">
							  <div class="form-group">
							    <div class="col-md-6">
							      <input type="password" class="form-control" id="password" name="password" placeholder="Password">
							    </div>
							  			  
							    <div class="col-md-6">
							      <input type="password" class="form-control" id="rePassword" name="rePassword" placeholder="Re-enter Password">
							    </div>
							  </div>
							  </div> -->
							  
							 <!--  <div class="row">
							  <div class="form-group">
							    <div class="col-md-12">
							      <input type="text" class="form-control" id="contact" name="contact" placeholder="Contact">
							    </div>
							    </div>
							   <div class="form-group">			  
							    <div class="col-md-12">
							      <input type="text" class="form-control" id="address" name="address" placeholder="Address">
							    </div>
							  </div>
							  </div> -->
							  <!-- <div class="row">
							 <div class="form-group">
							    <div class="col-md-3">
							      <input type="text" class="form-control" id="zip" name="zip" placeholder="ZIP">
							    </div>
							   
							    </div>
							  </div> -->
							  <div class="form-group">
							  <div class="row">
							    <div class="col-md-4">
							      <input type="submit"  id="register" class="btn btn-primary uppercase" value="Register" />
							    </div>
							    <sec:authorize access="hasAnyRole('ROLE_RMG')">
							    	<c:if test="${role == 'ROLE_RMG'}">
									     <div class="col-md-3 col-md-offset-5">
									      <a href="rmgdashboard" class="btn btn-link">Go to Dashboard.</a>
									     </div>
								    </c:if>
							    </sec:authorize>
							    <sec:authorize access="hasAnyRole('ROLE_RMG_ADMIN')">
							    <c:if test="${role == 'ROLE_RMG_ADMIN'}">
								     <div class="col-md-3 col-md-offset-5">
								      <a href="rmgadmindashboard" class="btn btn-link">Go to Dashboard.</a>
								    </div>
								</c:if>
							    </sec:authorize>
							    
							    <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
							    <c:if test="${role == 'ROLE_ADMIN'}">
								     <div class="col-md-3 col-md-offset-5">
								      <a href="dashboard" class="btn btn-link">Go to Dashboard.</a>
								    </div>
								</c:if>
							    </sec:authorize>
							    
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
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/bootbox.min.js"></script>
    <script type="text/javascript" src="${context}/resources/js/register.js"></script>
</body>
</html>