
var counter = 1;
var totalPages;
var fromDate;
var toDate;
var offset = 0;
var searchTerm = "";
var searchCounter = 0;
$(document).ready(function(){
	
	
	$("#previous").attr("disabled",true);
	$("#next").attr("disabled",true);
	
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
		
		
	});


	renderDashboardTable(values);

	function renderDashboardTable(values)
	{
		$("#preEmpTable>tbody>tr").remove();
		var whtml = "";
		var divhtml = "";
		
		var progressFilter = $("#progressFilter").val();
		var filterCondition;
		var chooseFilter;
		
		if(progressFilter == "RMG Not Approved"){
			filterCondition = 0;
			chooseFilter = "RMG";
		}
		else if(progressFilter == "RMG Admin Not Approved"){
			filterCondition = 0;
			chooseFilter = "RMG Admin";
		}
		else if(progressFilter == "HR Not Approved"){
			filterCondition = 1;
			chooseFilter = "HR";
		}
		else if(progressFilter == "RMG Approved"){
			filterCondition = 1;
			chooseFilter = "RMG";
		}
		else if(progressFilter == "RMG Admin Approved"){
			filterCondition = 1;
			chooseFilter = "RMG Admin";
		}
		else if(progressFilter == "HR Approved"){
			filterCondition = 0;
			chooseFilter = "HR";
		}	
				
		if(values != null){
		for(var i=0; i<values.length;i++)
		{

			if(chooseFilter == "RMG"){
				if(values[i]['rmgDoneFlag'] == filterCondition){
					
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
					/*whtml+="<td class='text-center'>";
					whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
					
					whtml+="</button>";
					whtml+="</td>";
					whtml+="<td class='text-center'><span id='downloadFiles' class='glyphicon glyphicon-floppy-save doc-icon'></span></td>";
					whtml+="<td class='text-center'><span  id='mailFiles' data-toggle='modal' data-target='#mailData' class='glyphicon glyphicon-envelope doc-icon'></span></td>";
					*/
					if(values[i]["preFlag"] == 1){
					whtml+="<td class='text-center'></td>";
					whtml+="<td class='text-center'></td>";
					}
					else
						{
						whtml+="<td class='text-center'>"+ values[i]['empId']+"</td>";
						whtml+="<td class='text-center'>"+ values[i]['email'] +"</td>";
						
						}
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
			}
			
			else if(chooseFilter == "RMG Admin"){
				if(values[i]['rmgAdminDoneFlag'] == filterCondition){
					
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
					/*whtml+="<td class='text-center'>";
					whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
					
					whtml+="</button>";
					whtml+="</td>";
					whtml+="<td class='text-center'><span id='downloadFiles' class='glyphicon glyphicon-floppy-save doc-icon'></span></td>";
					whtml+="<td class='text-center'><span  id='mailFiles' data-toggle='modal' data-target='#mailData' class='glyphicon glyphicon-envelope doc-icon'></span></td>";
					*/
					if(values[i]["preFlag"] == 1){
					whtml+="<td class='text-center'></td>";
					whtml+="<td class='text-center'></td>";
					}
					else
						{
						whtml+="<td class='text-center'>"+ values[i]['empId']+"</td>";
						whtml+="<td class='text-center'>"+ values[i]['email'] +"</td>";
						
						}
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
			}
			
			else if(chooseFilter == "HR"){
				if(values[i]['preFlag'] == filterCondition){
					
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
					/*whtml+="<td class='text-center'>";
					whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
					
					whtml+="</button>";
					whtml+="</td>";
					whtml+="<td class='text-center'><span id='downloadFiles' class='glyphicon glyphicon-floppy-save doc-icon'></span></td>";
					whtml+="<td class='text-center'><span  id='mailFiles' data-toggle='modal' data-target='#mailData' class='glyphicon glyphicon-envelope doc-icon'></span></td>";
					*/
					if(values[i]["preFlag"] == 1){
					whtml+="<td class='text-center'></td>";
					whtml+="<td class='text-center'></td>";
					}
					else
						{
						whtml+="<td class='text-center'>"+ values[i]['empId']+"</td>";
						whtml+="<td class='text-center'>"+ values[i]['email'] +"</td>";
						
						}
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
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
		
		var progressFilter = $("#progressFilter").val();
		var filterCondition;
		var chooseFilter;
		
		if(progressFilter == "RMG Not Approved"){
			filterCondition = 0;
			chooseFilter = "RMG";
		}
		else if(progressFilter == "RMG Admin Not Approved"){
			filterCondition = 0;
			chooseFilter = "RMG Admin";
		}
		else if(progressFilter == "HR Not Approved"){
			filterCondition = 1;
			chooseFilter = "HR";
		}
		else if(progressFilter == "RMG Approved"){
			filterCondition = 1;
			chooseFilter = "RMG";
		}
		else if(progressFilter == "RMG Admin Approved"){
			filterCondition = 1;
			chooseFilter = "RMG Admin";
		}
		else if(progressFilter == "HR Approved"){
			filterCondition = 0;
			chooseFilter = "HR";
		}	
				
		if(values.empList != null){
		for(var i=0; i<values.empList.length;i++)
		{

			if(chooseFilter == "RMG"){
				if(values.empList[i]['rmgDoneFlag'] == filterCondition){
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
					if(values.empList[i]["preFlag"] == 1){
						whtml+="<td class='text-center'></td>";
						whtml+="<td class='text-center'></td>";
						}
						else
							{
							whtml+="<td class='text-center'>" + values.empList[i]['empId']+"</td>";
							whtml+="<td class='text-center'>" +values.empList[i]['email']+"</td>";
							
							}
					
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
				
			}
			
			else if(chooseFilter == "RMG Admin"){
				if(values.empList[i]['rmgAdminDoneFlag'] == filterCondition){
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
					if(values.empList[i]["preFlag"] == 1){
						whtml+="<td class='text-center'></td>";
						whtml+="<td class='text-center'></td>";
						}
						else
							{
							whtml+="<td class='text-center'>" + values.empList[i]['empId']+"</td>";
							whtml+="<td class='text-center'>" +values.empList[i]['email']+"</td>";
							
							}
					
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
			}
			else if(chooseFilter == "HR"){
				if(values.empList[i]['preFlag'] == filterCondition){
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
					if(values.empList[i]["preFlag"] == 1){
						whtml+="<td class='text-center'></td>";
						whtml+="<td class='text-center'></td>";
						}
						else
							{
							whtml+="<td class='text-center'>" + values.empList[i]['empId']+"</td>";
							whtml+="<td class='text-center'>" +values.empList[i]['email']+"</td>";
							
							}
					
					whtml+="</tr>";
		
					$("#preEmpTable>tbody").append(whtml);
					counter++;
				}
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
		$.getJSON("workflowstats/"+ offset +"/"+ searchTerm , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
			
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    bootbox.alert( "Request Failed: " + err );
		});
	}
	function onPageClick(offset){
		$.getJSON("workflowstats/"+ offset  , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    bootbox.alert( "Request Failed: " + err );
		});
	}
	function onSearchByDate(offset,fromDate,toDate){
		$.getJSON("workflowstats/"+ offset + "/"+fromDate+"/"+toDate  , function(dashboardData){
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
		var tr = null;
		for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
			tr = $(landingPageTblRows[i]);
			if(tr.hasClass("checked")){
					var applicationId = tr.attr("data_applicationid");
					var data = {"applicationId" :applicationId};
						rmgAdminReject.push(data);
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
				location.reload();
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
			var emailAndWelcomeData = {"mailToList":data.mailToList,"welcomeDataList":data.welcomeDataList}
			$.ajax({
				type: 'POST',
				url: 'sendMultipleWelcomeEmail',
				data: JSON.stringify(emailAndWelcomeData),
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