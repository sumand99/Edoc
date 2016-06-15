$(document).ready(function(){
	
$(document).click(function(e){
	var _target = $(e.target);
	
	$("#loginForm :input[name = 'password']").keypress(function (e) {
		 var key = e.which;
		 if(key == 13)  
		  {
		    $('#login').click();
		    
		    return false;  
		  }
		});	
	
	if(_target.attr('id')=="login"){
		if($("#loginForm :input[name = 'username']").val()==""){
			$("#userName").focus();
			return;
		}
		else if($("#loginForm :input[name = 'password']").val()==""){
			$("#userPassword").focus();
			return;
		}
		else if($("#loginForm :input[name = 'username']").val()!="" && $("#loginForm :input[name = 'password']").val()!="")
			{
			
			$("#loginForm").submit();
			}
	}
	
});	
	

});