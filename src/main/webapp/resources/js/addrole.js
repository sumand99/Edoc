$(document).ready(function(){
	
	
	
	for(var i=0;i<values.length;i++){
		var option = $("<option>").val(values[i]).text(values[i]);
		$("#employee-id").append(option);
	}
	
	for(var i=0;i<rmgList.length;i++){
		var option = $("<option>").val(rmgList[i]).text(rmgList[i]);
		$("#rmg").append(option);
	}
	
	for(var i=0;i<rmgAdminList.length;i++){
		var option = $("<option>").val(rmgAdminList[i]).text(rmgAdminList[i]);
		$("#rmgAdmin").append(option);
	}
	
	for(var i=0;i<hrList.length;i++){
		var option = $("<option>").val(hrList[i]).text(hrList[i]);
		$("#hr").append(option);
	}
	
	$("#employee-id").autocomplete( { source: values });
	$(document).click(function(event){
		var _target = $(event.target);
		if(_target.attr("id")=="add-role"){
			if($("#employee-id").val()==""){
				$("#employee-id").focus();
				
			}
			else{
				var employeeId = $("#employee-id").val();
				var role = $("#role :selected").val();
				var data = {"employeeId":employeeId,"role":role};
				$.ajax({
					type : "post",
					url : "addNewRole",
					 contentType: 'application/json',
					data : JSON.stringify(data),
					success:function(data){
						bootbox.alert("Role Added.");
					},
					error : function(data){
						bootbox.alert("Something Happened. Please try again.");
					}
				});
			}
		}
		else if(_target.attr("id")=="remove-rmg"){
			var username =  $("#rmg :selected").val();
			remove(4, username);
		}
		else if(_target.attr("id")=="remove-rmg-admin"){
			var username =  $("#rmgAdmin :selected").val();
			remove(5, username);
		}
		else if(_target.attr("id")=="remove-hr"){
			var username =  $("#hr :selected").val();
			remove(1, username);
		}
	})
})

function remove(role, username){
	var data ={"role":role, "employeeId":username};
	$.ajax({
		type : "post",
		url : "removeUserRole",
		 contentType: 'application/json',
		data : JSON.stringify(data),
		success:function(data){
			bootbox.alert("role removed");
		},
		error:function(data){
			bootbox.alert("something happened. Try again.");
		}
	
		
	})
}