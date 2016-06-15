
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
 <script type="text/javascript">
  var values = ${landingpageDataResponse};
  var bgcData = ${bgcData};
  <c:set var="context" value="${pageContext.request.contextPath}" />
  var contextPath='<%= request.getContextPath()%>'; 
  var role = "${user.roles[0]}";
  <c:set var="role" value="${user.roles[0]}" />
 
  </script>
	<title>Welcome to Infogain</title>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
	<link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${context}/resources/css/app-custom.css">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
  <![endif]-->
 
</head>
<body id="appInternalPage">
    <!-- Header Part -->
    <div class="app-header">
      <div class="container">
      	<div class="row">
      		<div class="col-md-2"><img src= "${context}/resources/images/eDOC-logo.png"></div>
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
        
        <sec:authorize access="hasAnyRole('ROLE_USER')">
        <c:if test="${role == 'ROLE_USER'}">
        	<li class="active"><a href="${context}/landingpage">Home</a></li>
        </c:if>
        	<!-- <li><a href="employeeapplicationform" class="btn">Employee Application Form</a></li> -->
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
          <div class="col-md-3">
              <div class="app-content-box user-bg">
                  <div class="app-panel-header">
                  </div>
                  <div class="app-panel-content">
                     <table id="bgtable" class="table table-bordered table-condensed bg-warning">
						<tr data_fileDescriptionId="" data_fileId="" data_FileLocation="" data_flagId="">
						<sec:authorize access="hasAnyRole('ROLE_USER')">
							<td class="text-center"><input type="image" id="uploadthisbgCheck" src="${context}/resources/images/display.jpg" data-target="#upload-background-check-doc" width="130" height="150"></td> 
						 </sec:authorize>
						 <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_RMG', 'ROLE_RMG_ADMIN')">
						<td class="text-center"><input type="image" src="${context}/resources/images/display.jpg"  width="130" height="150"></td> 
						 </sec:authorize>
						</tr>
					</table>  
					
                  </div>
              </div>
              
               <div class="app-content-box user-bg">
                  <div class="app-panel-header">
                  <div class="app-panel-title">Employee Application Form</div>
                  </div>
                  <div class="app-panel-content">
                     <table  class="table table-bordered table-condensed bg-warning">
						<tr >
							<td class="text-center"><a href="employeeapplicationform" >Employee Application Form</a>
						</tr>
					</table>  
					 <sec:authorize access="hasAnyRole('ROLE_USER')">
              <p class="text-danger text-center">Fill this form before submitting the documents.</p>
              </sec:authorize>
				</div>
				
              </div>
              
              
              
              
          
          <div class="app-content-box user-profile">
                  <div class="app-panel-header">
                  <div class="app-panel-title">Profile Snapshot</div>
                  </div>
                  <div class="app-panel-content">
                      
                      <div class="alert text-center" id="hr-status" role="alert"></div>

                      <ul class="app-panel-list">
                        <li>
                          <div class="defination-term">Username</div> 
                          <div id="username" class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Application Id</div> 
                          <div id="applicationId" class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Email</div> 
                          <div id="email" class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">First Name</div> 
                          <div id="firstName" class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Last Name</div> 
                          <div id="lastName" class="defination-desc"></div>
                        </li>
                        <!-- <li>
                          <div class="defination-term">Grade</div> 
                          <div class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">RM</div> 
                          <div class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Status</div> 
                          <div class="defination-desc"></div>
                        </li>
                         <li>
                          <div class="defination-term">ZIP</div> 
                          <div class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Location</div> 
                          <div class="defination-desc">IND-NOIDA</div>
                        </li>
                        <li>
                          <div class="defination-term">Project</div> 
                          <div class="defination-desc"></div>
                        </li>
                        <li>
                          <div class="defination-term">Skill</div> 
                          <div class="defination-desc"></div>
                        </li> -->
                      </ul> 
                  </div>
              </div>
             
          </div>
          
          <div class="col-md-9">
                 <div class="app-content-box doc-details">
                  <div class="app-panel-header">
                  <div class="app-panel-title">Document Information</div>
                  <!-- <div class="doc-status pull-right">Document Uploaded : <span class="text-large" id="total"></span></div> -->
                  </div>
                  <div class="app-panel-content">
                       <sec:authorize access="hasAnyRole('ROLE_USER')">
                      <div class="row">
                        <div id="notification" class="col-md-12">
                         <div class='alert alert-info alert-dismissible' role='alert'>
                          <button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                          One file per document is allowed.
                          </div>
                        </div>
                      </div>
				 </sec:authorize> 
                       <div class="row">
                        <div class="col-md-12">
                          <ul id="editPanel" class="list-inline pull-right functionality">
                          <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                            <li><img src="${context}/resources/images/create-folder.png" id="addFolder" class="tooltip-btn" data-placement="top" title="Create Folder"  data-toggle="modal" data-target="#create-folder"></li>
                          </sec:authorize>  
                            <li><img src="${context}/resources/images/upload-doc.png" class="tooltip-btn" data-placement="top" title="Upload Document" data-toggle="modal" data-target="#upload-non-default-doc"></li>
                            <li><img src="${context}/resources/images/delete.png" class="tooltip-btn" data-placement="top" title="Remove" data-toggle="modal" data-target="#doc-remove"></li>
                          </ul>

                        <div class="clearfix"></div>
                        
                        <div class="doc-table">
                        <table id="dataTable" class="table table-condensed table-hover">
                        <thead>
                          <tr>
                            <th><input type="checkbox" id="selectAll"></th>
                            <th><button class="btn btn-default btn-sm"  id="goback">&lt</button></th>
                            <th></th>
                            <th>Documents</th>
                            <th>Last Modified</th>
                            <th>HR Approval Status</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
						</tbody>
                      </table>
                    </div>

                   
                    
                    <div class="row">
                    <div class="col-md-5">
                      <ul class="list-inline icon-notation">
                          <li class="approve"><img src="${context}/resources/images/3.png"> Approved</li>
                          <li class="pending"><img src="${context}/resources/images/2.png"> Pending</li>
                          <li class="rejected"><img src="${context}/resources/images/1.png"> Rejected</li>
                          <li class="not-uploaded"><img src="${context}/resources/images/0.png"> Not Uploaded</li>
                      </ul>
                    </div>
				<sec:authorize access="hasAnyRole('ROLE_RMG')">
                    <div class="col-md-7 text-right">
                        <button class="btn btn-default btn-sm" type="button" id="approve-button" data-toggle="modal" data-target="#approve-doc" disabled="disabled">Approve</button>
                        <button class="btn btn-default btn-sm" type="button" id="reject-button" data-toggle="modal" data-target="#reject-doc" disabled="disabled">Reject</button>      
                    </div>
                    
                  </sec:authorize>
                  <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                	<div class="col-md-7 text-right">
                        <button class="btn btn-default btn-sm" type="button" id="approve-button" data-toggle="modal" data-target="#approve-doc" disabled="disabled">Approve</button>
                        <button class="btn btn-default btn-sm" type="button" id="reject-button" data-toggle="modal" data-target="#reject-doc" disabled="disabled">Reject</button>      
                    	<button class="btn btn-default btn-sm" type="button" id="make-employee" data-toggle="modal" data-target="#makeEmployee">Make Employee</button>
                    	<button class="btn btn-default btn-sm" type="button" id="enable-user">Enable User</button>
                    	<button class="btn btn-default btn-sm" type="button" id="disable-user">Disable User</button>
                    	<button class="btn btn-default btn-sm" type="button" id="enable-edit">Enable Edit</button>
                    	<button class="btn btn-default btn-sm" type="button" id="disable-edit">Disable Edit</button>
                    </div>     
                  </sec:authorize>
                   <sec:authorize access="hasAnyRole('ROLE_USER')">
                  <div class="col-md-7 text-right">
                  <button class="btn btn-default btn-sm" id="submit" type="button">Submit</button> 
                   </div>
                  </sec:authorize> 
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



    <!-- Model : Upload Document -->
    <div class="modal fade"  id="upload-doc" tabindex="-1" role="dialog" aria-labelledby="uploadDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Upload Document</h4>
          </div>
          <div class="modal-body">

            <h1 class="modal-title-h1">Experience Certificates from Previous Employer(s)</h1>

             <table class="table table-bordered table-condensed table-hover">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th width="100%">Documents</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Relieving Letter from Tecnoglare Infotech Pvt. Ltd.</td>
                            <td class="action-btn">
                            <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-remove-circle"></span>
                            </button>

                             <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            </td>
                          </tr>
                            <tr>
                            <td>2</td>
                            <td>Relieving Letter from Eznet Solution Pvt. Ltd.</td>
                            <td class="action-btn">
                            <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-remove-circle"></span>
                            </button>

                             <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            </td>
                          </tr>
                        </tbody>
                      </table>


                      <form class="form-horizontal">
                        <legend class="title">Add Document</legend>
                        <div class="form-group">
                          <div class="col-sm-10">
                            <input type="email" class="form-control input-sm" id="docTitle" placeholder="Document Title">
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-sm-10">
                                <div class="input-group">
                                <input type="text" class="form-control input-sm" placeholder="">
                                <span class="input-group-btn">
                                  <button class="btn btn-sm btn-default" type="button">BROWSE</button>
                                </span>
                              </div>
                          </div>
                          <div class="col-sm-2">
                            <button class="btn btn-sm btn-default" type="button">UPLOAD</button>
                          </div>
                        </div>
                      </form>
          </div>
          <div class="modal-footer">
            <button type="button"  class="btn btn-primary" data-dismiss="modal" disabled="">Submit</button>
            <button type="button" class="btn btn-default">Reset</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- Model : Document Approve -->
    <div class="modal fade" id="approve-doc" tabindex="-1" role="dialog" aria-labelledby="approveRejectDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title text-center">Document Review</h4>
          </div>
          <div class="modal-body  text-center">
            Do you want to approve all the selected document(s) submitted by Employee?
          </div>
          <div class="modal-footer  text-center">
            <button type="button" class="btn btn-primary" data-dismiss="modal" id="fileApprove">Yes</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    
    
    <!-- Model : Document Reject -->
    <div class="modal fade" id="reject-doc" tabindex="-1" role="dialog" aria-labelledby="approveRejectDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title text-center">Document Review</h4>
          </div>
          <div class="modal-body  text-center">
            Do you want to reject the selected document(s) submitted by this Employee?
          </div>
          <div class="modal-footer  text-center">
            <button type="button" class="btn btn-primary" data-dismiss="modal" id="fileReject">Yes</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- Model : Edit Document -->
    <div class="modal fade"  id="edit-doc" tabindex="-1" role="dialog" aria-labelledby="editDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Edit Document</h4>
          </div>
          <div class="modal-body">

            <h1 class="modal-title-h1">Experience Certificates from Previous Employer(s)</h1>

             <table class="table table-bordered table-condensed table-hover">
                        <thead>
                          <tr>
                            <th>#</th>
                            <th width="100%">Documents</th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                            <tr>
                            <td>1</td>
                            <td>Relieving Letter from Tecnoglare Infotech Pvt. Ltd.</td>
                            <td class="action-btn">
                            <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-remove-circle"></span>
                            </button>

                             <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            </td>
                          </tr>
                            <tr>
                            <td>2</td>
                            <td>Relieving Letter from Eznet Solution Pvt. Ltd.</td>
                            <td class="action-btn">
                            <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-remove-circle"></span>
                            </button>

                             <button type="button" class="btn  btn-link btn-xs">
                              <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            </td>
                          </tr>
                        </tbody>
                      </table>


                      <form class="form-horizontal">
                        <legend class="title">Add Document</legend>
                        <div class="form-group">
                          <div class="col-sm-10">
                            <input type="email" class="form-control input-sm" id="docTitle" placeholder="Document Title">
                          </div>
                        </div>
                        <div class="form-group">
                          <div class="col-sm-10">
                                <div class="input-group">
                                <input type="text" class="form-control input-sm" placeholder="">
                                <span class="input-group-btn">
                                  <button class="btn btn-sm btn-default" type="button">BROWSE</button>
                                </span>
                              </div>
                          </div>
                          <div class="col-sm-2">
                            <button class="btn btn-sm btn-default" type="button">UPLOAD</button>
                          </div>
                        </div>
                      </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" data-dismiss="modal">Submit</button>
            <button type="button" class="btn btn-default">Reset</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


<!-- Model : Upload Default Doc -->
    <div class="modal fade"  id="upload-single-doc" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Upload Document</h4>
          </div>
         
          <div class="modal-body">
          <h3 class ="text-center"> Only docx, pdf and jpg/jpeg/gif formats are supported.</h3>
              <form class="form-horizontal"  id="form2" action="uploadFile" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                  <div class="col-sm-10">
                    <input type="hidden" name="fileId"  value="0" >
                    <input type="hidden" name="fileDescriptionId"  value="0">
                    <input type="hidden" name="parentCategoryId"  value="0" >
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-8">
                        <div class="input-group">
                        <input type="file" id="file2" name="file2" class="form-control input-sm" placeholder="">
                        
                      </div>
                  </div>
                  <div class="col-sm-4">
                    <input class="btn btn-sm btn-default" id="fileUpload" type="button"  value="UPLOAD & SAVE"/>
                  </div>
                </div>
              </form>
          </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
  <!-- Model : Upload Non Default Doc -->
    <div class="modal fade"  id="upload-non-default-doc" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Upload Document</h4>
          </div>
          <div class="modal-body">
          <h3 class ="text-center"> Only docx, pdf and jpg/jpeg/gif formats are supported.</h3>
              <form class="form-horizontal"  id="form3" action="uploadFile" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                  <div class="col-sm-10">
                    <input type="hidden" name="fileId"  value="0" >
                    <input type="hidden" name="fileDescriptionId"  value="0">
                    <input type="hidden" name="parentCategoryId"  value="0" >
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-8">
                        <div class="input-group">
                        <input type="file" id="file2" name="file2" class="form-control input-sm" placeholder="">
                       
                      </div>
                  </div>
                  <div class="col-sm-4">
                    <input class="btn btn-sm btn-default" onclick="uploadJqueryForm2()" type="button"  value="UPLOAD & SAVE"/>
                  </div>
                </div>
              </form>
          </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <!-- Model : Make Employee -->
    <div class="modal fade"  id="makeEmployee" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Enter Information</h4>
          </div>
          <div class="modal-body">
              <form  id="employeeForm" class="form-horizontal">
                <div class="form-group">
                  <div class="col-sm-10">
                    <input type="text" class="form-control input-sm" name="employeeId" placeholder="Employee ID">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-10">
                   <input type="email" class="form-control input-sm" name="employeeEmail" placeholder="Employee Email">
                  </div>
                  <div class="col-sm-2">
                    <button class="btn btn-sm btn-default" type="button" id="make" >Submit</button>
                  </div>
                </div>
              </form>
          </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    
    <!-- Model : view pdf -->
    <div id="dialog" title="Document">
<div class="media" style="width: 500px; background-color: rgb(255, 255, 255);"><iframe id="viewframe" width="950" height="400" src=""></iframe></div>
</div>


    <!-- Model : Create Folder-->
    <div class="modal fade"  id="create-folder" tabindex="-1" role="dialog" aria-labelledby="createFolderLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Create Folder</h4>
          </div>
          <div class="modal-body">
            <form method="post" id="form4" class="form-horizontal" action="createFolder"> 
              <div class="input-group">
                <input type="text" class="form-control input-sm" name="categoryName" placeholder="Folder Title">
               <!--  <input type="radio" id="visibleToUser" name="visibility"> Visible to user
                <input type="radio" id="invisibleToUser" name="visibility"> Invisible to user -->
                <input type="hidden" name="parentCategoryId" value="">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="createFolder" class="btn btn-primary" data-dismiss="modal" onclick="createFolderForm()">Create</button>
            <button type="button" class="btn btn-default">Reset</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- Model : Document/Folder Remove -->
    <div class="modal fade" id="doc-remove" tabindex="-1" role="dialog" aria-labelledby="docRemoveLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title text-center">Document/Folder Remove</h4>
          </div>
          <div class="modal-body  text-center">
            Do you want to remove the selected documents or folder?
          </div>
          <div class="modal-footer  text-center">
            <button type="button" id="fileRemove"  class="btn btn-primary" data-dismiss="modal">Yes</button>
            <button type="button" class="btn btn-default">No</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

<!-- Model : Upload Default Background Check Doc -->
    <div class="modal fade"  id="upload-background-check-doc" tabindex="-1" role="dialog" aria-labelledby="uploadSingleDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Upload Document</h4>
          </div>
          <div class="modal-body">
              <form class="form-horizontal"  id="bgform" action="uploadFile" method="post" enctype="multipart/form-data" >
                <div class="form-group">
                  <div class="col-sm-10">
                    <input type="hidden" name="fileId"  value="" >
                    <input type="hidden" name="fileDescriptionId"  value="">
                    <input type="hidden" name="parentCategoryId"  value="" >
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-8">
                        <div class="input-group">
                        <input type="file" id="file2" name="file2" class="form-control input-sm" placeholder="">
                        
                      </div>
                  </div>
                  <div class="col-sm-4">
                    <input class="btn btn-sm btn-default" id="fileUploadBackgroundCheck" type="button"  value="UPLOAD & SAVE"/>
                  </div>
                </div>
              </form>
          </div>
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

<!-- Model : Employee Reject -->
    <div class="modal fade" id="rejectEmployee" tabindex="-1" role="dialog" aria-labelledby="approveRejectDocLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title text-center">Document Review</h4>
          </div>
          <div class="modal-body  text-center">
            Do you want to reject the selected Employee?
          </div>
          <div class="modal-footer  text-center">
            <button type="button" class="btn btn-primary" data-dismiss="modal" id="reject">Yes</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

<!-- Model : Employee Registration Form -->
    <div class="modal fade"  id="employee-registration-form" tabindex="-1" role="dialog" aria-labelledby="employeeRegistrationLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content formHide">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">Employee Registration Form</h4>
          </div>
          <div class="modal-body">
             
			
          
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- jQuery -->
    <script src="${context}/resources/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    <!-- Custom JS -->
    <script src="${context}/resources/js/app-custom-js.js"></script>
     <script src="${context}/resources/js/jqueryform.js"></script>
     <!-- custom alert and prompt js -->
     <script src="${context}/resources/js/bootbox.min.js"></script>
    
    <!-- Include landing page custome javascript -->
    <script src="${context}/resources/js/landingpage.js"></script>
    
   

    
 <script type="text/javascript" src="${context}/resources/js/jquery.media.js"></script>  
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
 
</body>
</html>