var counter = 1;
var totalPages;
var fromDate;
var toDate;
var offset = 0;
var searchTerm = "";
var rmgapprove = [];
var notificationMailList = [];
$(document).ready(function(){

	$("#rmg-approve").attr("disabled",true);
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
	
	$(document).click(function(e){
		var _target = $(e.target);
		if(_target.attr('id') == "clicked"){
			var applicationId = _target.closest('tr').attr("data_applicationId");
			window.location.href='landingpage/'+applicationId;
		}
		else if(_target.hasClass("case")){
			if(_target.closest("tr").hasClass("checked"))
				_target.closest("tr").removeClass("checked");
			else
				_target.closest("tr").addClass("checked"); 
		}
		else if(_target.attr("id")=="rmgApprove"){
			rmgapprove.length = 0;
			onRmgApprove(_target);
			
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
			$("#rmg-approve").attr("disabled",false);	
		}
		else
		{
			$("#rmg-approve").attr("disabled",true);
		}
	});


	renderDashboardTable(values);

	function renderDashboardTable(values) {
	    $("#preEmpTable>tbody>tr").remove();
	    var whtml = "";
	    var divhtml = "";
	    if (values != null) {
	        for (var i = 0; i < values.length; i++) {

	            whtml = "";
	            whtml += "<tr data_applicationId=" + values[i]["applicationId"] + ">";
	            whtml += "<td>" + (counter) + "</td>";
	            whtml += "<td>" + values[i]["firstName"] + "</td>";
	           /* whtml += "<td></td>";
	            whtml += "<td></td>";
	            whtml += "<td></td>";

	            whtml += "<td class='text-center'></td>";*/
	           /* if (values[i]["preFlag"] == 1) {
	                whtml += " <td class='text-center'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }*/
	            if (values[i]["rmgDoneFlag"] == 0) {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
	            /*if (values[i]["rmgAdminDoneFlag"] == 0) {
	                whtml += " <td class='text-center'><img src=" + contextPath + "/resources/images/0.png></td>";
	            } else {
	                whtml += " <td class='text-center'><img src=" + contextPath + "/resources/images/3.png></td>";
	            }
	            if (values[i]["bgcDoneFlag"] == 1) {
	                whtml += "<td class='text-center bgc-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
	            } else {
	                whtml += "<td class='text-center bgc-flag'><img src=" + contextPath + "/resources/images/2.png></td>";
	            }*/
	            whtml += "<td>";
	            whtml += "<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";

	            whtml += "</button>";
	            whtml += "</td>";
	            whtml += "<td class='text-center' ><input type='checkbox' class='case'></td>";
	            whtml += "</tr>";

	            $("#preEmpTable>tbody").append(whtml);
	            counter++;
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
		if(values.empList != null){
		for(var i=0; i<values.empList.length;i++)
		{
			
			whtml = "";
			whtml+="<tr data_applicationId=" + values.empList[i]["applicationId"] +">";
			whtml+="<td>"+ (counter) +"</td>";
			whtml+="<td>"+ values.empList[i]["firstName"] +"</td>";
			/*whtml+="<td></td>";
			whtml+="<td></td>";
			whtml+="<td></td>";*/
			
			/*whtml+="<td class='text-center'></td>";*/
			/*if(values.empList[i]["preFlag"] == 1){
				whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/0.png></td>";
				}
				else {
					whtml+=" <td class='text-center'><img src="+ contextPath +"/resources/images/3.png></td>";
				}*/
			if (values.empList[i]["rmgDoneFlag"] == 0) {
                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/0.png></td>";
            } else {
                whtml += " <td class='text-center rmg-flag'><img src=" + contextPath + "/resources/images/3.png></td>";
            }
			/*if(values.empList[i]["bgcDoneFlag"] == 1)
			{
				whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/3.png></td>";
			}
			else
			{
				whtml+="<td class='text-center bgc-flag'><img src="+ contextPath +"/resources/images/2.png></td>";
			}*/
			whtml+="<td>";
			whtml+="<button id='clicked' type='button' class='btn  btn-link btn-xs glyphicon glyphicon-pencil'>";
			
			whtml+="</button>";
			whtml+="</td>";
			whtml+="<td class='text-center'><input type='checkbox' class='case'></td>";
			whtml+="</tr>";

			$("#preEmpTable>tbody").append(whtml);
			counter++;
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
		$.getJSON("rmgdashboard/"+ offset +"/"+ searchTerm , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    console.log( "Request Failed: " + err );
		});
	}
	function onPageClick(offset){
		$.getJSON("rmgdashboard/"+ offset  , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    console.log( "Request Failed: " + err );
		});
	}
	function onSearchByDate(offset,fromDate,toDate){
		$.getJSON("rmgdashboard/"+ offset + "/"+fromDate+"/"+toDate  , function(dashboardData){
			values = dashboardData;
			renderDashboardSearchTable(values);
		}).fail(function( jqxhr, textStatus, error ) {
		    var err = textStatus;
		    console.log( "Request Failed: " + err );
		});
	}

});




function onRmgApprove(target){
	var landingPageTblRows = $("#preEmpTable>tbody>tr");
	var tr = null;
	for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
		tr = $(landingPageTblRows[i]);
		if(tr.hasClass("checked")){
				var applicationId = tr.attr("data_applicationid");
				var data = {"applicationId" :applicationId};
					rmgapprove.push(data);
					notificationMailList.push(applicationId);
			}
		}
	$.ajax({
		type: 'POST',
		url: 'approveRmg',
		data: JSON.stringify(rmgapprove),
		dataType: 'json',
		contentType: 'application/json',
		success:function(data){

			var json = data;
			var landingPageTblRows = $("#preEmpTable>tbody>tr");
			var tr = null;
			for(var m=0;m<json.rmgApprovedList.length;m++){
				for(var i =0 ; i <landingPageTblRows.length ; i ++ ){
					tr = $(landingPageTblRows[i]);
					if(tr.hasClass("checked")){
						if(tr.attr("data_applicationId") == json.rmgApprovedList[m]["applicationId"]){
							tr.find("td.rmg-flag").html("<img src='"+contextPath+"/resources/images/3.png'>");
						}
						
					}}
					
			} 

			processNotificationMail = {"applicationIdList":notificationMailList, "status":0};
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
			bootbox.alert(data.statusText);;
		}
	}); 
	
	
}