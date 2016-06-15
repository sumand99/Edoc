<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
   <c:set var="context" value="${pageContext.request.contextPath}" />
  var contextPath='<%= request.getContextPath()%>'; 
  var role = "${user.roles[0]}";
  var values = ${empApplicationFormData};
  <c:set var="role" value="${user.roles[0]}" />
	
  </script>
	<title>Welcome to Infogain</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
	<link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css">

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
        	<li class="active"><a href="${context}/dashboard">Home</a></li>
       	</c:if>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_RMG')">
        <c:if test="${role == 'ROLE_RMG'}">	
        	<li class="active"><a href="${context}/rmgdashboard">Home</a></li>
        </c:if>
        </sec:authorize>
        
        <sec:authorize access="hasAnyRole('ROLE_RMG_ADMIN')">
        <c:if test="${role == 'ROLE_RMG_ADMIN'}">
        	<li class="active"><a href="${context}/rmgadmindashboard">Home</a></li>
        </c:if>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RMG','ROLE_RMG_ADMIN')">
        <c:if test="${role == 'ROLE_ADMIN' || role == 'ROLE_RMG' || role == 'ROLE_RMG_ADMIN'}">	
        	<li><a href="${context}/landingpage/<%= session.getAttribute("selectedApplicationId")%>">Back To Candidate Page</a></li>
        </c:if>
        </sec:authorize>
        
        <sec:authorize access="hasAnyRole('ROLE_USER')">
        <c:if test="${role == 'ROLE_USER'}">
        	<li class="active"><a href="${context}/landingpage">Home</a></li>
        	<li><a href="employeeapplicationform" class="btn">Employee Application Form</a></li>
        </c:if>
        </sec:authorize>
          
          
        </ul>

        <ul class="nav navbar-nav navbar-right">
          <li><a href="#" ><span class="glyphicon glyphicon-user"  aria-hidden="true"></span> <%= session.getAttribute("userName") %></a></li>
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
                 <div class="app-panel-header">
                  <h1 class="app-panel-title">Employment Application Form</h1>
                 </div>


                 <div class="app-panel-content">

                  <div class="row">
                    <div  class="col-md-12">
                      
                      <div role="tabpanel">
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs" role="tablist" id="tabs">
                      	<li role="presentation" class="active" ><a href="#1" aria-controls="settings" role="tab" data-toggle="tab">Background Check Form</a></li>
                        <li role="presentation"><a href="#2" aria-controls="home" role="tab" data-toggle="tab">Personal Detail</a></li>
                        <li role="presentation"><a href="#3" aria-controls="profile" role="tab" data-toggle="tab">Education/ Training Details</a></li>
                        <li role="presentation"><a href="#4" aria-controls="messages" role="tab" data-toggle="tab">Skill Detail</a></li>
                        <li role="presentation"><a href="#5" aria-controls="settings" role="tab" data-toggle="tab">Employment Detail</a></li>
                        <li role="presentation"><a href="#6" aria-controls="settings" role="tab" data-toggle="tab">Salary Detail</a></li>
                        <li role="presentation"><a href="#7" aria-controls="settings" role="tab" data-toggle="tab">Other Detail</a></li>
                      </ul>

                      <!-- Tab panes -->
                      <div class="tab-content" >
                      <div role="tabpanel" class="tab-pane active" id="1">
                          <div class="title">Personal Details<br/></div>
                          
                          <form class="form-horizontal" id="bgcDetail">
                              <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Name of Applicant:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="nameOfApplicant">
                              </div>
                              <label for="dob" class="col-sm-2 control-label  col-offset-1">Date of Birth:</label>
                              <div class="col-sm-4">
                                <input type="date" class="form-control input-sm" id="dateOfBirth">
                              </div>
                            </div>

                              <div class="form-group">
                              <label for="pob" class="col-sm-2 control-label">Place of Birth:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="placeOfBirth">
                              </div>
                              <label for="sex" class="col-sm-2 control-label  col-offset-1">Sex:</label>
                              <div class="col-sm-4">
                                 <select class="form-control input-sm" id="sex">
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                                </select>
                              </div>
                            </div>
							
							<div class="form-group">
                              <label for="nationality" class="col-sm-2 control-label">Nationality:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="nationality">
                              </div>
                              <label for="fatherName" class="col-sm-2 control-label  col-offset-1">Father's Name:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="fatherName">
                              </div>
                            </div>

							<div class="form-group">
                              <label for="passportNo" class="col-sm-2 control-label">Passport No.:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="passportNumber">
                              </div>
                              <label for="homePhone" class="col-sm-2 control-label  col-offset-1">Home Phone:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="homePhone">
                              </div>
                            </div>
                            
                            <div class="form-group">
                              <label for="officePhone" class="col-sm-2 control-label">Office Phone:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="officePhone">
                              </div>
                              <label for="mobile" class="col-sm-2 control-label  col-offset-1">Mobile:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="mobile">
                              </div>
                            </div>
                            <hr>
                            <div class="title">Residential Address</div>
                            
                             <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Permanent Address:</label>
                              <div class="col-sm-4">
                                <textarea class="form-control" rows="3" id="permanentAddress"></textarea>
                              </div>
                            </div>
							
							<div class="form-group">
                              <label for="name" class="col-sm-2 control-label">City:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="permanentCity">
                              </div>
                               <label for="name" class="col-sm-2 control-label">State:</label>
                               <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="permanentState">
                              </div>
                            </div>
							
							<div class="form-group">
                              <label for="permanentTelephone" class="col-sm-2 control-label">Phone No.:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="permanentTelephone">
                              </div>
                             <label for="name" class="col-sm-2 control-label">Nature of Location:</label>
                              <div class="col-sm-2">
                                <select class="form-control input-sm" id="permanentNatureOfLocation">
                                <option>Rented</option>
                                <option>Own</option>
                              </select>
                              </div>
                            </div>
                            
                           <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Duration Of Stay:</label>
                              <div class="col-sm-2">
                                <input type="date" class="form-control input-sm" id="permanentDurationFrom">
                              </div>
                              <div class="col-sm-2">
                                <input type="date" class="form-control input-sm" id="permanentDurationTo">
                              </div>
                           </div>
                            
                            <div class="form-group">
                              <label for="pinCode" class="col-sm-2 control-label">Pin Code:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="permanentPinCode">
                              </div>
                            </div>
                            
                            
							
							<div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Current Address:</label>
                              <div class="col-sm-4">
                                <textarea class="form-control" rows="3" id="presentAddress"></textarea>
                              </div>
                            </div>
							
							<div class="form-group">
                              <label for="name" class="col-sm-2 control-label">City:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="presentCity">
                              </div>
                               <label for="name" class="col-sm-2 control-label">State:</label>
                               <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="presentState">
                              </div>
                            </div>
							
							<div class="form-group">
                              <label for="permanentTelephone" class="col-sm-2 control-label">Phone No.:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="presentTelephone">
                              </div>
                             <label for="name" class="col-sm-2 control-label">Nature of Location:</label>
                              <div class="col-sm-2">
                                <select class="form-control input-sm" id="presentNatureOfLocation">
                                <option>Rented</option>
                                <option>Own</option>
                              </select>
                              </div>
                            </div>
                            
                           <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Duration Of Stay:</label>
                              <div class="col-sm-2">
                                <input type="date" class="form-control input-sm" id="presentDurationFrom">
                              </div>
                              <div class="col-sm-2">
                                <input type="date" class="form-control input-sm" id="presentDurationTo">
                              </div>
                           </div>
                            
                            <div class="form-group">
                              <label for="pinCode" class="col-sm-2 control-label">Pin Code:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="presentPinCode">
                              </div>
                            </div>
 						<hr>
 						
 						<div class="title">Education Details<br/></div>
 						<div class="form-group-table">
                               <div class="form-group">
                                  <label for="name" class="col-sm-2 control-label">Examinations (Starting From 10th)</label>
                                  <label for="name" class="col-sm-1 control-label">From - To (MM/YY)</label>
                                  <label for="name" class="col-sm-2 control-label">School/College and Location</label>
                                  <label for="name" class="col-sm-2 control-label">Board & University</label>
                                  <label for="name" class="col-sm-1 control-label">%Marks</label>
                                  <label for="name" class="col-sm-1 control-label">Course Attended</label>
                                  <label for="name" class="col-sm-2 control-label">Roll Number/ Registration Number/ Exam Seat Number</label>
                                  <label for="name" class="col-sm-1 control-label">Discipline:</label>
                                  
                               </div>

                               <div class="form-group education-data">
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="examinationPassed">
                               	 	</select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                   <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
                                  
                               </div>
								
								
                                <div class="form-group education-data">
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="examinationPassed">
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
								
                                  
                               </div>
								 
                                <div class="form-group education-data">
                                  <div class="col-sm-2">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                  
                                   <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
                                  
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-2">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-2">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
                               </div>

                               <div class="form-group education-data">
                                  <div class="col-sm-2">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-2">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="courseAttended">
                                    	<option>Morning</option>
                                		<option>Evening</option>
                                		<option>Correspondence</option>
                               	 	</select>
                                  </div>
                                  
                                   <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="rollNumber">
                                  </div>
                                  
                                  <div class="col-sm-1">
                                    <select  class="form-control input-sm" name="discipline">
                                    	<option>Full Time</option>
                                		<option>Part Time</option>
                                		<option>Distance</option>
                               	 	</select>
                                  </div>
                               </div>
                     		</div>
 							<hr>
 							
 							<div class="title">Employment Record
 							<br/>
 							<br/>
 							
 							Starting with your present or most recent employer, please list last 2 employments. When listing consulting or temporary assignments, under "Employer", state the name of the consulting or temporary agency that placed you at the client site. Complete and accurate dates (month/year) must be provided. 
 							</div>
 							
 							<table class="table table-bordered table-condensed" id="employmentRecord">
                            <thead>
                              <tr>
                                <th></th>
                                <th>Employer 1</th>
                                <th>Employer 2</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>Name of Employer</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerName"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerName"></td>
                              </tr>
                              <tr>
                                <td>Employee Id</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employeeId"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employeeId"></td>
                              </tr>
                              <tr>
                                <td>Duration Worked</td>
                                <td class="employer1">
                                    <input type="text" class="form-control input-sm inputDate" name="employmentFrom" placeholder="From(mm/yy)">
                                    <input type="text" class="form-control input-sm inputDate" name="employmentTo" placeholder="To(mm/yy)">
                                </td>
                                <td class="employer2">
                                    <input type="text" class="form-control input-sm inputDate" name="employmentFrom" placeholder="From(mm/yy)">
                                    <input type="text" class="form-control input-sm inputDate" name="employmentTo" placeholder="To(mm/yy)">
                                </td>
                              </tr>
                              <tr>
                                <td>Street Address</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="streetAddress"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="streetAddress"></td>
                              </tr>
                              <tr>
                                <td>City</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerCity"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerCity"></td>
                              </tr>
                              <tr>
                                <td>State</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerState"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerState"></td>
                              </tr>
                              <tr>
                                <td>Country</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerCountry"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerCountry"></td>
                              </tr>
                              <tr>
                                <td>Postal Code</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerPostalCode"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerPostalCode"></td>
                              </tr>
                              <tr>
                                <td>Employer's Phone No.</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="employerTelephone"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="employerTelephone"></td>
                              </tr>
                              <tr>
                                <td>Remuneration</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="remuneration"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="remuneration"></td>
                              </tr>
                              <tr>
                                <td>Job Title</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                              </tr>
                              <tr>
                                <td>Reasons For Leaving</td>
                                <td class="employer1"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                                <td class="employer2"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                              </tr>
                              <tr>
                                <td>Employment Status</td>
                                <td class="employer1">
                                    <select  class="form-control input-sm" name="employmentStatus">
                                    	<option>Full Time</option>
                                    	<option>Contract / Through Outsourcing Agency</option>
                               	 	</select>
                                </td>
                                <td class="employer2">
                                	 <select  class="form-control input-sm" name="employmentStatus">
                                    	<option>Full Time</option>
                                    	<option>Contract / Through Outsourcing Agency</option>
                               	 	</select>
                                </td>
                              </tr>
                              <tr>
                                <td>Outsourcing Agency Details</td>
                                <td class="employer1">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyAddress" placeholder="Address">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyTelephone" placeholder="Tel No.">
                                </td>
                                <td class="employer2">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyAddress" placeholder="Address">
                                    <input type="text" class="form-control input-sm input-sm" name="outsourcingAgencyTelephone" placeholder="Tel No.">
                                </td>
                              </tr>
                              <tr>
                                <td>Description of Duties</td>
                                <td class="employer1"><textarea class="form-control" rows="3" id="employmentDiscription"></textarea></td>
                                <td class="employer2"><textarea class="form-control" rows="3" id="employmentDiscription"></textarea></td>
                              </tr>
                              <tr>
                                <td>Supervisor's Details</td>
                                <td class="employer1">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorTitle" placeholder="Title">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorTelephone" placeholder="Phone No.">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorEmail" placeholder="Email(Preferably official)">
                                    
                                </td>
                                <td class="employer2">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorTitle" placeholder="Title">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorTelephone" placeholder="Phone No.">
                                    <input type="text" class="form-control input-sm input-sm" name="supervisorEmail" placeholder="Email(Preferably official)">
                                </td>
                              </tr>
                              <tr>
                                <td>HR Manager's Details</td>
                                <td class="employer1">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerTelephone" placeholder="Phone No.">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerEmail" placeholder="Email(Preferably official)">
                                </td>
                                <td class="employer2">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerName" placeholder="Name">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerTelephone" placeholder="Phone No.">
                                    <input type="text" class="form-control input-sm input-sm" name="hrManagerEmail" placeholder="Email(Preferably official)">
                                </td>
                              </tr>
                              <tr>
                                <td>Current Employment Authority Provided</td>
                                <td class="employer1">
                                    <select  class="form-control input-sm" name="currentEmploymentAuthority">
                                    	<option>Yes</option>
                                    	<option>No</option>
                               	 	</select>
                                </td>
                                <td class="employer2">
                                </td>
                              </tr>
                            </tbody>
                          </table>
                          
                          <hr>
                          <div class="title">Declaration & Authorization<br/></div>
                          <ul class="list-group"> 
                          	<li class="list-group-item">I certify that the statements made in this application are valid and complete to the best of my knowledge. I understand that false or misleading information may result in termination of employment.</li>
                          	<li class="list-group-item">If upon investigations, any of this information is found to be incomplete or inaccurate, I understand that I will be subject to dismissal at any time during my employment.</li>
 							<li class="list-group-item">I hereby authorize Infogain and/or any of its subsidiaries or affiliates and any persons or organizations acting on its behalf (TP --------------------------.), to verify the information presented on this application form and to procure an investigative report or consumer report for that purpose.  </li>	
 							<li class="list-group-item">I hereby grant authority for the bearer of this letter to access or be provided with full details of my previous records. In addition, please provide any other pertinent information requested by the individual presenting this authority. </li>	
 							<li class="list-group-item">I hereby release from liability all persons or entities requesting or supplying such information.</li>	
 							<li class="list-group-item">I authorize Infogain to contact my present employer.</li>	
 							<li class="list-group-item"><div class="checkbox"><label><input type="checkbox" id="iAgree">I have read, understand, and by my acceptance, I am giving consent to above statements</label></div></li>	
                          </ul>
                          <!-- <div class="form-group">
                          	<label for="name" class="col-sm-2 control-label">Name:</label>
                          </div>
                          
                          <div class="form-group">
                          	<label for="name" class="col-sm-2 control-label">Name:</label>
                          </div> -->
                          
                          </form>
                          <div class="action-btn">
                            <button class="btn btn-default" id="saveBgcDetail">Save and Continue</button> <button class="btn btn-default" >Reset</button>
                          </div>
                        </div>
                      
                      <!-- Personal Detail -->
                        <div role="tabpanel" class="tab-pane" id="2">
                          <form class="form-horizontal" id="personalDetail">
                              <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Name:</label>
                              <div class="col-sm-4">
                                <input type="text" class="form-control input-sm" id="name">
                              </div>
                              <label for="name" class="col-sm-2 control-label  col-offset-1">Date:</label>
                              <div class="col-sm-4">
                                <input type="date" class="form-control input-sm" id="date">
                              </div>
                            </div>

                              <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Date of Birth:</label>
                              <div class="col-sm-4">
                                <input type="date" class="form-control input-sm" id="dob">
                              </div>
                              <label for="name" class="col-sm-2 control-label  col-offset-1">Marital Status:</label>
                              <div class="col-sm-4">
                                <select class="form-control input-sm" id="maritalStatus">
                                <option value="S">Single</option>
                                <option value="M">Married</option>
                                <option value="D">Divorced</option>
                                <option value="O">Other</option>
                                </select>
                              </div>
                            </div>


                              <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Permanent Address:</label>
                              <div class="col-sm-4">
                                <textarea class="form-control" rows="3" id="permanentAddress"></textarea>
                              </div>
                              <label for="name" class="col-sm-2 control-label  col-offset-1">Present Address:</label>
                              <div class="col-sm-4">
                                <textarea class="form-control" rows="3" id="presentAddress"></textarea>
                              </div>
                            </div>

                            <div class="form-group">
                              <label for="name" class="col-sm-2 control-label"></label>
                              <label for="name" class="col-sm-4 control-label">Telephone No. 
                                <input type="text" class="form-control input-sm" id="permanentTelephone">
                              </label>

                             <label for="name" class="col-sm-2 control-label"></label>

                              <label for="name" class="col-sm-2 control-label">Telephone  No.
                                <input type="text" class="form-control input-sm" id="presentAddressTelephone">
                              </label>

                              <label for="name" class="col-sm-2 control-label">Mobile 
                                <input type="text" class="form-control input-sm" id="presentAddressMobile">
                              </label>
                            </div>

                            <hr>

                              <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Valid Passport:</label>
                              <div class="col-sm-2">
                                <select class="form-control input-sm" id="passport">
                                <option>Yes</option>
                                <option>No</option>
                              </select>
                              </div>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="passportExpiry" placeholder="Valid Upto">
                              </div>
                            </div>

                            <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Valid Visa:</label>
                              <div class="col-sm-2">
                                <select class="form-control input-sm" id="visa">
                                <option>Yes</option>
                                <option>No</option>
                              </select>
                              </div>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" id="visaExpiry" placeholder="Valid Upto">
                              </div>
                            </div>

                            <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Onsite/Overseas Experience; if any:</label>
                              <div class="col-sm-4">
                                <textarea class="form-control" rows="3" id="onsiteExperience"></textarea>
                              </div>
                            </div>
<hr>
                            <div class="form-group">
                              <label for="name" class="col-sm-2 control-label">Total Experience:</label>
                              <label for="name" class="col-sm-4 control-label">IT <textarea class="form-control" rows="3" id="itExperience"></textarea></label>
                              <label for="name" class="col-sm-4 control-label">Relevant <textarea class="form-control" rows="3" id="relevantExperience"></textarea></label>
                            </div>
                          </form>
                          <div class="action-btn">
                            <button class="btn btn-default" id="savePersonalDetail">Save and Continue</button> <button class="btn btn-default" >Reset</button>
                          </div>
                        </div>


                        <div role="tabpanel" class="tab-pane" id="3">
                          <!-- Education Detail -->
                          <div class="title">Educational Detail</div>
                          <form class="form-horizontal" id="educationalBackground">
                            <div class="form-group-table">
                               <div class="form-group">
                                  <label for="name" class="col-sm-3 control-label">Examinations (Starting From 10th)</label>
                                  <label for="name" class="col-sm-2 control-label">From - To (MM/YY)</label>
                                  <label for="name" class="col-sm-3 control-label">School/College and Location</label>
                                  <label for="name" class="col-sm-3 control-label">Board & University</label>
                                  <label for="name" class="col-sm-1 control-label">%Marks</label>
                               </div>

                               <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>

                                <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>

                               <div class="form-group education-data">
                                  <div class="col-sm-3">
                                   
                                    <select  class="form-control input-sm" name="examinationPassed">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-3">
                                    <select  class="form-control input-sm" name="institution">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-3">
                                     <select  class="form-control input-sm" name="board">
                                  
                                </select>
                                  </div>
                                  <div class="col-sm-1">
                                    <input type="text" class="form-control input-sm" name="marks">
                                  </div>
                               </div>
                               </div>
                          </form>

                          <hr>

                          <!-- Training Detail -->
                          <div class="title">Training Detail</div>
                          <form class="form-horizontal" id="trainingAttended">
                            <div class="form-group-table">
                               <div class="form-group">
                                  <label for="name" class="col-sm-3 control-label">Name of the Course</label>
                                  <label for="name" class="col-sm-2 control-label">From - To (MM/YY)</label>
                                  <label for="name" class="col-sm-4 control-label">Name of the Institute/ Organization</label>
                                  <label for="name" class="col-sm-3 control-label">Whether certificate Awarded</label>
                               </div>

                               <div class="form-group training-data">
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="courseName">
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-4">
                                    <input type="text" class="form-control input-sm" name="institution">
                                  </div>
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="certificateAwarded">
                                  </div>
                               </div>

                               <div class="form-group training-data">
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="courseName">
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-4">
                                    <input type="text" class="form-control input-sm" name="institution">
                                  </div>
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="certificateAwarded">
                                  </div>
                               </div>

                              <div class="form-group training-data">
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="courseName">
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-4">
                                    <input type="text" class="form-control input-sm" name="institution">
                                  </div>
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="certificateAwarded">
                                  </div>
                               </div>

                               <div class="form-group training-data">
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="courseName">
                                  </div>
                                  <div class="col-sm-2">
                                    <input type="text" class="form-control input-sm" name="fromToDate">
                                  </div>
                                  <div class="col-sm-4">
                                    <input type="text" class="form-control input-sm" name="institution">
                                  </div>
                                  <div class="col-sm-3">
                                    <input type="text" class="form-control input-sm" name="certificateAwarded">
                                  </div>
                               </div>

                            </div>   
                          </form>

                          <div class="action-btn">
                            <button class="btn btn-default" id="saveEducationalQualification">Save and Continue</button> <button class="btn btn-default">Reset</button>
                          </div>

                        </div>


                        <div role="tabpanel" class="tab-pane" id="4">
                        <!-- Skill Detail -->
                        <form class="form-horizontal" id="skillsForm">
                            <div class="form-group">
                                  <label class="col-sm-12"><div class="title">Skills Knowledge (Self Rating) <br>
Ratings: 0 - No Knowledge, 1 - Training only, 2 - Can work with Support, 3 - Can work independently, 4 - Can lead and guide others, 5 - Expert
</div></label>
                               </div>

                               <div class="form-group">
                                   <label for="name" class="col-sm-2 control-label">Primary Skill</label>
                                  <div class="col-sm-6">
                                    <input type="text" class="form-control input-sm" name="primarySkill">
                                  </div>
                               </div>

                                <div class="form-group">
                                   <label for="name" class="col-sm-2 control-label">Secondary Skill</label>
                                  <div class="col-sm-6">
                                    <input type="text" class="form-control input-sm" id="secondarySkill">
                                  </div>
                               </div>

                               <hr>

                               <div class="form-group-table">

                                <div class="form-group">
                                      <label for="name" class="col-sm-2 control-label title">Technical Skills</label>
                                      <label for="name" class="col-sm-2 control-label title">Self Rating</label>
                                      <label for="name" class="col-sm-2 control-label title">Technical Skills</label>
                                      <label for="name" class="col-sm-2 control-label title">Self Rating</label>
                                      <label for="name" class="col-sm-2 control-label title">Technical Skills</label>
                                      <label for="name" class="col-sm-2 control-label title">Self Rating</label>
                                  
                               </div>

                                  <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                 <div class="form-group">
                                        <label for="name" class="col-sm-2 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                        <label for="name" class="col-sm-2 col-sm-offset-1 control-label skill-name"></label>
                                        <div class="col-sm-1">
                                          <input type="text" class="form-control input-sm skill-id" data_skillId="">
                                        </div>
                                 </div>
                                
                                 
                               
                                  
                               </div>
                        </form>
                          <div class="action-btn">
                            <button id="saveSkillDetail" class="btn btn-default">Save and Continue</button> <button class="btn btn-default">Reset</button>
                          </div>
                        </div>


                        <div role="tabpanel" class="tab-pane" id="5">
                        <!-- Employment Detail -->
                          <table class="table table-bordered table-condensed" id="employmentDetail">
                            <thead>
                              <tr>
                                <th></th>
                                <th>Present Employer</th>
                                <th>Previous Employer</th>
                                <th>Previous Second</th>
                                <th>Previous to Third</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>Name of Employer</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="employerName"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="employerName"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="employerName"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="employerName"></td>
                              </tr>
                              <tr>
                                <td>Location</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="location"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="location"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="location"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="location"></td>
                              </tr>
                              <tr>
                                <td>Number of Employees</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="numberOfEmployee"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="numberOfEmployee"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="numberOfEmployee"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="numberOfEmployee"></td>
                              </tr>
                              <tr>
                                <td>Duration Worked</td>
                                <td class="present-employer">
                                    <input type="text" class="form-control input-sm inputDate" name="years" placeholder="years">
                                    <input type="text" class="form-control input-sm inputDate" name="months" placeholder="months">
                                </td>
                                <td class="previous-employer">
                                    <input type="text" class="form-control input-sm inputDate" name="years" placeholder="years">
                                    <input type="text" class="form-control input-sm inputDate" name="months" placeholder="months">
                                </td>
                                <td class="previous-second">
                                    <input type="text" class="form-control input-sm inputDate" name="years" placeholder="years">
                                    <input type="text" class="form-control input-sm inputDate" name="months" placeholder="months">
                                </td>
                                <td class="previous-third">
                                    <input type="text" class="form-control input-sm inputDate" name="years" placeholder="years">
                                    <input type="text" class="form-control input-sm inputDate" name="months" placeholder="months">
                                </td>
                              </tr>
                              <tr>
                                <td>Experience (in months)</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="experience"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="experience"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="experience"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="experience"></td>
                              </tr>
                              <tr>
                                <td>Designation (s) Held</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="designationHeld"></td>
                              </tr>
                              <tr>
                                <td>Reporting To (Name & Designation)</td>
                                 <td class="present-employer"><input type="text" class="form-control input-sm" name="reportingTo"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="reportingTo"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="reportingTo"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="reportingTo"></td>
                              </tr>
                              <tr>
                                <td>Reasons For Leaving</td>
                                <td class="present-employer"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                                <td class="previous-employer"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                                <td class="previous-second"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                                <td class="previous-third"><input type="text" class="form-control input-sm" name="reasonForLeaving"></td>
                              </tr>
                            </tbody>
                          </table>

                          <div class="title">Brief Description of Current Job Responsibilities & your present role (TM/ML/TL/PL/PM/Consultant/Sr. Consultant/others):</div>
                          <form class="form-horizontal" id="currentEmploymentDetail">
                              <div class="form-group">
                              <label for="name" class="col-sm-3 control-label">Name of Current Project:</label>
                              <div class="col-sm-5">
                                <input type="text" class="form-control input-sm" name="currentProjectName">
                              </div>
                              <label for="name" class="col-sm-2 control-label  col-offset-1">Team Size:</label>
                              <div class="col-sm-2">
                                <input type="text" class="form-control input-sm" name="teamSize">
                              </div>
                            </div>
                              <div class="form-group">
                              <label for="name" class="col-sm-3 control-label">Skill Used in Projects:</label>
                              <div class="col-sm-9">
                                <input type="text" class="form-control input-sm" name="skillsUsed">
                              </div>
                            </div>
                              <div class="form-group">
                              <label for="name" class="col-sm-3 control-label">Roles & Responsibilities:</label>
                              <div class="col-sm-9">
                                <textarea row="8" class="form-control input-sm textarea" name="rolesAndResponsibilities"></textarea>
                              </div>
                            </div>
                          </form>

                           <div class="title">Draw in brief the Organization Structure of the company you are presently employed with indicating two levels above and one level below.</div>
                              <form class="form-horizontal" id="organisationDescription">
                              <div class="form-group">
                              <div class="col-sm-12">
                                <textarea row="8" class="form-control input-sm textarea" name="organisationDescription"></textarea>
                              </div>
                            </div>
                            </form>

                          <div class="action-btn">
                            <button id="saveEmploymentDetail" class="btn btn-default">Save and Continue</button> <button class="btn btn-default">Reset</button>
                          </div>
                        </div>


                       
                        <div role="tabpanel" class="tab-pane" id="6">
                           <!-- Salary Detail -->
                            <table class="table table-bordered table-condensed" id="salaryData">
                            <thead>
                              <tr>
                                <th>S. No.</th>
                                <th>Salary Breakup (Components)</th>
                                <th>Monthly</th>
                                <th>Annual</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>1</td>
                                <td>Current CTC</td>
                                <td><input type="text" class="form-control input-sm" name="currentCtcMonthly"></td>
                                <td><input type="text" class="form-control input-sm" name="currentCtcAnnual"></td>
                              </tr>
                              <tr>
                                <td>2</td>
                                <td>Current</td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryMonthly"></td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryAnnual"></td>
                              </tr>
                              <tr>
                                <td>3</td>
                                <td>Fixed</td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryFixedMonthly"></td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryFixedAnnual"></td>
                              </tr>
                              <tr>
                                <td>4</td>
                                <td>Variable</td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryVariableMonthly"></td>
                                <td><input type="text" class="form-control input-sm" name="currentSalaryVariableAnnual"></td>
                              </tr>
                              <tr>
                                <td>5</td>
                                <td>Incentive / Performance Bonus /Ex-Gratia(If Any)</td>
                                <td><input type="text" class="form-control input-sm" name="incentiveMonthly"></td>
                                <td><input type="text" class="form-control input-sm" name="incentiveAnnual"></td>
                              </tr>
                              <tr>
                                <td>6</td>
                                <td>Monthly in Hand</td>
                                <td><input type="text" class="form-control input-sm" name="monthlyInHand"></td>
                                <td></td>
                              </tr>
                              <tr>
                                <td>7</td>
                                <td>Salary Expectations</td>
                                <td><input type="text" class="form-control input-sm" name="expectedSalary"></td>
                                <td></td>
                              </tr>
                            </tbody>
                            </table>

                            <form class="form-horizontal" id="notice">
                              <div class="form-group">
                              <label for="name" class="col-sm-3 control-label">Expected Joining Time:</label>
                              <div class="col-sm-3">
                                <input type="text" class="form-control input-sm" name="expectedJoiningDate">
                              </div>
                            </div>
                              <div class="form-group">
                              <label for="name" class="col-sm-3 control-label">Notice Period:</label>
                              <div class="col-sm-3">
                                <input type="text" class="form-control input-sm" name="noticePeriod">
                              </div>
                            </div>
                          </form>

                           <div class="title">Significant Achievements/Contributions you have made in the previous assignments like appreciations awards etc.  Please Specify :</div>
                              <form class="form-horizontal" id="achievements">
                              <div class="form-group">
                              <div class="col-sm-12">
                                <textarea row="8" class="form-control input-sm textarea" name="achievements"></textarea>
                              </div>
                            </div>
                            </form>

                          <div class="action-btn">
                            <button id="saveSalaryDetail" class="btn btn-default">Save and Continue</button> <button class="btn btn-default">Reset</button>
                          </div>

                         </div>

                         <div role="tabpanel" class="tab-pane" id="7">
                         <!-- Other Detail -->
                           <div class="title">Family Details</div>
                            <table class="table table-bordered table-condensed" id="familyData">
                            <thead>
                              <tr>
                                <th>Members (Names)</th>
                                <th>Occupation / Name Of Organisation</th>
                                <th>Age</th>
                                <th>Dependent Yes / No</th>
                                <th>Present Location</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                               <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                                <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                               <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                                 <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                              <tr>
                                <td><input type="text" class="form-control input-sm" name="memberName"></td>
                                <td><input type="text" class="form-control input-sm" name="occupation"></td>
                                <td><input type="text" class="form-control input-sm" name="age"></td>
                                <td><input type="text" class="form-control input-sm" name="dependent" maxlength="1"></td>
                                <td><input type="text" class="form-control input-sm" name="presentLocation"></td>
                              </tr>
                            </tbody>
                            </table>

                            <table class="table table-bordered table-condensed" id="checks">
                              <tr>
                                <td>Have you been referred to us by someone?</td>
                                <td>
                                <select class="form-control input-sm" id="ifReferred">
                                <option>Yes</option>
                                <option>No</option>
                              </select>
                              </td>
                              </tr>
                              <tr>
                                <td>Do you have any contract/bond with your present Employer?</td>
                                <td><select class="form-control input-sm" id="ifContract">
                                <option>Yes</option>
                                <option>No</option>
                              </select></td>
                              </tr>
                              <tr>
                                <td>Have you any objection to our making Enquiries from your present Employer?</td>
                                <td><select class="form-control input-sm" id="ifObjection">
                                <option>Yes</option>
                                <option>No</option>
                              </select></td>
                              </tr>
                              <tr>
                                <td>Have you ever applied to work for this Company?</td>
                                <td><select class="form-control input-sm" id="ifAppliedBefore">
                                <option>Yes</option>
                                <option>No</option>
                              </select></td>
                              </tr>
                              <tr>
                                <td>Have you ever applied H1 Visa/Work Permit by any other Company?</td>
                                <td><select class="form-control input-sm" id="ifAppliedForVisa">
                                <option>Yes</option>
                                <option>No</option>
                              </select></td>
                              </tr>
                            </table>

                           <form class="form-horizontal">
                              <div class="form-group">
                              <label for="name" class="col-sm-7 control-label">Your Location preference:</label>
                              <div class="col-sm-5">
                                <select  class="form-control input-sm" name="locationPreference" >
                                  <option>India</option>
                                  <option>Overseas</option>
                                </select>
                              </div>
                            </div>
                              <div class="form-group" >
                              <label for="name" class="col-sm-7 control-label">What are your specific strengths & Improvement Areas?</label>
                               <div class="col-sm-5">
                                <textarea class="form-control input-sm textarea" row="5" name="strength"></textarea>
                              </div>
                            </div>
                             
                              <div class="form-group">
                              <label for="name" class="col-sm-7 control-label">What are the new technological areas you think you need to pickup / improve?</label>
                              <div class="col-sm-5"> <input type="text" class="form-control input-sm" name="toImprove"></div>
                            </div>
                          </form>

                          <table class="table table-bordered table-condensed">
                            <thead>
                              <tr>
                                <th>PROFESSIONAL REFERENCES:</th>
                                <th>1</th>
                                <th>2</th>
                                <th>3</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>Name:</td>
                                <td class="reference-1"><input type="text" class="form-control input-sm" name="referenceName"></td>
                                <td class="reference-2"><input type="text" class="form-control input-sm" name="referenceName"></td>
                                <td class="reference-3"><input type="text" class="form-control input-sm" name="referenceName"></td>
                              </tr>
                              <tr>
                                <td>Designation:</td>
                                <td class="reference-1"><input type="text" class="form-control input-sm" name="designation"></td>
                                <td class="reference-2"><input type="text" class="form-control input-sm" name="designation"></td>
                                <td class="reference-3"><input type="text" class="form-control input-sm" name="designation"></td>
                              </tr>
                              <tr>
                                <td>Name of Organization:</td>
                               <td class="reference-1"><input type="text" class="form-control input-sm" name="organisationName"></td>
                                <td class="reference-2"><input type="text" class="form-control input-sm" name="organisationName"></td>
                                <td class="reference-3"><input type="text" class="form-control input-sm" name="organisationName"></td>
                              </tr>
                              <tr>
                                <td>Telephone No:</td>
                                <td class="reference-1"><input type="text" class="form-control input-sm" name="contact"></td>
                                <td class="reference-2"><input type="text" class="form-control input-sm" name="contact"></td>
                                <td class="reference-3"><input type="text" class="form-control input-sm" name="contact"></td>
                              </tr>
                            </tbody>
                            </table>
                            
                          <div class="action-btn">
                            <button id="saveOtherDetail" class="btn btn-default">Save</button> <button class="btn btn-default">Reset</button>
                          </div>
                         </div>
                         
                         <!-- Background Check Form -->
                        
                      </div>


                      
                      </div>

                    </div>
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

    <!-- jQuery -->
    <script src="${context}/resources/js/jquery.min.js"></script>
    <script src="${context}/resources/js/jquery-ui.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>
     <script src="${context}/resources/js/bootbox.min.js"></script>
    <script src="${context}/resources/js/employeeapplicationform.js"></script>
</body>
</html>