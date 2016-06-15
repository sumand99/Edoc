var mailTo = [];
$(document).ready(function() {
	
	$("#registrationForm").submit(function(e){
		mailTo  =[];
	onRegister(e);
	});
	
	/*$(document).click(function(e){
		var _target = $(e.target);
		
		if(_target.attr("id")=="register"){
			if($("#registrationForm :input[name='username']").val() == ""){
				$("#registrationForm :input[name='username']").focus();
				return;
			}
			else if($("#registrationForm :input[name='password']").val() == ""){
				$("#registrationForm :input[name='password']").focus();
				return;
			}
			else if($("#registrationForm :input[name='firstName']").val() == ""){
				$("#registrationForm :input[name='firstName']").focus();
				return;
			}
			else if($("#registrationForm :input[name='lastName']").val() == ""){
				$("#registrationForm :input[name='lastName']").focus();
				return;
			}
			else if($("#registrationForm :input[name='address']").val() == ""){
				$("#registrationForm :input[name='address']").focus();
				return;
			}
			else if($("#registrationForm :input[name='email']").val() == ""){
				$("#registrationForm :input[name='email']").focus();
				return;
			}
			else if($("#registrationForm :input[name='contact']").val() == ""){
				$("#registrationForm :input[name='contact']").focus();
				return;
			}
			else if($("#registrationForm :input[name='zip']").val() == ""){
				$("#registrationForm :input[name='zip']").focus();
				return;
			}
			else if($("#registrationForm :input[name='password']").val() != $("#registrationForm :input[name='rePassword']").val()){
				bootbox.alert("password and re-enter password do not match.");
				return;
			}
			
			onRegister();
		}
	});*/
	
	
	function onRegister(e){
		
		var username = $("#registrationForm :input[name='username']").val();
		var password = $("#registrationForm :input[name='password']").val();
		var firstName = $("#registrationForm :input[name='firstName']").val();
		var lastName = $("#registrationForm :input[name='lastName']").val();
		var address = $("#registrationForm :input[name='address']").val();
		var to = $("#registrationForm :input[name='email']").val();
		
		emails = to.split(";");
		for(i=0;i<emails.length;i++){
			if(isEmail(emails[i])){
				mailTo.push(emails[i]);				
			}
			else
				{
				bootbox.alert("Entered email is invalid.");
				return;
				}
		}
		
		var contact = $("#registrationForm :input[name='contact']").val();
		var zip = $("#registrationForm :input[name='zip']").val();
	
		var data = {"username" : username, "password":password, "firstName":firstName, "lastName":lastName, "address":address, "email":mailTo, "contact":contact, "zip":parseInt(zip) };
		
		$.ajax({
			type: 'POST',
			url: 'userRegistration',
			data: JSON.stringify(data),
			contentType: 'application/json',
			success:function(data){
				
				if(data.status != null)
				{
					var mailData = {"mailTo":mailTo, "mailSubject":"Registration Successful","mailContent":" Hi, your username is "+ data.status.username + " and password is "+ data.status.password +"" };
					$.ajax({
						type: 'POST',
						url: 'sendRegisterMail',
						data: JSON.stringify(mailData),
						dataType: 'json',
						contentType: 'application/json',
						success:function(data){
							
							bootbox.alert("Mail has been sent on given Email with the login credentials");	
							
						},
						error:function(data){
							bootbox.alert(data.statusText);
						}
					});
				}
				else
				{
					bootbox.alert("Could not Register");
				}
			},
			error:function(data)
			{
				if(data.status == 406)
				{
					bootbox.alert("Email Already Exists");
				}
				else
					bootbox.alert(data.statusText);
			}
			
		});
		
		e.preventDefault();
	}
});

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}