var employeeEducationalBackground = [];
var employeeTrainingDetail = [];
var employeeEmploymentDetail = [];
var bgcEducationDetail = [];
var bgcEmploymentDetail = [];

var familyData = [];
var referenceData = [];
var otherData = [];
var skills;
var skillData=[];
$(document).ready(function(){
	
	$("#tabs").find("li").addClass("disabled");
	$("#tabs").find("li:eq(0)").removeClass("disabled");
	
	$("#tabs li").on("click", function(e) {
		  if ($(this).hasClass("disabled")) {
			  $("#tabs a[data-toggle=tab]").attr('href','');
			  $("#tabs a[data-toggle=tab]:eq(0)").attr('href','#1');
		    e.preventDefault();
		    return false;
		  }
		});
	
	$.ajax({
		  dataType: "json",
		  url: "getSkills",
		  success: function(data){
			 skills = data.skills;
			 var skillName = null;
			 for(i=0;i<$(".skill-name").length;i++){
				 skillName = $(".skill-name")[i];
				 skillId = $(".skill-id")[i];
				 $(skillName).text(skills[i]["skillName"]);
				 $(skillId).attr("data_skillId", skills[i]["skillId"]);
			 }
			 populateEducationFormData(values);
				enterAlreadyEnteredData(values);
		  },
		  error:function(data){
			  
		  }
		});
	
	
	
	$(document).click(function(event){
		var _target = $(event.target);
	if(_target.attr("id")=="savePersonalDetail"){
		savePersonalDetail();
		
	}
	else if(_target.attr("id")=="saveEducationalQualification"){
		employeeEducationalBackground = [];
		employeeTrainingDetail = [];
		saveEducationalQualification();
	}
	else if(_target.attr("id")=="saveEmploymentDetail"){
		employeeEmploymentDetail = [];
		saveEmploymentDetail();
	}
	else if(_target.attr("id")=="saveSalaryDetail"){
		saveSalaryDetail();
	}
	else if(_target.attr("id")=="saveSkillDetail"){
		skillData =[];
		saveSkillDetail();
	}
	else if(_target.attr("id")=="saveOtherDetail"){
		familyData = [];
		referenceData = [];
		otherDetail = [];
		otherData = [];
		saveOtherDetail();
	}
	else if(_target.attr("id")=="saveBgcDetail"){
		bgcEducationDetail = [];
		bgcEmploymentDetail = [];
		saveBgcDetail();
	}
	});
});

function enterAlreadyEnteredData(values)
{
	
	if((values.editFlag == 0 && values.totalEditFlag == 0) || role != "ROLE_USER" ){
		$(document).find(":input").attr("disabled",true);
	}
	else{
		$(document).find(":input").attr("disabled",false);		
	}
		
	if(values.backgroundCheckPersonalDetail != null){
		var bgcPersonalDetail = values.backgroundCheckPersonalDetail;
		
		/*Background Personal Details*/
		$("#bgcDetail").find("#nameOfApplicant").attr("value",bgcPersonalDetail.nameOfApplicant);
		$("#bgcDetail").find("#dateOfBirth").attr("value",bgcPersonalDetail.dateOfBirth);
		$("#bgcDetail").find("#placeOfBirth").attr("value",bgcPersonalDetail.placeOfBirth);
		$("#bgcDetail").find("#sex").find(":contains('"+bgcPersonalDetail.sex +"')").attr("selected", "selected");
		$("#bgcDetail").find("#nationality").attr("value",bgcPersonalDetail.nationality);
		$("#bgcDetail").find("#fatherName").attr("value",bgcPersonalDetail.fatherName);
		$("#bgcDetail").find("#passportNumber").attr("value",bgcPersonalDetail.passportNumber);
		$("#bgcDetail").find("#homePhone").attr("value",bgcPersonalDetail.homePhone);
		$("#bgcDetail").find("#officePhone").attr("value",bgcPersonalDetail.officePhone);
		$("#bgcDetail").find("#mobile").attr("value",bgcPersonalDetail.mobile);
		$("#bgcDetail").find("#permanentAddress").text(bgcPersonalDetail.permanentAddress);
		$("#bgcDetail").find("#permanentCity").attr("value",bgcPersonalDetail.permanentCity);
		$("#bgcDetail").find("#permanentState").attr("value",bgcPersonalDetail.permanentState);
		$("#bgcDetail").find("#permanentTelephone").attr("value",bgcPersonalDetail.permanentTelephone);
		$("#bgcDetail").find("#permanentNatureOfLocation").attr("value",bgcPersonalDetail.permanentNatureOfLocation);
		$("#bgcDetail").find("#permanentDurationFrom").attr("value",bgcPersonalDetail.permanentDurationFrom);
		$("#bgcDetail").find("#permanentDurationTo").attr("value",bgcPersonalDetail.permanentDurationTo);
		$("#bgcDetail").find("#permanentPinCode").attr("value",bgcPersonalDetail.permanentPinCode);
		$("#bgcDetail").find("#presentAddress").text(bgcPersonalDetail.presentAddress);
		$("#bgcDetail").find("#presentCity").attr("value",bgcPersonalDetail.presentCity);
		$("#bgcDetail").find("#presentState").attr("value",bgcPersonalDetail.presentState);
		$("#bgcDetail").find("#presentTelephone").attr("value",bgcPersonalDetail.presentTelephone);
		$("#bgcDetail").find("#presentNatureOfLocation").attr("value",bgcPersonalDetail.presentNatureOfLocation);
		$("#bgcDetail").find("#presentDurationFrom").attr("value",bgcPersonalDetail.presentDurationFrom);
		$("#bgcDetail").find("#presentDurationTo").attr("value",bgcPersonalDetail.presentDurationTo);
		$("#bgcDetail").find("#presentPinCode").attr("value",bgcPersonalDetail.presentPinCode);
		
		$("#personalDetail").find("#name").attr("value",bgcPersonalDetail.nameOfApplicant);
		$("#personalDetail").find("#dob").attr("value",bgcPersonalDetail.dateOfBirth);
		$("#personalDetail").find("#permanentAddress").text(bgcPersonalDetail.permanentAddress);
		$("#personalDetail").find("#presentAddress").text(bgcPersonalDetail.presentAddress);
		$("#personalDetail").find("#permanentTelephone").attr("value",bgcPersonalDetail.permanentTelephone);
		$("#personalDetail").find("#presentAddressTelephone").attr("value",bgcPersonalDetail.presentTelephone);
		
		if(bgcPersonalDetail.iAgree == 'yes'){
			$("#bgcDetail").find("#iAgree").attr("checked",true);
			$("#tabs").find("li").removeClass("disabled");
		}
			
		
	}
	
	if(values.backgroundCheckEducationalDetail != null){
		var bgcEducationalDetail = values.backgroundCheckEducationalDetail;
		
		var educationalBackgroundDivs1 = $("#bgcDetail").find(".education-data");
		var educationalBackgroundDivs2 = $("#educationalBackground").find(".education-data");
		
		for(i=0; i<bgcEducationalDetail.length; i++){
			
			var div = null;
			var data;
			div = $(educationalBackgroundDivs1[i]);
			div.find(":input[name='examinationPassed']").find(":contains('"+bgcEducationalDetail[i].examinationPassed +"')").attr("selected", "selected");
			div.find(":input[name='fromToDate']").attr("value",bgcEducationalDetail[i].fromToDate);
			div.find(":input[name='institution']").find(":contains('"+bgcEducationalDetail[i].institution +"')").attr("selected", "selected");
			div.find(":input[name='board']").find(":contains('"+bgcEducationalDetail[i].board +"')").attr("selected", "selected");
			div.find(":input[name='marks']").attr("value",bgcEducationalDetail[i].marks);
			div.find(":input[name='courseAttended']").find(":contains('"+bgcEducationalDetail[i].courseAttended +"')").attr("selected", "selected");
			div.find(":input[name='rollNumber']").attr("value",bgcEducationalDetail[i].rollNumber);
			div.find(":input[name='discipline']").find(":contains('"+bgcEducationalDetail[i].discipline +"')").attr("selected", "selected");
			
		}
		for(i=0; i<bgcEducationalDetail.length; i++){
			
			var div = null;
			var data;
			div = $(educationalBackgroundDivs2[i]);
			div.find(":input[name='examinationPassed']").find(":contains('"+bgcEducationalDetail[i].examinationPassed +"')").attr("selected", "selected");
			div.find(":input[name='fromToDate']").attr("value",bgcEducationalDetail[i].fromToDate);
			div.find(":input[name='institution']").find(":contains('"+bgcEducationalDetail[i].institution +"')").attr("selected", "selected");
			div.find(":input[name='board']").find(":contains('"+bgcEducationalDetail[i].board +"')").attr("selected", "selected");
			div.find(":input[name='marks']").attr("value",bgcEducationalDetail[i].marks);
		}	
	}
	
	if(values.backgroundCheckEmploymentDetail != null){
		var bgcEmploymentDetail = values.backgroundCheckEmploymentDetail;
		
		var columns;
		var columns2;
		
		for(i=0; i<bgcEmploymentDetail.length; i++){
			
			if(i == 0){
				columns = $(".employer1");
				columns2 = $(".present-employer");
			}
			else if(i == 1){
				columns = $(".employer2");
				columns2 = $(".previous-employer");
			}
			
			var employerName = $(columns).find(":input[name='employerName']");
			$(employerName).attr("value",bgcEmploymentDetail[i].employerName);
			employerName = $(columns2).find(":input[name='employerName']");
			$(employerName).attr("value",bgcEmploymentDetail[i].employerName);
			
			
			var employeeId = $(columns).find(":input[name='employeeId']");
			$(employeeId).attr("value",bgcEmploymentDetail[i].employeeId);
			
			var employmentFrom = $(columns).find(":input[name='employmentFrom']");
			$(employmentFrom).attr("value",bgcEmploymentDetail[i].employmentFrom);
			
			var employmentTo = $(columns).find(":input[name='employmentTo']");
			$(employmentTo).attr("value",bgcEmploymentDetail[i].employmentTo);
			
			var streetAddress = $(columns).find(":input[name='streetAddress']");
			$(streetAddress).attr("value",bgcEmploymentDetail[i].streetAddress);
			
			var employerCity = $(columns).find(":input[name='employerCity']");
			$(employerCity).attr("value",bgcEmploymentDetail[i].employerCity);
			
			var employerState = $(columns).find(":input[name='employerState']");
			$(employerState).attr("value",bgcEmploymentDetail[i].employerState);
			
			var employerCountry = $(columns).find(":input[name='employerCountry']");
			$(employerCountry).attr("value",bgcEmploymentDetail[i].employerCountry);
			
			var employerPostalCode = $(columns).find(":input[name='employerPostalCode']");
			$(employerPostalCode).attr("value",bgcEmploymentDetail[i].employerPostalCode);
			
			var employerTelephone = $(columns).find(":input[name='employerTelephone']");
			$(employerTelephone).attr("value",bgcEmploymentDetail[i].employerTelephone);
			
			var remuneration = $(columns).find(":input[name='remuneration']");
			$(remuneration).attr("value",bgcEmploymentDetail[i].remuneration);
			
			var designationHeld = $(columns).find(":input[name='designationHeld']");
			$(designationHeld).attr("value",bgcEmploymentDetail[i].designationHeld);
			designationHeld = $(columns2).find(":input[name='designationHeld']");
			$(designationHeld).attr("value",bgcEmploymentDetail[i].designationHeld);
			
			var employmentStatus = $(columns).find(":input[name='employmentStatus']");
			$(employmentStatus).attr("value",bgcEmploymentDetail[i].employmentStatus);
			
			var reasonForLeaving = $(columns).find(":input[name='reasonForLeaving']");
			$(reasonForLeaving).attr("value",bgcEmploymentDetail[i].reasonForLeaving);
			reasonForLeaving = $(columns2).find(":input[name='reasonForLeaving']");
			$(reasonForLeaving).attr("value",bgcEmploymentDetail[i].reasonForLeaving);
			
			var outsourcingAgencyName = $(columns).find(":input[name='outsourcingAgencyName']");
			$(outsourcingAgencyName).attr("value",bgcEmploymentDetail[i].outsourcingAgencyName);
			
			var outsourcingAgencyAddress = $(columns).find(":input[name='outsourcingAgencyAddress']");
			$(outsourcingAgencyAddress).attr("value",bgcEmploymentDetail[i].outsourcingAgencyAddress);
			
			var outsourcingAgencyTelephone = $(columns).find(":input[name='outsourcingAgencyTelephone']");
			$(outsourcingAgencyTelephone).attr("value",bgcEmploymentDetail[i].outsourcingAgencyTelephone);
			
			var employmentDiscription = $(columns).find(":input[name='employmentDiscription']");
			$(employmentDiscription).text(bgcEmploymentDetail[i].employmentDiscription);
			
			var supervisorName = $(columns).find(":input[name='supervisorName']");
			$(supervisorName).attr("value",bgcEmploymentDetail[i].supervisorName);
			
			var supervisorTitle = $(columns).find(":input[name='supervisorTitle']");
			$(supervisorTitle).attr("value",bgcEmploymentDetail[i].supervisorTitle);
			
			var supervisorTelephone = $(columns).find(":input[name='supervisorTelephone']");
			$(supervisorTelephone).attr("value",bgcEmploymentDetail[i].supervisorTelephone);
			
			var supervisorEmail = $(columns).find(":input[name='supervisorEmail']");
			$(supervisorEmail).attr("value",bgcEmploymentDetail[i].supervisorEmail);
			
			var hrManagerName = $(columns).find(":input[name='hrManagerName']");
			$(hrManagerName).attr("value",bgcEmploymentDetail[i].hrManagerName);
			
			var hrManagerTelephone = $(columns).find(":input[name='hrManagerTelephone']");
			$(hrManagerTelephone).attr("value",bgcEmploymentDetail[i].hrManagerTelephone);
			
			var hrManagerEmail = $(columns).find(":input[name='hrManagerEmail']");
			$(hrManagerEmail).attr("value",bgcEmploymentDetail[i].hrManagerEmail);
			
			var currentEmploymentAuthority = $(columns).find(":input[name='currentEmploymentAuthority']");
			$(currentEmploymentAuthority).find(":contains('"+bgcEmploymentDetail[i].currentEmploymentAuthority +"')").attr("selected", "selected");
			
		}
	}
	
	if(values.empPersonalDetail != null ){
		
		var empPersonalDetail = values.empPersonalDetail;
		
		$("#personalDetail").find("#name").attr("value",values.empPersonalDetail.name);
		$("#personalDetail").find("#date").attr("value",values.empPersonalDetail.date);
		$("#personalDetail").find("#dob").attr("value",values.empPersonalDetail.dob);
		$("#personalDetail").find("#maritalStatus").find(":contains('"+values.empPersonalDetail.maritalStatus +"')").attr("selected", "selected");
		$("#personalDetail").find("#presentAddress").text(values.empPersonalDetail.presentAddress);
		$("#personalDetail").find("#permanentTelephone").attr("value",values.empPersonalDetail.permanentTelephone);
		$("#personalDetail").find("#presentAddressTelephone").attr("value",values.empPersonalDetail.presentAddressTelephone);
		$("#personalDetail").find("#presentAddressMobile").attr("value",values.empPersonalDetail.presentAddressMobile);
		$("#personalDetail").find("#passport").attr("value",values.empPersonalDetail.passport);
		$("#personalDetail").find("#passportExpiry").attr("value",values.empPersonalDetail.passportExpiry);
		$("#personalDetail").find("#visa").attr("value",values.empPersonalDetail.visa);
		$("#personalDetail").find("#visaExpiry").attr("value",values.empPersonalDetail.visaExpiry);
		$("#personalDetail").find("#onsiteExperience").text(values.empPersonalDetail.onsiteExperience);
		$("#personalDetail").find("#itExperience").text(values.empPersonalDetail.itExperience);
		$("#personalDetail").find("#relevantExperience").text(values.empPersonalDetail.relevantExperience);
		$("#skillsForm :input[name='primarySkill']").attr("value",values.empPersonalDetail.primarySkill);
		var secondarySkill = $("#skillsForm :input[id='secondarySkill']").attr("value",values.empPersonalDetail.secondarySkill);
				
	}
	
	if(values.empEducationalBackgroundList != null){
		var empEducationalBackgroundList = values.empEducationalBackgroundList;
		
		var educationalBackgroundDivs = $("#educationalBackground").find(".education-data");
		//var trainingDetailDivs = $("#trainingAttended").find(".training-data");
		
		for(i=0; i<empEducationalBackgroundList.length; i++){
			
			var div = null;
			var data;
			div = $(educationalBackgroundDivs[i]);
			div.find(":input[name='examinationPassed']").find(":contains('"+empEducationalBackgroundList[i].examinationPassed +"')").attr("selected", "selected");
			div.find(":input[name='fromToDate']").attr("value",empEducationalBackgroundList[i].fromToDate);
			div.find(":input[name='institution']").find(":contains('"+empEducationalBackgroundList[i].institution +"')").attr("selected", "selected");
			div.find(":input[name='board']").find(":contains('"+empEducationalBackgroundList[i].board +"')").attr("selected", "selected");
			div.find(":input[name='marks']").attr("value",empEducationalBackgroundList[i].marks);
		}	
	}
	
	if(values.empTrainingAttendedList != null){
		var empTrainingAttendedList = values.empTrainingAttendedList;
		
		var trainingDetailDivs = $("#trainingAttended").find(".training-data");
		
		for(i=0; i<empTrainingAttendedList.length; i++){
			
			var div = null;
			var data;
			div = $(trainingDetailDivs[i]);
			div.find(":input[name='courseName']").attr("value",empTrainingAttendedList[i].courseName);
			div.find(":input[name='fromToDate']").attr("value",empTrainingAttendedList[i].fromToDate);
			div.find(":input[name='institution']").attr("value",empTrainingAttendedList[i].institution);
			div.find(":input[name='certificateAwarded']").attr("value",empTrainingAttendedList[i].certificateAwarded);
		}	
	}
	
	if(values.empEmploymentDetailList != null){
		var empEmploymentDetailList = values.empEmploymentDetailList;
		var columns;
		for(i=0; i<empEmploymentDetailList.length; i++){
			
			if(i == 0){
				columns = $(".present-employer");
			}
			else if(i == 1){
				columns = $(".previous-employer");
			}
			else if(i == 2){
				columns = $(".previous-second");
			}
			else if(i == 3){
				columns = $(".previous-third");
			}
			
			var employerName = $(columns).find(":input[name='employerName']");
			$(employerName).attr("value",empEmploymentDetailList[i].employerName);
			
			var location = $(columns).find(":input[name='location']");
			$(location).attr("value",empEmploymentDetailList[i].location);
			
			var numberOfEmployee = $(columns).find(":input[name='numberOfEmployee']");
			$(numberOfEmployee).attr("value",empEmploymentDetailList[i].numberOfEmployee);
			
			var years = $(columns).find(":input[name='years']");
			$(years).attr("value",empEmploymentDetailList[i].years);
			
			var months = $(columns).find(":input[name='months']");
			$(months).attr("value",empEmploymentDetailList[i].months);
			
			var experience = $(columns).find(":input[name='experience']");
			$(experience).attr("value",empEmploymentDetailList[i].experience);
			
			var designationHeld = $(columns).find(":input[name='designationHeld']");
			$(designationHeld).attr("value",empEmploymentDetailList[i].designationHeld);
			
			var reportingTo = $(columns).find(":input[name='reportingTo']");
			$(reportingTo).attr("value",empEmploymentDetailList[i].reportingTo);
			
			var reasonForLeaving = $(columns).find(":input[name='reasonForLeaving']");
			$(reasonForLeaving).attr("value",empEmploymentDetailList[i].reasonForLeaving);
					
		}
	}
		if(values.empCurrentEmploymentDetail != null){
			var empCurrentEmploymentDetail = values.empCurrentEmploymentDetail;
			
			$("#currentEmploymentDetail :input[name='currentProjectName']").attr("value", empCurrentEmploymentDetail.currentProjectName);
			$("#currentEmploymentDetail :input[name='skillsUsed']").attr("value", empCurrentEmploymentDetail.skillsUsed);
			$("#currentEmploymentDetail :input[name='teamSize']").attr("value", empCurrentEmploymentDetail.teamSize);
			$("#currentEmploymentDetail :input[name='rolesAndResponsibilities']").text(empCurrentEmploymentDetail.rolesAndResponsibilities);
			$("#organisationDescription :input[name='organisationDescription']").text(empCurrentEmploymentDetail.organisationDescription);
			
		}
		
		if(values.empSalaryDetail != null){
			var empSalaryDetail = values.empSalaryDetail;
		
			$("#salaryData :input[name='currentCtcMonthly']").attr("value", empSalaryDetail.currentCtcMonthly);
			$("#salaryData :input[name='currentCtcAnnual']").attr("value", empSalaryDetail.currentCtcAnnual);
			$("#salaryData :input[name='currentSalaryMonthly']").attr("value", empSalaryDetail.currentSalaryMonthly);
			$("#salaryData :input[name='currentSalaryAnnual']").attr("value", empSalaryDetail.currentSalaryAnnual);
			$("#salaryData :input[name='currentSalaryFixedMonthly']").attr("value", empSalaryDetail.currentSalaryFixedMonthly);
			$("#salaryData :input[name='currentSalaryFixedAnnual']").attr("value", empSalaryDetail.currentSalaryFixedAnnual);
			$("#salaryData :input[name='currentSalaryVariableMonthly']").attr("value", empSalaryDetail.currentSalaryVariableMonthly);
			$("#salaryData :input[name='currentSalaryVariableAnnual']").attr("value", empSalaryDetail.currentSalaryVariableAnnual);
			$("#salaryData :input[name='incentiveMonthly']").attr("value", empSalaryDetail.incentiveMonthly);
			$("#salaryData :input[name='incentiveAnnual']").attr("value", empSalaryDetail.incentiveAnnual);
			$("#salaryData :input[name='expectedSalary']").attr("value", empSalaryDetail.expectedSalary);
			$("#salaryData :input[name='monthlyInHand']").attr("value", empSalaryDetail.monthlyInHand);
			$("#notice :input[name='expectedJoiningDate']").attr("value", empSalaryDetail.expectedJoiningDate);
			$("#notice :input[name='noticePeriod']").attr("value", empSalaryDetail.noticePeriod);
			$("#achievements :input[name='achievements']").text(empSalaryDetail.achievements);
		}
		
		if(values.empFamilyDetailList != null){
			var empFamilyDetailList = values.empFamilyDetailList;
			
			var familyDataTableRows = $("#familyData>tbody>tr");
			//var otherDataTableRows = $("#checks>tbody>tr");
			
			var tr = null;
			for(i=0; i<empFamilyDetailList.length;i++){
				tr = familyDataTableRows[i];
				
				$(tr).find("input[name='memberName']").attr("value", empFamilyDetailList[i].memberName);
				$(tr).find("input[name='occupation']").attr("value", empFamilyDetailList[i].occupation);
				$(tr).find("input[name='age']").attr("value", empFamilyDetailList[i].age);
				$(tr).find("input[name='dependent']").attr("value", empFamilyDetailList[i].dependent);
				$(tr).find("input[name='presentLocation']").attr("value", empFamilyDetailList[i].presentLocation);
			
			}
			
		}
		
		if(values.empReferenceList != null){
			var empReferenceList = values.empReferenceList;
			
			var columns;
			for(i=0; i<empReferenceList.length; i++){
				
				if(i == 0){
					columns = $(".reference-1");
				}
				else if(i == 1){
					columns = $(".reference-2");
				}
				else if(i == 2){
					columns = $(".reference-3");
				}
				
				$(columns).find(":input[name='referenceName']").attr("value", empReferenceList[i].referenceName);
				$(columns).find(":input[name='designation']").attr("value", empReferenceList[i].designation);
				$(columns).find(":input[name='organisationName']").attr("value", empReferenceList[i].organisationName);
				$(columns).find(":input[name='contact']").attr("value", empReferenceList[i].contact);
				
		}
		}
		if(values.empOtherDetail != null){
			var empOtherDetail = values.empOtherDetail;
			var option;
			
			if(empOtherDetail.ifReferred == "No"){
				option = $("#checks>tbody>tr :input[id='ifReferred']").find("option")[1];
			}
			else{
				option = $("#checks>tbody>tr :input[id='ifReferred']").find("option")[0];
			}
			$(option).attr("selected", "selected");
			
			if(empOtherDetail.ifContract == "No"){
				option = $("#checks>tbody>tr :input[id='ifContract']").find("option")[1];
			}
			else{
				option = $("#checks>tbody>tr :input[id='ifContract']").find("option")[0];
			}
			$(option).attr("selected", "selected");
			
			if(empOtherDetail.ifObjection == "No"){
				option = $("#checks>tbody>tr :input[id='ifObjection']").find("option")[1];
			}
			else{
				option = $("#checks>tbody>tr :input[id='ifObjection']").find("option")[0];
			}
			$(option).attr("selected", "selected");
			
			if(empOtherDetail.ifAppliedBefore == "No"){
				option = $("#checks>tbody>tr :input[id='ifAppliedBefore']").find("option")[1];
			}
			else{
				option = $("#checks>tbody>tr :input[id='ifAppliedBefore']").find("option")[0];
			}
			$(option).attr("selected", "selected");
			
			if(empOtherDetail.ifAppliedForVisa == "No"){
				option = $("#checks>tbody>tr :input[id='ifAppliedForVisa']").find("option")[1];
			}
			else{
				option = $("#checks>tbody>tr :input[id='ifAppliedForVisa']").find("option")[0];
			}
			$(option).attr("selected", "selected");
			
			$(document).find(":input[name='locationPreference']").attr("value", empOtherDetail.locationPreference);
			$(document).find(":input[name='strength']").text(empOtherDetail.strength);
			$(document).find(":input[name='toImprove']").attr("value", empOtherDetail.toImprove);
		}
		
		if(values.empSkillList != null){
			var empSkillList = values.empSkillList;
			var ratingField = null;
			
			for(i=0; i<empSkillList.length;i++){
				for(j=0;j<$(".skill-id").length;j++){
				ratingField = $(".skill-id")[j];
				if(empSkillList[i].skillId == $(ratingField).attr("data_skillId"))
				$(ratingField).attr("value",empSkillList[i].rating);
			}
			}
		}
	}

	

function savePersonalDetail(){
	
	if($("#personalDetail :input[id='name']").val() == ""){
		$("#personalDetail :input[id='name']").focus();
		return;
	}
	if($("#personalDetail :input[id='date']").val() == ""){
		$("#personalDetail :input[id='date']").focus();
		return;
	}
	if($("#personalDetail :input[id='dob']").val() == ""){
		$("#personalDetail :input[id='dob']").focus();
		return;
	}
	if($("#personalDetail :input[id='maritalStatus']").val() == ""){
		$("#personalDetail :input[id='maritalStatus']").focus();
		return;
	}
	
	var name = $("#personalDetail :input[id='name']").val();
	var date = $("#personalDetail :input[id='date']").val();
	var dob = $("#personalDetail :input[id='dob']").val();
	var maritalStatus = $("#personalDetail :input[id='maritalStatus']").val();
	var passport = $("#personalDetail :input[id='passport']").val();
	var permanentAddress = $("#personalDetail :input[id='permanentAddress']").val();
	var permanentTelephone = $("#personalDetail :input[id='permanentTelephone']").val();
	var passportExpiry = $("#personalDetail :input[id='passportExpiry']").val();
	var visa = $("#personalDetail :input[id='visa']").val();
	var visaExpiry = $("#personalDetail :input[id='visaExpiry']").val();
	var presentAddress = $("#personalDetail :input[id='presentAddress']").val();
	var presentAddressTelephone = $("#personalDetail :input[id='presentAddressTelephone']").val();
	var presentAddressMobile = $("#personalDetail :input[id='presentAddressMobile']").val();
	var itExperience = $("#personalDetail :input[id='itExperience']").val();
	var relevantExperience = $("#personalDetail :input[id='relevantExperience']").val();
	var onsiteExperience = $("#personalDetail :input[id='onsiteExperience']").val();
	
	var data = {"name":name,"date":date,"dob":dob,"maritalStatus":maritalStatus,"passport":passport,"permanentAddress":permanentAddress,"permanentTelephone":permanentTelephone,"passportExpiry":passportExpiry,"visa":visa,
				"visaExpiry":visaExpiry,"presentAddress":presentAddress,"presentAddressTelephone":presentAddressTelephone,"presentAddressMobile":presentAddressMobile,"itExperience":itExperience,"relevantExperience":relevantExperience,"onsiteExperience":onsiteExperience};
	 $.ajax({
         type: 'POST',
         url: 'saveEmployeePersonalDetail',
         data:JSON.stringify(data),
         dataType: 'json',
         contentType: 'application/json',
         success: function(data) {
         	bootbox.alert("personal details saved");
         	$('#tabs li:eq(2) a').tab('show');
             
         },
         error: function(data) {
             bootbox.alert(data.statusText);
         }
     });

}

function saveEducationalQualification(){
	var educationalBackgroundDivs = $("#educationalBackground").find(".education-data");
	var trainingDetailDivs = $("#trainingAttended").find(".training-data");
	var div = null;
	var data;
	for(i=0; i<educationalBackgroundDivs.length;i++){
		div = $(educationalBackgroundDivs[i]);
		var examinationPassed = div.find(":input[name='examinationPassed']").val();
		var fromToDate = div.find(":input[name='fromToDate']").val();
		var institution = div.find(":input[name='institution']").val();
		var board = div.find(":input[name='board']").val();
		var marks = div.find(":input[name='marks']").val();
		if(examinationPassed != "" && fromToDate != "" && institution != "" && board != "" && marks != ""){
			data = {"examinationPassed":examinationPassed,"fromToDate":fromToDate,"institution":institution,
					"board":board,"marks":marks};
			employeeEducationalBackground.push(data);
		}
		
	}
	for(i=0; i<trainingDetailDivs.length;i++){
		div = $(trainingDetailDivs[i]);
		var courseName = div.find(":input[name='courseName']").val();
		var fromToDate = div.find(":input[name='fromToDate']").val();
		var institution = div.find(":input[name='institution']").val();
		var certificateAwarded = div.find(":input[name='certificateAwarded']").val();
		if(courseName != "" && fromToDate != "" && institution != "" && certificateAwarded != ""){
			data = {"courseName":courseName,"fromToDate":fromToDate,"institution":institution,"certificateAwarded":certificateAwarded};
			employeeTrainingDetail.push(data);
		}
		
	}
	var educationalQualification = {"employeeEducationalBackgroundList":employeeEducationalBackground, "employeeTrainingAttendedList":employeeTrainingDetail} ;
	
	
	 $.ajax({
         type: 'POST',
         url: 'saveEmployeeQualification',
         data:JSON.stringify(educationalQualification),
         dataType: 'json',
         contentType: 'application/json',
         success: function(data) {
         	bootbox.alert("educational qualification saved");
         	$('#tabs li:eq(3) a').tab('show');
             
         },
         error: function(data) {
             bootbox.alert(data.statusText);
         }
     });
}

function saveEmploymentDetail(){
	if($(".present-employer :input[name='employerName']").val() != ""){
		var employerName = $(".present-employer :input[name='employerName']").val();
		var location = $(".present-employer :input[name='location']").val();
		var months = $(".present-employer :input[name='months']").val();
		var years = $(".present-employer :input[name='years']").val();
		var designationHeld = $(".present-employer :input[name='designationHeld']").val();
		var reportingTo = $(".present-employer :input[name='reportingTo']").val();
		var reasonForLeaving = $(".present-employer :input[name='reasonForLeaving']").val();
		var experience = $(".present-employer :input[name='experience']").val();
		var numberOfEmployee = $(".present-employer :input[name='numberOfEmployee']").val();
		
		var data = {"employerName": employerName, "location": location, "months": months, "years": years, "designationHeld": designationHeld, "reportingTo": reportingTo
				, "reasonForLeaving": reasonForLeaving, "experience": experience, "numberOfEmployee": numberOfEmployee};
		employeeEmploymentDetail.push(data);
		
	}
	if($(".previous-employer :input[name='employerName']").val() != ""){
		var employerName = $(".previous-employer :input[name='employerName']").val();
		var location = $(".previous-employer :input[name='location']").val();
		var months = $(".previous-employer :input[name='months']").val();
		var years = $(".previous-employer :input[name='years']").val();
		var designationHeld = $(".previous-employer :input[name='designationHeld']").val();
		var reportingTo = $(".previous-employer :input[name='reportingTo']").val();
		var reasonForLeaving = $(".previous-employer :input[name='reasonForLeaving']").val();
		var experience = $(".previous-employer :input[name='experience']").val();
		var numberOfEmployee = $(".previous-employer :input[name='numberOfEmployee']").val();
		var data = {"employerName": employerName, "location": location, "months": months, "years": years, "designationHeld": designationHeld, "reportingTo": reportingTo
				, "reasonForLeaving": reasonForLeaving, "experience": experience, "numberOfEmployee": numberOfEmployee};
		employeeEmploymentDetail.push(data);
		
	}
	if($(".previous-second :input[name='employerName']").val() != ""){
		var employerName = $(".previous-second :input[name='employerName']").val();
		var location = $(".previous-second :input[name='location']").val();
		var months = $(".previous-second :input[name='months']").val();
		var years = $(".previous-second :input[name='years']").val();
		var designationHeld = $(".previous-second :input[name='designationHeld']").val();
		var reportingTo = $(".previous-second :input[name='reportingTo']").val();
		var reasonForLeaving = $(".previous-second :input[name='reasonForLeaving']").val();
		var experience = $(".previous-second :input[name='experience']").val();
		var numberOfEmployee = $(".previous-second :input[name='numberOfEmployee']").val();
		var data = {"employerName": employerName, "location": location, "months": months, "years": years, "designationHeld": designationHeld, "reportingTo": reportingTo
				, "reasonForLeaving": reasonForLeaving, "experience": experience, "numberOfEmployee": numberOfEmployee};
		employeeEmploymentDetail.push(data);
		
	}
	if($(".previous-third :input[name='employerName']").val() != ""){
		var employerName = $(".previous-third :input[name='employerName']").val();
		var location = $(".previous-third :input[name='location']").val();
		var months = $(".previous-third :input[name='months']").val();
		var years = $(".previous-third :input[name='years']").val();
		var designationHeld = $(".previous-third :input[name='designationHeld']").val();
		var reportingTo = $(".previous-third :input[name='reportingTo']").val();
		var reasonForLeaving = $(".previous-third :input[name='reasonForLeaving']").val();
		var experience = $(".previous-third :input[name='experience']").val();
		var numberOfEmployee = $(".previous-third :input[name='numberOfEmployee']").val();
		var data = {"employerName": employerName, "location": location, "months": months, "years": years, "designationHeld": designationHeld, "reportingTo": reportingTo
				, "reasonForLeaving": reasonForLeaving, "experience": experience, "numberOfEmployee": numberOfEmployee};
		employeeEmploymentDetail.push(data);
		
	}
	
	var currentProjectName = $("#currentEmploymentDetail :input[name='currentProjectName']").val();
	var skillsUsed = $("#currentEmploymentDetail :input[name='skillsUsed']").val();
	var teamSize = $("#currentEmploymentDetail :input[name='teamSize']").val();
	var rolesAndResponsibilities = $("#currentEmploymentDetail :input[name='rolesAndResponsibilities']").val();
	var organisationDescription = $("#organisationDescription :input[name='organisationDescription']").val();
	
	var currentEmploymentDetail = {"currentProjectName": currentProjectName, "skillsUsed": skillsUsed, "teamSize": teamSize
			, "rolesAndResponsibilities": rolesAndResponsibilities, "organisationDescription": organisationDescription};
	
	var employmentDetail = {"employeeEmploymentDetailList": employeeEmploymentDetail, "currentEmploymentDetail": currentEmploymentDetail};
	
	 $.ajax({
         type: 'POST',
         url: 'saveEmploymentDetail',
         data:JSON.stringify(employmentDetail),
         dataType: 'json',
         contentType: 'application/json',
         success: function(data) {
         	bootbox.alert("employment details saved");
         	$('#tabs li:eq(5) a').tab('show');
             
         },
         error: function(data) {
             bootbox.alert(data.statusText);
         }
     });

}

function saveSalaryDetail(){
	var currentCtcMonthly = $("#salaryData :input[name='currentCtcMonthly']").val();
	var currentCtcAnnual = $("#salaryData :input[name='currentCtcAnnual']").val();
	var currentSalaryMonthly = $("#salaryData :input[name='currentSalaryMonthly']").val();
	var currentSalaryAnnual = $("#salaryData :input[name='currentSalaryAnnual']").val();
	var currentSalaryFixedMonthly = $("#salaryData :input[name='currentSalaryFixedMonthly']").val();
	var currentSalaryFixedAnnual = $("#salaryData :input[name='currentSalaryFixedAnnual']").val();
	var currentSalaryVariableMonthly = $("#salaryData :input[name='currentSalaryVariableMonthly']").val();
	var currentSalaryVariableAnnual = $("#salaryData :input[name='currentSalaryVariableAnnual']").val();
	var incentiveMonthly = $("#salaryData :input[name='incentiveMonthly']").val();
	var incentiveAnnual = $("#salaryData :input[name='incentiveAnnual']").val();
	var expectedSalary = $("#salaryData :input[name='expectedSalary']").val();
	var monthlyInHand = $("#salaryData :input[name='monthlyInHand']").val();
	var expectedjoiningDate = $("#notice :input[name='expectedJoiningDate']").val();
	var noticePeriod = $("#notice :input[name='noticePeriod']").val();
	var achievements = $("#achievements :input[name='achievements']").val();
	
	var employeeSalaryDetail = {"currentCtcMonthly":currentCtcMonthly,"currentCtcAnnual":currentCtcAnnual,"currentSalaryMonthly":currentSalaryMonthly,
								"currentSalaryAnnual":currentSalaryAnnual,"currentSalaryFixedMonthly":currentSalaryFixedMonthly,"currentSalaryFixedAnnual":currentSalaryFixedAnnual,
								"currentSalaryVariableMonthly":currentSalaryVariableMonthly,"currentSalaryVariableAnnual":currentSalaryVariableAnnual,"incentiveMonthly":incentiveMonthly,
								"incentiveAnnual":incentiveAnnual,"expectedSalary":expectedSalary,"monthlyInHand":monthlyInHand,
								"expectedJoiningDate":expectedjoiningDate,"noticePeriod":noticePeriod,"achievements":achievements};
	 $.ajax({
         type: 'POST',
         url: 'saveEmployeeSalaryDetail',
         data:JSON.stringify(employeeSalaryDetail),
         dataType: 'json',
         contentType: 'application/json',
         success: function(data) {
         	bootbox.alert("employment salary details saved");
         	$('#tabs li:eq(6) a').tab('show');
             
         },
         error: function(data) {
             bootbox.alert(data.statusText);
         }
     });
}

function saveOtherDetail(){
	var familyDataTableRows = $("#familyData>tbody>tr");
	var otherDataTableRows = $("#checks>tbody>tr");
	
	var tr = null;
	for(i=0; i<familyDataTableRows.length;i++){
		tr = familyDataTableRows[i];
		if($(tr).find("input[name='memberName']").val() != ""){
			var memberName = $(tr).find("input[name='memberName']").val();
			var occupation = $(tr).find("input[name='occupation']").val();
			var age = $(tr).find("input[name='age']").val();
			var dependent = $(tr).find("input[name='dependent']").val();
			var presentLocation = $(tr).find("input[name='presentLocation']").val();
			family = {"memberName":memberName, "occupation":occupation, "age":age, "dependent":dependent, "presentLocation":presentLocation};
			familyData.push(family);
		}
	}
	
			var ifReferred = $("#checks>tbody>tr :input[id='ifReferred']").val();
			var ifContract = $("#checks>tbody>tr :input[id='ifContract']").val();
			var ifObjection = $("#checks>tbody>tr :input[id='ifObjection']").val();
			var ifAppliedBefore = $("#checks>tbody>tr :input[id='ifAppliedBefore']").val();
			var ifAppliedForVisa = $("#checks>tbody>tr :input[id='ifAppliedForVisa']").val();
			var locationPreference = $(document).find(":input[name='locationPreference']").val();
			var strength = $(document).find(":input[name='strength']").val();
			var toImprove = $(document).find(":input[name='toImprove']").val();
			var other = {"ifReferred":ifReferred, "ifContract":ifContract, "ifObjection":ifObjection, "ifAppliedBefore":ifAppliedBefore
					, "ifAppliedForVisa":ifAppliedForVisa, "locationPreference":locationPreference, "strength":strength, "toImprove":toImprove};
			otherData.push(other);
			
	
	if($(document).find(".reference-1 :input[name='referenceName']").val() != "")
		{
	var referenceName = $(document).find(".reference-1 :input[name='referenceName']").val();
	var designation = $(document).find(".reference-1 :input[name='designation']").val();
	var organisationName = $(document).find(".reference-1 :input[name='organisationName']").val();
	var contact = $(document).find(".reference-1 :input[name='contact']").val();
	
	var reference = {"referenceName":referenceName, "designation":designation,"organisationName":organisationName,"contact":contact};
	referenceData.push(reference);
		}
	if($(document).find(".reference-2 :input[name='referenceName']").val() != "")
	{
	referenceName = $(document).find(".reference-2 :input[name='referenceName']").val();
	designation = $(document).find(".reference-2 :input[name='designation']").val();
	organisationName = $(document).find(".reference-2 :input[name='organisationName']").val();
	contact = $(document).find(".reference-2 :input[name='contact']").val();
	reference = {"referenceName":referenceName, "designation":designation,"organisationName":organisationName,"contact":contact};
	console.log(reference);
	referenceData.push(reference);
	}
	
	if($(document).find(".reference-3 :input[name='referenceName']").val() != "")
		{
	referenceName = $(document).find(".reference-3 :input[name='referenceName']").val();
	designation = $(document).find(".reference-3 :input[name='designation']").val();
	organisationName = $(document).find(".reference-3 :input[name='organisationName']").val();
	contact = $(document).find(".reference-3 :input[name='contact']").val();
	
	reference = {"referenceName":referenceName, "designation":designation,"organisationName":organisationName,"contact":contact};
	referenceData.push(reference);
		}
	var otherDetail = {"employeeFamilyDetailList": familyData, "employeeReferenceList":referenceData, "employeeOtherDetail":otherData};
	$.ajax({
        type: 'POST',
        url: 'saveOtherDetail',
        data:JSON.stringify(otherDetail),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
        	bootbox.alert("employment details saved");
        	
            
        },
        error: function(data) {
            bootbox.alert(data.statusText);
        }
    });
	
}

function saveSkillDetail(){
	var ratingField = null;
	for(i=0; i<$(".skill-id").length;i++){
		ratingField = $(".skill-id")[i];
		if($(ratingField).val() != ""){
			var skillId = $(ratingField).attr("data_skillId");
			var rating = $(ratingField).val();
			var data = {"skillId":skillId, "rating":rating};
			skillData.push(data);
		}
	}
	
	var primarySkill = $("#skillsForm :input[name='primarySkill']").val();
	var secondarySkill = $("#skillsForm :input[id='secondarySkill']").val();
	
	var skillDetail = {"primarySkill": primarySkill, "secondarySkill":secondarySkill, "skills":skillData};
	$.ajax({
        type: 'POST',
        url: 'saveSkillDetail',
        data:JSON.stringify(skillDetail),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
        	bootbox.alert("employment details saved");
        	$('#tabs li:eq(4) a').tab('show');
            
        },
        error: function(data) {
            bootbox.alert(data.statusText);
        }
    });
}

function populateEducationFormData(values){
	var div = null;
	
	for(var j= 0;j < $(document).find(":input[name='examinationPassed']").length;j++){
		div = $(document).find(":input[name='examinationPassed']")[j];
	for(var i = 0;i< values.educationFormData.degreeList.length;i++){
		$(div).append("<option>"+values.educationFormData.degreeList[i] +"</option>");
	}
	div = $(document).find(":input[name='institution']")[j];
	for(var i = 0;i< values.educationFormData.collegeList.length;i++){
		$(div).append("<option>"+values.educationFormData.collegeList[i] +"</option>");
	}
	div = $(document).find(":input[name='board']")[j];
	for(var i = 0;i< values.educationFormData.universityList.length;i++){
		$(div).append("<option>"+values.educationFormData.universityList[i] +"</option>");
	}
	
	}
	
}

function saveBgcDetail(){
	/*if($("#personalDetail :input[id='name']").val() == ""){
		$("#personalDetail :input[id='name']").focus();
		return;
	}
	if($("#personalDetail :input[id='date']").val() == ""){
		$("#personalDetail :input[id='date']").focus();
		return;
	}
	if($("#personalDetail :input[id='dob']").val() == ""){
		$("#personalDetail :input[id='dob']").focus();
		return;
	}
	if($("#personalDetail :input[id='maritalStatus']").val() == ""){
		$("#personalDetail :input[id='maritalStatus']").focus();
		return;
	}*/
	
	
	
	var nameOfApplicant = $("#bgcDetail :input[id='nameOfApplicant']").val();
	var dateOfBirth = $("#bgcDetail :input[id='dateOfBirth']").val();
	var placeOfBirth = $("#bgcDetail :input[id='placeOfBirth']").val();
	var sex = $("#bgcDetail :input[id='sex']").val();
	var nationality = $("#bgcDetail :input[id='nationality']").val();
	var fatherName = $("#bgcDetail :input[id='fatherName']").val();
	var passportNumber = $("#bgcDetail :input[id='passportNumber']").val();
	var homePhone = $("#bgcDetail :input[id='homePhone']").val();
	var officePhone = $("#bgcDetail :input[id='officePhone']").val();
	var mobile = $("#bgcDetail :input[id='mobile']").val();
	var permanentAddress = $("#bgcDetail :input[id='permanentAddress']").val();
	var permanentCity = $("#bgcDetail :input[id='permanentCity']").val();
	var permanentState = $("#bgcDetail :input[id='permanentState']").val();
	var permanentTelephone = $("#bgcDetail :input[id='permanentTelephone']").val();
	var permanentNatureOfLocation = $("#bgcDetail :input[id='permanentNatureOfLocation']").val();
	var permanentDurationFrom = $("#bgcDetail :input[id='permanentDurationFrom']").val();
	var permanentDurationTo = $("#bgcDetail :input[id='permanentDurationTo']").val();
	var permanentPinCode = $("#bgcDetail :input[id='permanentPinCode']").val();
	
	var presentAddress = $("#bgcDetail :input[id='presentAddress']").val();
	var presentCity = $("#bgcDetail :input[id='presentCity']").val();
	var presentState = $("#bgcDetail :input[id='presentState']").val();
	var presentTelephone = $("#bgcDetail :input[id='presentTelephone']").val();
	var presentNatureOfLocation = $("#bgcDetail :input[id='presentNatureOfLocation']").val();
	var presentDurationFrom = $("#bgcDetail :input[id='presentDurationFrom']").val();
	var presentDurationTo = $("#bgcDetail :input[id='presentDurationTo']").val();
	var presentPinCode = $("#bgcDetail :input[id='presentPinCode']").val();
	var iAgree;
	if ($("#bgcDetail :input[id='iAgree']").is(":checked")){
		iAgree = "yes";
	}
	else{
		bootbox.alert("You much accept all the terms and conditions");
		$("#tabs").find("li").addClass("disabled");
        $("#tabs").find("li:eq(0)").removeClass("disabled");
		return ;
	}
	
	
	var backgroundCheckPersonalDetail = {"nameOfApplicant":nameOfApplicant,"dateOfBirth":dateOfBirth,"placeOfBirth":placeOfBirth,"sex":sex,"nationality":nationality,"fatherName":fatherName,"passportNumber":passportNumber,"homePhone":homePhone,
				"officePhone":officePhone,"mobile":mobile,"permanentAddress":permanentAddress,"permanentCity":permanentCity,"permanentState":permanentState,"permanentTelephone":permanentTelephone,"permanentNatureOfLocation":permanentNatureOfLocation,
				"permanentDurationFrom":permanentDurationFrom,"permanentDurationTo":permanentDurationTo,"permanentPinCode":permanentPinCode,"presentAddress":presentAddress,"presentCity":presentCity,"presentState":presentState,
				"presentTelephone":presentTelephone,"presentNatureOfLocation":presentNatureOfLocation,"presentDurationFrom":presentDurationFrom,"presentDurationTo":presentDurationTo,"presentPinCode":presentPinCode,"iAgree":iAgree};
	
	var educationalBackgroundDivs = $("#bgcDetail").find(".education-data");

	var div = null;
	var data;
	for(i=0; i<educationalBackgroundDivs.length;i++){
		div = $(educationalBackgroundDivs[i]);
		var examinationPassed = div.find(":input[name='examinationPassed']").val();
		var fromToDate = div.find(":input[name='fromToDate']").val();
		var institution = div.find(":input[name='institution']").val();
		var board = div.find(":input[name='board']").val();
		var marks = div.find(":input[name='marks']").val();
		var courseAttended = div.find(":input[name='courseAttended']").val();
		var rollNumber = div.find(":input[name='rollNumber']").val();
		var discipline = div.find(":input[name='discipline']").val();
		
		
		if(examinationPassed != "" && fromToDate != "" && institution != "" && board != "" && marks != ""){
			data = {"examinationPassed":examinationPassed,"fromToDate":fromToDate,"institution":institution,
					"board":board,"marks":marks,"courseAttended":courseAttended,"rollNumber":rollNumber,"discipline":discipline};
			bgcEducationDetail.push(data);
		}
	}

	if($(".employer1 :input[name='employerName']").val() != ""){
		var employerName = $(".employer1 :input[name='employerName']").val();
		var employeeId = $(".employer1 :input[name='employeeId']").val();
		var employmentFrom = $(".employer1 :input[name='employmentFrom']").val();
		var employmentTo = $(".employer1 :input[name='employmentTo']").val();
		var employerCity = $(".employer1 :input[name='employerCity']").val();
		var employerState = $(".employer1 :input[name='employerState']").val();
		var employerCountry = $(".employer1 :input[name='employerCountry']").val();
		var employerPostalCode = $(".employer1 :input[name='employerPostalCode']").val();
		var employerTelephone = $(".employer1 :input[name='employerTelephone']").val();
		var remuneration = $(".employer1 :input[name='remuneration']").val();
		var designationHeld = $(".employer1 :input[name='designationHeld']").val();
		var reasonForLeaving = $(".employer1 :input[name='reasonForLeaving']").val();
		var employmentStatus = $(".employer1 :input[name='employmentStatus']").val();
		var outsourcingAgencyName = $(".employer1 :input[name='outsourcingAgencyName']").val();
		var outsourcingAgencyAddress = $(".employer1 :input[name='outsourcingAgencyAddress']").val();
		var outsourcingAgencyTelephone = $(".employer1 :input[name='outsourcingAgencyTelephone']").val();
		var employmentDiscription = $(".employer1 :input[name='employmentDiscription']").val();
		var supervisorName = $(".employer1 :input[name='supervisorName']").val();
		var supervisorTitle = $(".employer1 :input[name='supervisorTitle']").val();
		var supervisorTelephone = $(".employer1 :input[name='supervisorTelephone']").val();
		var supervisorEmail = $(".employer1 :input[name='supervisorEmail']").val();
		var hrManagerName = $(".employer1 :input[name='hrManagerName']").val();
		var hrManagerTelephone = $(".employer1 :input[name='hrManagerTelephone']").val();
		var hrManagerEmail = $(".employer1 :input[name='hrManagerEmail']").val();
		var currentEmploymentAuthority = $(".employer1 :input[name='currentEmploymentAuthority']").val();
		
		
		var data = {"employerName": employerName, "employeeId": employeeId, "employmentFrom": employmentFrom, "employmentTo": employmentTo, "employerCity": employerCity, "employerState": employerState
				, "employerCountry": employerCountry, "employerPostalCode": employerPostalCode, "employerTelephone": employerTelephone,"remuneration": remuneration,"designationHeld": designationHeld,"reasonForLeaving": reasonForLeaving,
				"employmentStatus": employmentStatus,"outsourcingAgencyName": outsourcingAgencyName,"outsourcingAgencyAddress": outsourcingAgencyAddress,"outsourcingAgencyTelephone": outsourcingAgencyTelephone,"employmentDiscription": employmentDiscription,
				"supervisorName": supervisorName,"supervisorTitle": supervisorTitle,"supervisorTelephone": supervisorTelephone,"supervisorEmail": supervisorEmail,"hrManagerName": hrManagerName,"hrManagerTelephone": hrManagerTelephone,"hrManagerEmail": hrManagerEmail,
				"currentEmploymentAuthority": currentEmploymentAuthority};
		bgcEmploymentDetail.push(data);
		
	}
	if($(".employer2 :input[name='employerName']").val() != ""){
		var employerName = $(".employer2 :input[name='employerName']").val();
		var employerId = $(".employer2 :input[name='employerId']").val();
		var employmentFrom = $(".employer2 :input[name='employmentFrom']").val();
		var employmentTo = $(".employer2 :input[name='employmentTo']").val();
		var employerCity = $(".employer2 :input[name='employerCity']").val();
		var employerState = $(".employer2 :input[name='employerState']").val();
		var employerCountry = $(".employer2 :input[name='employerCountry']").val();
		var employerPostalCode = $(".employer2 :input[name='employerPostalCode']").val();
		var employerTelephone = $(".employer2 :input[name='employerTelephone']").val();
		var remuneration = $(".employer2 :input[name='remuneration']").val();
		var designationHeld = $(".employer2 :input[name='designationHeld']").val();
		var reasonForLeaving = $(".employer2 :input[name='reasonForLeaving']").val();
		var employmentStatus = $(".employemployer2er1 :input[name='employmentStatus']").val();
		var outsourcingAgencyName = $(".employer2 :input[name='outsourcingAgencyName']").val();
		var outsourcingAgencyAddress = $(".employer2 :input[name='outsourcingAgencyAddress']").val();
		var outsourcingAgencyTelephone = $(".employer2 :input[name='outsourcingAgencyTelephone']").val();
		var employmentDiscrition = $(".employer2 :input[name='employmentDiscrition']").val();
		var supervisorName = $(".employer2 :input[name='supervisorName']").val();
		var supervisorTitle = $(".employer2 :input[name='supervisorTitle']").val();
		var supervisorTelephone = $(".employer2 :input[name='supervisorTelephone']").val();
		var supervisorEmail = $(".employer2 :input[name='supervisorEmail']").val();
		var hrManagerName = $(".employer2 :input[name='hrManagerName']").val();
		var hrManagerNameTelephone = $(".employer2 :input[name='hrManagerNameTelephone']").val();
		var hrManagerNameEmail = $(".employer2 :input[name='hrManagerNameEmail']").val();
		var currentEmploymentAuthority = $(".employer2 :input[name='currentEmploymentAuthority']").val();
		
		
		var data = {"employerName": employerName, "employerId": employerId, "employmentFrom": employmentFrom, "employmentTo": employmentTo, "employerCity": employerCity, "employerState": employerState
				, "employerCountry": employerCountry, "employerPostalCode": employerPostalCode, "employerTelephone": employerTelephone,"remuneration": remuneration,"designationHeld": designationHeld,"reasonForLeaving": reasonForLeaving,
				"employmentStatus": employmentStatus,"outsourcingAgencyName": outsourcingAgencyName,"outsourcingAgencyAddress": outsourcingAgencyAddress,"outsourcingAgencyTelephone": outsourcingAgencyTelephone,"employmentDiscrition": employmentDiscrition,
				"supervisorName": supervisorName,"supervisorTitle": supervisorTitle,"supervisorTelephone": supervisorTelephone,"supervisorEmail": supervisorEmail,"hrManagerName": hrManagerName,"hrManagerNameTelephone": hrManagerNameTelephone,"hrManagerNameEmail": hrManagerNameEmail,
				"currentEmploymentAuthority": currentEmploymentAuthority};
		bgcEmploymentDetail.push(data);
		
	}
	
	var bgcData = {"backgroundCheckPersonalDetail": backgroundCheckPersonalDetail, "backgroundCheckEducationalDetailList": bgcEducationDetail,"backgroundCheckEmploymentDetailList": bgcEmploymentDetail};
	
	$.ajax({
         type: 'POST',
         url: 'saveBackgroundCheckDetail',
         data:JSON.stringify(bgcData),
         dataType: 'json',
         contentType: 'application/json',
         success: function(data) {
         	
         	bootbox.confirm("bgc details saved", function(result) {
         			window.location.reload();
         		});
         	//$("#tabs").find("li").removeClass("disabled");
         	
         	$("#tabs a[data-toggle=tab]:eq(1)").attr('href','#2');
         	$("#tabs a[data-toggle=tab]:eq(2)").attr('href','#3');
         	$("#tabs a[data-toggle=tab]:eq(3)").attr('href','#4');
         	$("#tabs a[data-toggle=tab]:eq(4)").attr('href','#5');
         	$("#tabs a[data-toggle=tab]:eq(5)").attr('href','#6');
         	$("#tabs a[data-toggle=tab]:eq(6)").attr('href','#7');
         	
         	//$('#tabs li:eq(1) a').tab('show');
             
         },
         error: function(data) {
             bootbox.alert(data.statusText);
             $("#tabs").find("li").addClass("disabled");
             $("#tabs").find("li:eq(0)").removeClass("disabled");
             
         }
     });
}