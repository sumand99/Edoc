
var mailTo = [];
var mailFail = [];
var counter = 1;
var totalPages;
var fromDate;
var toDate;
var offset = 0;
var searchTerm = "";
var bgOperate = [];
var rmgAdminReject = [];
var searchCounter = 0;
var makeEmployeeData = [];
var notificationMailList = [];
$(document).ready(function(){
	
	$("#initiateBgc").attr("disabled",true);
	$("#rmgAdminReject").attr("disabled",true);
	$("#approveBgc").attr("disabled",true);
	$("#makeEmployee").attr("disabled",true);
	$("#previous").attr("disabled",true);
	$("#next").attr("disabled",true);
	$("#sendWelcomeMail").attr("disabled",true);
	$("#sendWelcomeMail").hide();
	
	$("#searchForm :input[name='searchText']").keypress(function (e) {
		 var key = e.which;
		 if(key == 13)  
		  {
		    $('#search').click();
		    
		    return false;  
		  }
		});	
	
	$("#filter").keyup(function(){
        var filter = $(this).val();
        $("#preEmpTable>tbody>tr").each(function(){
            if ($(this).text().search(new RegExp(filter, "i")) < 0) {
                $(this).fadeOut();
            } else {
                $(this).show();
            }
        });
    });
	
	$( ".filer1" ).change(function() {
		counter = 1;
		if($("#searchForm :input[name='searchText']").val() == "" && searchCounter == 0)
		renderDashboardTable(values);
		else 
			renderDashboardSearchTable(values);
	});
	
	$(document).click(function(e){
		var _target = $(e.target);
		if(_target.attr('id') == "clicked"){
			var applicationId = _target.closest('tr').attr("data_applicationId");
			window.location.href='landingpage/'+applicationId;
		}
		else if(_target.hasClass("case")){
			
			if(_target.closest("tr").hasClass("checked"))
				{
					_target.closest("tr").removeClass("checked");
				}
			else
				{
					_target.closest("tr").addClass("checked"); 
				}
		}
		else if(_target.attr("id")=="selectAll"){
			if($('#selectAll').hasClass("checked")){
				$('#preEmpTable>tbody>tr').removeClass("checked");
				$('.case').prop('checked',false);
				$('#selectAll').removeClass("checked");
			}
			else{
			$("#selectAll").addClass("checked");
			$('#preEmpTable>tbody>tr').addClass("checked");
			$('.case').prop('checked',true);
			}
		}
		else if(_target.attr('id') == "downloadFiles"){
			var applicationId = _target.closest('tr').attr("data_applicationId");
			window.location='downloadZip/'+applicationId;
		}
		else if(_target.attr('id') == "initiateBgc"){
			bootbox.confirm("initiate BGC", function(result){
				if(result)
					{
						bgOperate = [];
						onBgOperate("initiate");
					}
			});
		}
		else if(_target.attr('id') == "approveBgc"){
			bootbox.confirm("approve BGC", function(result){
				if(result)
					{
						bgOperate = [];
						onBgOperate("approve");
					}
				});
		}
		else if(_target.attr('id') == "rmgAdminReject"){
			$("#rejectionReason").modal('show');
			/*bootbox.confirm("Reject RMG Admin", function(result){
				if(result)
					{
						rmgAdminReject = [];
						$("#rejectionReason").modal('show');
						onRmgAdminReject();
					}
			});*/
		}
		
		else if(_target.attr('id') == "rmgAdminRejectButton"){
			rmgAdminReject = [];
			if($("#reasonForm :input[name = 'reason']").val()==""){
				$("#reason").focus();
				return;
			}
			onRmgAdminReject();
			$("#rejectionReason").modal('hide');
		}
		else if(_target.attr('id') == "mailFiles"){
			var applicationId = _target.closest('tr').attr("data_applicationId");
			$("#mailForm :input[name='applicationId']").attr('value',applicationId);
			$("#mailData").modal('show');
		}
		else if(_target.attr('id') == "sendMail"){
			onMail();
			
		}
		else if(_target.attr('id') == "makeEmployee"){
			makeEmployeeData = [];
			makeMultipleEmployees();
			
		}
		else if(_target.attr('id') == "sendWelcomeMail"){
			makeEmployeeData = [];
			sendWelcomeMail();
			
		}
		else if(_target.attr("id")=="search"){
			counter = 1;
			offset = 0;
			searchCounter++;
			searchTerm = $("#searchForm :input[name='searchText']").val();
			onSearch(offset,searchTerm);
		}
		else if(_target.attr("id")=="searchByDate"){
			counter = 1;
			offset = 0;
			fromDate = $("#fromDate").val();
			toDate = $("#toDate").val();
			onSearchByDate(offset,fromDate,toDate);
		}
		else if(_target.attr("id")=="next"){
			
			if(offset + 10 >= totalCount){
			
				return;
			}
			else{
				offset = offset + 10;
				counter = parseInt(offset) + 1;
			if(searchTerm == null){
				onPageClick(offset);
			}else
				onSearch(offset,searchTerm);
		}}
		else if(_target.attr("id")=="previous"){
			
			if(offset - 10 < 0){
			
				return;
			}
			else{
			offset = offset - 10;
			counter = parseInt(offset) + 1;
			if(searchTerm == null){
				onPageClick(offset);
			}else
				onSearch(offset,searchTerm);
		}}
		
		if($('#preEmpTable>tbody>tr').hasClass("checked"))
		{
			$("#initiateBgc").attr("disabled",false);
			$("#approveBgc").attr("disabled",false);
			$("#rmgAdminReject").attr("disabled",false);
			$("#makeEmployee").attr("disabled",false);
			$("#sendWelcomeMail").attr("disabled",false);
		}
		else
		{
			$("#initiateBgc").attr("disabled",true);
			$("#approveBgc").attr("disabled",true);
			$("#rmgAdminReject").attr("disabled",true);
			$("#makeEmployee").attr("disabled",true);
		
		$("#sendWelcomeMail").attr("disabled",true);
		}
		
		if($("#employeeFilter").val() == "Existing Employees"){
			$("#sendWelcomeMail").show();
			$("#makeEmployee").hide();
			
		}
		else{
			$("#sendWelcomeMail").hide();
			$("#makeEmployee").show();
		}
	});


	renderDashboardTable(values);

	function renderDashboardTable(values)
	{
		$("#preEmpTable>tbody>tr").remove();
		var whtml = "";
		var divhtml = "";
		
		var employeeFilter = $("#employeeFilter").val();
		var bgcFilter = $("#bgcFilter").val();
		var filterCondition1;
		var filterCondition2;
		
		if(employeeFilter == "Future Employees" && bgcFilter == "BGC Not Initiated"){
			filterCondition1 = 1;
			filterCondition2 = 0;
		}
		else if(employeeFilter == "Future Employees" && bgcFilter == "BGC Initiated"){
			filterCondition1 = 1;
			filterCondition2 = 2;
		}
		else if(employeeFilter == "Future Employees" && bgcFilter == "BGC Approved"){
			filterCondition1 = 1;
			filterCondition2 = 1;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Not Initiated"){
			filterCondition1 = 0;
			filterCondition2 = 0;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Initiated"){
			filterCondition1 = 0;
			filterCondition2 = 2;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Approved"){
			filterCondition1 = 0;
			filterCondition2 = 1;
		}
				
		if(values != null){
		for(var i=0; i<values.length;i++)
		{

			if(values[i]['preFlag'] == filterCondition1 && values[i]['bgcDoneFlag'] == filterCondition2){
				whtml = "";
				whtml+="<tr data_bgcDoneFlag='"+ values[i]['bgcDoneFlag'] +"' data_preFlag='"+ values[i]['preFlag'] +"' data_applicationId=" + values[i]["applicationId"] +">";
				whtml+="<td>"+ (counter) +"</td>";
				whtml+="<td>"+ values[i]["firstName"] +"</td>";
				
				if (values[i]["rmgDoneFlag"] == 0) {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
	            if (values[i]["rmgAdminDoneFlag"] == 0) {
	                whtml += " <td class='text-center rmgAdmin-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center rmgAdmin-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
				if(values[i]["preFlag"] == 1){
				whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/0.png></td>";
				}
				else {
					whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/3.png></td>";
				}
				if(values[i]["bgcDoneFlag"] == 1)
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/3.png></td>";
				}
				else if(values[i]["bgcDoneFlag"] == 0)
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/0.png></td>";
				}
				else
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/2.png></td>";
				}
				whtml+="<td class='text-center'>";
				whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
				
				whtml+="</button>";
				whtml+="</td>";
				whtml+="<td class='text-center'><span id='downloadFiles' class='glyphicon glyphicon-floppy-save doc-icon'></span></td>";
				whtml+="<td class='text-center'><span  id='mailFiles' data-toggle='modal' data-target='#mailData' class='glyphicon glyphicon-envelope doc-icon'></span></td>";
				if(values[i]["preFlag"] == 1){
				whtml+="<td class='text-center'><input class='form-control input-sm' type='text' name='employeeId'></td>";
				whtml+="<td class='text-center'><input class='form-control input-sm' type='email' name='employeeEmail'></td>";
				}
				else
					{
					whtml+="<td class='text-center'>"+ values[i]['empId']+"</td>";
					whtml+="<td class='text-center employee-email'>"+ values[i]['email'] +"</td>";
					
					}
				whtml += "<td class='text-center' ><input type='checkbox' class='case'></td>";
				whtml+="</tr>";
	
				$("#preEmpTable>tbody").append(whtml);
				counter++;
			}
		}

		
	}
		if(totalCount > 10)
		{	
			$("#previous").attr("disabled",false);
			$("#next").attr("disabled",false);
		}
		else
		{
			$("#previous").attr("disabled",true);
			$("#next").attr("disabled",true);
		}
		
	}

	function renderDashboardSearchTable(values)
	{
		$("#preEmpTable>tbody>tr").remove();
		var whtml = "";
		var divhtml = "";
		
		var employeeFilter = $("#employeeFilter").val();
		var bgcFilter = $("#bgcFilter").val();
		var filterCondition1;
		var filterCondition2;
		
		if(employeeFilter == "Future Employees" && bgcFilter == "BGC Not Initiated"){
			filterCondition1 = 1;
			filterCondition2 = 0;
		}
		else if(employeeFilter == "Future Employees" && bgcFilter == "BGC Initiated"){
			filterCondition1 = 1;
			filterCondition2 = 2;
		}
		else if(employeeFilter == "Future Employees" && bgcFilter == "BGC Approved"){
			filterCondition1 = 1;
			filterCondition2 = 1;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Not Initiated"){
			filterCondition1 = 0;
			filterCondition2 = 0;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Initiated"){
			filterCondition1 = 0;
			filterCondition2 = 2;
		}
		else if(employeeFilter == "Existing Employees" && bgcFilter == "BGC Approved"){
			filterCondition1 = 0;
			filterCondition2 = 1;
		}
				
		if(values.empList != null){
		for(var i=0; i<values.empList.length;i++)
		{

			if(values.empList[i]['preFlag'] == filterCondition1 && values.empList[i]['bgcDoneFlag'] == filterCondition2){
				whtml = "";
				whtml+="<tr data_bgcDoneFlag='"+ values.empList[i]['bgcDoneFlag'] +"' data_preFlag='"+ values.empList[i]['preFlag'] +"' data_applicationId=" + values.empList[i]["applicationId"] +">";
				whtml+="<td>"+ (counter) +"</td>";
				whtml+="<td>"+ values.empList[i]["firstName"] +"</td>";
				/*whtml+="<td></td>";
				whtml+="<td></td>";
				whtml+="<td class='text-center'></td>";*/
				if (values.empList[i]["rmgDoneFlag"] == 0) {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
	            if (values.empList[i]["rmgAdminDoneFlag"] == 0) {
	                whtml += " <td class='text-center rmgAdmin-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center rmgAdmin-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
				if(values.empList[i]["preFlag"] == 1){
				whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/0.png></td>";
				}
				else {
					whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/3.png></td>";
				}
				if(values.empList[i]["bgcDoneFlag"] == 1)
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/3.png></td>";
				}
				else if(values.empList[i]["bgcDoneFlag"] == 0)
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/0.png></td>";
				}
				else
				{
					whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/2.png></td>";
				}
				whtml+="<td class='text-center'>";
				whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
				
				whtml+="</button>";
				whtml+="</td>";
				whtml+="<td class='text-center'><span id='downloadFiles' class='glyphicon glyphicon-floppy-save doc-icon'></span></td>";
				whtml+="<td class='text-center'><span  id='mailFiles' data-toggle='modal' data-target='#mailData' class='glyphicon glyphicon-envelope doc-icon'></span></td>";
				if(values.empList[i]["preFlag"] == 1){
					whtml+="<td class='text-center'><input class='form-control input-sm' type='text' name='employeeId'></td>";
					whtml+="<td class='text-center'><input class='form-control input-sm' type='email' name='employeeEmail'></td>";
					}
					else
						{
						whtml+="<td class='text-center'>" + values.empList[i]['empId']+"</td>";
						whtml+="<td class='text-center employee-email' >" +values.empList[i]['email']+"</td>";
						
						}
				whtml += "<td class='text-center' ><input type='checkbox' class='case'></td>";
				whtml+="</tr>";
	
				$("#preEmpTable>tbody").append(whtml);
				counter++;
			}
		}
		totalCount = values.totalCount;
		
	}
		
		if(totalCount > 10)
		{	
			$("#previous").attr("disabled",false);
			$("#next").attr("disabled",false);
		}
		else
		{
			$("#previous").attr("disabled",true);
			$("#next").attr("disabled",true);
		}
		
		
	}
	function onSearch(offset,searchTerm){
		$.getJSON("dashboard/"+ offset +"/"+ searchTerm , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
			
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    bootbox.alert( "Request Failed: " + err );
		});
	}
	function onPageClick(offset){
		$.getJSON("dashboard/"+ offset  , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    bootbox.alert( "Request Failed: " + err );
		});
	}
	function onSearchByDate(offset,fromDate,toDate){
		$.getJSON("dashboard/"+ offset + "/"+fromDate+"/"+toDate  , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    bootbox.alert( "Request Failed: " + err );
		});
	}
	
	function onBgOperate(bgStatus){
		var url;

		if(bgStatus == "approve")
		{
			url = 'approveBgc';
		}
		else if(bgStatus == "initiate")
		{
			url = 'initiateBgc';
		}
		var landingPageTblRows = $("#preEmpTable>tbody>tr");
		var tr = null;
		for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
			tr = $(landingPageTblRows[i]);
			if(tr.hasClass("checked")){
				if(tr.attr('data_bgcdoneflag')=='1'){
					bootbox.alert("Background check is already done for one of the selected employee.");
					return;
				}
				else if(tr.attr('data_bgcdoneflag')=='2' && bgStatus == "initiate"){
					bootbox.alert("Background check is already initiated for one of the selected employee.");
					return;
				}
				
				var applicationId = tr.attr("data_applicationid");
				var data = {"applicationId" :applicationId};
				bgOperate.push(data);
			}
		}
		$.ajax({
			type: 'POST',
			url: url,
			data: JSON.stringify(bgOperate),
			dataType: 'json',
			contentType: 'application/json',
			success:function(data){

				var json = data;
				var landingPageTblRows = $("#preEmpTable>tbody>tr");
				var tr = null;
				if(bgStatus == "approve")
				{
					for(var m=0;m<json.bgcApprovedList.length;m++){
						for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
							tr = $(landingPageTblRows[i]);
							if(tr.hasClass("checked"))
							{
								if(tr.attr("data_applicationId") == json.bgcApprovedList[m]["applicationId"]){
									tr.find("td.bgc-flag").html("<img src='"+contextPath+"/resources/images/3.png'>");
									
								}
							}
						}
					}
					
					$("#search").click();
					/*for(var m = 0;m<values.length;m++){
						for(var n = 0;json.bgcApprovedList.length; n++){
							if(values[m]['applicationId'] == json.bgcApprovedList[n]['applicationId']){
								values[m]["bgcDoneFlag"] = json.bgcApprovedList[n]["bgcDoneFlag"];
							}
						}
					}*/
				}
				else if(bgStatus == "initiate")
				{
					for(var m=0;m<json.bgcInitiatedList.length;m++){
						for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
							tr = $(landingPageTblRows[i]);
							if(tr.hasClass("checked"))
							{
								if(tr.attr("data_applicationId") == json.bgcInitiatedList[m]["applicationId"]){
									tr.find("td.bgc-flag").html("<img src='"+contextPath+"/resources/images/2.png'>");
								}


							}
						}
					}
					$("#search").click();
					/*for(var m = 0;m<values.length;m++){
						for(var n = 0;json.bgcInitiatedList.length; n++){
							if(values[m]['applicationId'] == json.bgcInitiatedList[n]['applicationId']){
								values[m]["bgcDoneFlag"] = json.bgcInitiatedList[n]["bgcDoneFlag"];
							}
						}
					}*/
				}

			},
			error:function(data){
				bootbox.alert(data.statusText);
			}
		}); 

	}
	
	function onRmgAdminReject(){
		var landingPageTblRows = $("#preEmpTable>tbody>tr");
		var reason = $("#reason").val();
		var tr = null;
		for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
			tr = $(landingPageTblRows[i]);
			if(tr.hasClass("checked")){
					var applicationId = tr.attr("data_applicationid");
					var data = {"applicationId" :applicationId};
						rmgAdminReject.push(data);
						notificationMailList.push(applicationId);
				}
			}
		$.ajax({
			type: 'POST',
			url: 'rejectRmgAdmin',
			data: JSON.stringify(rmgAdminReject),
			dataType: 'json',
			contentType: 'application/json',
			success:function(data){

				var json = data;
				var landingPageTblRows = $("#preEmpTable>tbody>tr");
				var tr = null;
				for(var m=0;m<json.rmgAdminRejectedList.length;m++){
					for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
						tr = $(landingPageTblRows[i]);
						if(tr.hasClass("checked")){
							if(tr.attr("data_applicationId") == json.rmgAdminRejectedList[m]["applicationId"]){
								tr.find("td.rmgAdmin-flag").html("<img src='"+contextPath+"/resources/images/0.png'>");
							}
							
						}}
						
				} 
				processNotificationMail = {"applicationIdList":notificationMailList, "status":3,"reason" : reason};
				$.ajax({
					type: 'POST',
					url: 'sendNotificationMail',
					data: JSON.stringify(processNotificationMail),
					dataType: 'json',
					contentType: 'application/json',
					success:function(data){
						location.reload();
					},
					error:function(data){
						alert(data.statusText);
					}
				});
			},
			error:function(data){
				alert(data.statusText);
			}
		}); 
		
		
	}

	function onMail(){
		var to = $("#mailForm :input[name='to']").val();
		
		emails = to.split(";");
		for(i=0;i<emails.length;i++){
			if(isEmail(emails[i])){
				mailTo.push(emails[i]);				
			}
			else
				{
				mailFail.push(emails[i]);
				}
		}
		var subject = $("#mailForm :input[name='subject']").val();
		var applicationId = $("#mailForm :input[name='applicationId']").val();
		var data = {"mailTo":mailTo, "mailSubject":subject,"applicationId":parseInt(applicationId) };
		$.ajax({
			type: 'POST',
			url: 'sendmail',
			data: JSON.stringify(data),
			dataType: 'json',
			contentType: 'application/json',
			success:function(data){
				var json = data;
				bootbox.alert(json.message + "to following emails successfully: " + mailTo);
				if(mailFail != [])
				bootbox.alert("Following emails are incorrect:" + mailFail );
				$("#mailData").modal('hide');	
				
			},
			error:function(data){
				bootbox.alert(data.statusText);
			}
		}); 
		
	}
});

function makeMultipleEmployees(){
	var landingPageTblRows = $("#preEmpTable>tbody>tr");
	var tr = null;
	for(var i = 0; i<landingPageTblRows.length;i++){
		tr = $(landingPageTblRows[i]);
		if(tr.hasClass("checked")){
			if(tr.attr('data_preflag')==0){
				bootbox.alert("one of the selected row is already an employee.");
				return;
			}
			var applicationId = tr.attr("data_applicationId");
			var employeeId = $(tr).find("td").find("input[name='employeeId']").val();
			var employeeEmail = $(tr).find("td").find("input[name='employeeEmail']").val();
			if(employeeId != "" && isEmail(employeeEmail)){
				data = {"empId":employeeId, "email":employeeEmail, "applicationId":applicationId};
				makeEmployeeData.push(data);
			}
			else {
				bootbox.alert("invalid input");
				return;
			}
		}
		
	}
	$.ajax({
		type: 'POST',
		url: 'makeMultipleEmployees',
		data: JSON.stringify(makeEmployeeData),
		dataType: 'json',
		contentType: 'application/json',
		success:function(data){
			bootbox.alert("Employee(s) added.");
			$("#search").click();
		},
		error:function(data){
			bootbox.alert(data.statusText);
		}
	});
}
function sendWelcomeMail(){
	var landingPageTblRows = $("#preEmpTable>tbody>tr");
	var tr = null;
	for(var i = 0; i<landingPageTblRows.length;i++){
		tr = $(landingPageTblRows[i]);
		if(tr.hasClass("checked")){
			
			var applicationId = tr.attr("data_applicationId");
			var employeeId = $(tr).find("td").find("input[name='employeeId']").val();
			var employeeEmail = $(tr).find("td.employee-email").text();
				data = {"empId":employeeId, "email":employeeEmail, "applicationId":applicationId};
				makeEmployeeData.push(data);
		}
	}
	$.ajax({
		type: 'POST',
		url: 'sendMultipleWelcomeEmail',
		data: JSON.stringify(makeEmployeeData),
		dataType: 'json',
		contentType: 'application/json',
		success:function(data){
			bootbox.alert("welcome emails sent");
			$("#search").click();
		},
		error:function(data){
			bootbox.alert(data.statusText);
		}
		});
	
}

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}