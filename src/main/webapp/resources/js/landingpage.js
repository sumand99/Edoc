var approve = [];
var reject = [];
var remove = [];
var counter = 1;
var backCounter;
var back;
var backParent;
var removeFolder = [];
var approveFolder = [];
var rejectFolder = [];
var makeEmployeeData = [];
$( document ).ajaxStop(function() {
	
	
	  $("#dataTable>tbody>tr").removeClass("checked");
	  $("#bgtable>tbody>tr").removeClass("checked");
	  $("#dataTable>tbody>tr").removeClass("categorychecked");
	  $('.case').prop('checked', false);
      $('.category').prop('checked', false);
      if ($("#dataTable>tbody>tr").hasClass("checked") || $("#bgtable>tbody>tr").hasClass("checked")) {
          $("#approve-button").attr("disabled", false);
          $("#reject-button").attr("disabled", false);
      } else {
          $("#approve-button").attr("disabled", true);
          $("#reject-button").attr("disabled", true);
      }
	});

$(document).ready(function() {
    $("#dialog").dialog({
        height: 500,
        width: 950,
        modal:true,
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 500
        },
        hide: {
            effect: "blind",
            duration: 500
        }
    });

    $("#total").hide();
    

    $("#form4 :input[name='categoryName']").keypress(function(e) {
        var key = e.which;
        if (key == 13) {
            $('#createFolder').click();
            return false;
        }
    });

    $('#goback').hide();

    
    

    renderLandingPageTable(values);

    if(role = "ROLE_USER" && $("#bgtable>tbody>tr").attr("data_fileId")=="0")
    bootbox.alert("Upload your photograph in order to activate your account.");
    
    $(document).click(function(e) {
        var _target = $(e.target);
        
        $(".selectVisibility").unbind().change(function(){
        	
        	var tr = _target.closest("tr");
        	var data = null;
        	var categoryId = $(tr).attr("data_categoryId");
        	var visibility = $(tr).find(".selectVisibility :selected").val();
        	data = {"categoryId":parseInt(categoryId), "visibility":parseInt(visibility)};
        	$.ajax({
    	        type: 'POST',
    	        url: 'changeFolderVisibility',
    	        data: JSON.stringify(data),
    	        contentType: 'application/json',
    	        success: function(data) {
    	            var json = data;
    	        },
    	        error: function(data) {
    	            bootbox.alert(data.statusText);
    	        }
    	    });
        	
        	/*changeVisiblity(data);*/
        });
        
        if (_target.hasClass("case")) {
            if (_target.closest("tr").hasClass("checked")){
                _target.closest("tr").removeClass("checked");
                if (!($("#dataTable>tbody>tr").hasClass("checked") || $("#bgtable>tbody>tr").hasClass("checked"))) {
                    $("#approve-button").attr("disabled", true);
                    $("#reject-button").attr("disabled", true);
                } else {
                    $("#approve-button").attr("disabled", false);
                    $("#reject-button").attr("disabled", false);
                }
            }
            else{
                _target.closest("tr").addClass("checked");
                if( _target.closest("tr").attr("data_flagId")==2 ){
                	 $("#approve-button").attr("disabled", false);
                     $("#reject-button").attr("disabled", false);
                }
                else if(_target.closest("tr").attr("data_flagId")==1){
                	$("#approve-button").attr("disabled", false);
                }
                else if(_target.closest("tr").attr("data_flagId")==3){
                	$("#reject-button").attr("disabled", false);
                }
                
            }
            } else if (_target.attr("id") == "selectAll") {
            if ($('#selectAll').hasClass("checked")) {
                $('#dataTable>tbody>tr').removeClass("checked categorychecked");
                $('#bgtable>tbody>tr').removeClass("checked categorychecked");
                $('.case').prop('checked', false);
                $('.category').prop('checked', false);
                $('#selectAll').removeClass("checked");
            } else {
                $("#selectAll").addClass("checked");
                $('#dataTable>tbody>tr').addClass("checked categorychecked");
                $('#bgtable>tbody>tr').addClass("checked categorychecked");
                $('.case').prop('checked', true);
                $('.category').prop('checked', true);
            }
            
            if ($("#dataTable>tbody>tr").hasClass("checked") || $("#bgtable>tbody>tr").hasClass("checked")) {
                $("#approve-button").attr("disabled", false);
                $("#reject-button").attr("disabled", false);
            } else {
                $("#approve-button").attr("disabled", true);
                $("#reject-button").attr("disabled", true);
            }
            
        } else if (_target.hasClass("category")) {
            if (_target.closest("tr").hasClass("categorychecked")){
                _target.closest("tr").removeClass("categorychecked");
                if ($("#dataTable>tbody>tr").hasClass("checked") || $("#bgtable>tbody>tr").hasClass("checked") || $("#dataTable>tbody>tr").hasClass("categorychecked")) {
                    $("#approve-button").attr("disabled", false);
                    $("#reject-button").attr("disabled", false);
                } else {
                    $("#approve-button").attr("disabled", true);
                    $("#reject-button").attr("disabled", true);
                }
            }
            else
            	{
                _target.closest("tr").addClass("categorychecked");
                $("#approve-button").attr("disabled", false);
                $("#reject-button").attr("disabled", false);
            	}
        } else if (_target.attr("id") == "uploadthis") {

            _target.closest("tr").addClass("clicked");
            $('#upload-single-doc').modal('show');
        } else if (_target.attr("id") == "uploadthisbgCheck") {

            _target.closest("tr").addClass("clicked");
            $('#upload-background-check-doc').modal('show');
        } else if (_target.attr("id") == "make") {
            makeEmployeeForm();
        }else if (_target.attr("id") == "reject") {
            rejectEmployee();
        } else if (_target.attr("id") == "fileApprove") {
        	approveFolder.length = 0;
            approve.length = 0;
            onApprove(_target);
            if(role == "ROLE_ADMIN")
            onFolderApprove();

        } else if (_target.attr("id") == "fileReject") {
        	rejectFolder.length = 0;
            reject.length = 0;
            onReject(_target);
            if(role == "ROLE_ADMIN")
            onFolderReject();

        }
        else if (_target.attr("id") == "enable-edit"){
        	bootbox.confirm("Are you sure you want to enable editing by this user?", function(result) {
        		  if(result)
        		  {
        			 onEnableDisableEdit(true);
        		  }
        	}); 
        }
        else if (_target.attr("id") == "disable-edit"){
        	bootbox.confirm("Are you sure you want to disable editing by this user?", function(result) {
        		  if(result)
        		  {
        			  onEnableDisableEdit(false);
        		  }
        	}); 
        }
        else if (_target.attr("id") == "enable-user"){
        	bootbox.confirm("Are you sure you want to enable user?", function(result) {
        		  if(result)
        		  {
        			 onEnableDisableUser(true);
        		  }
        	}); 
        }
        else if (_target.attr("id") == "disable-user"){
        	bootbox.confirm("Are you sure you want to disable user?", function(result) {
	      		  if(result)
	      		  {
	      			 onEnableDisableUser(false);
	      		  }
        	});
        }
        
        else if (_target.attr("id") == "submit") {
           /* if ($("#bgtable>tbody>tr").attr('data_fileid') == 0) {
                bootbox.alert("BGC form must be submitted");
            }*/
        	if($('#bgtable>tbody>tr').attr("data_fileId") == 0){
        		bootbox.alert("photograph must be uploaded.")
        	}
        	else if(bgcData != null){
            if (bootbox.confirm('Once submitted, you will not be able to edit documents. Do you want to proceed?', function(result) {
                    if (result) {
                        onSubmit();
                    }
                })) {}
            }
            else{
            	bootbox.alert("Terms and Conditions in Employee Application Form must be accepted");
            }
        } else if (_target.attr("id") == "fileRemove") {

            remove.length = 0;
            removeFolder.length = 0;
            var landingPageTblRows = $("#dataTable>tbody>tr");
            var backgroundCheckTblRows = $("#bgtable>tbody>tr");
            var tr = null;
            for (var i = 0; i < landingPageTblRows.length; i++) {
                tr = $(landingPageTblRows[i]);
                if (tr.hasClass("checked") && tr.find('td :input:checkbox').hasClass("case")) {
                    var fileId = tr.attr("data_fileId");
                    var fileDescriptionId = tr.attr("data_fileDescriptionId");
                    var fileLocation = tr.attr("data_fileLocation");
                    if (fileId == 0) {
                        bootbox.alert('One of the selected field is empty');

                        return;
                    } else if (tr.attr("data_flagId") == 3) {
                        bootbox.alert("approved file cannot be deleted");

                        return;
                    } else {
                        var data = {
                            "fileId": fileId,
                            "fileDescriptionId": fileDescriptionId,
                            "fileLocation": fileLocation
                        };
                        remove.push(data);
                    }
                } else if (tr.hasClass("categorychecked") && tr.find('td :input:checkbox').hasClass("category")) {
                    var categoryId = tr.attr("data_categoryId");

                    var datacat = {
                        "categoryId": parseInt(categoryId)
                    };
                    removeFolder.push(datacat);
                }
            }
            for (var i = 0; i < backgroundCheckTblRows.length; i++) {
                tr = $(backgroundCheckTblRows[i]);
                if (tr.hasClass("checked") && tr.find('td :input:checkbox').hasClass("case")) {
                    var fileId = tr.attr("data_fileId");
                    var fileDescriptionId = tr.attr("data_fileDescriptionId");
                    var fileLocation = tr.attr("data_fileLocation");
                    if (fileId == 0) {
                        bootbox.alert('One of the selected field is empty');

                        return;
                    } else if (tr.attr("data_flagId") == 3) {
                        bootbox.alert("approved file cannot be deleted");

                        return;
                    } else {
                        var data = {
                            "fileId": fileId,
                            "fileDescriptionId": fileDescriptionId,
                            "fileLocation": fileLocation
                        };
                        remove.push(data);
                    }
                } 
            }

            onRemove(remove);
            if(role == "ROLE_ADMIN")
            onFolderRemove(removeFolder);

        } else if (_target.attr("id") == "fileUpload") {
            $("#upload-single-doc").modal('hide');

            onUpload(_target);
        } else if (_target.attr("id") == "fileUploadBackgroundCheck") {
            $("#upload-background-check-doc").modal('hide');

            onUploadBackgroundCheck(_target);
        } else if (_target.attr("id") == "goback") {
            counter = backCounter;
            $('#goback').hide();
            $('#addFolder').show();
            $("#dataTable>tbody>tr").remove();

            values.parentCategoryId = backParent;
            if ($("#selectAll").hasClass("checked")) {
                $("#dataTable>tbody").append(back);
            } else {
                $("#dataTable>tbody").append(back);
                $("#dataTable>tbody>tr").removeClass("checked");
                $("#bgtable>tbody>tr").removeClass("checked");
                $('.case').prop('checked', false);
            }
        } else if (_target.hasClass("view-pdf")) {
            var str = _target.closest('tr').attr('data_fileLocation');
            if (_target.hasClass("bgc") && _target.closest("tr").attr("data_fileId") == 0) {
                $('#viewframe').attr('src', contextPath + "/resources/Sample_Authorization_Form.pdf");
            } else if (str.search(".docx") == -1)
                $('#viewframe').attr('src', _target.closest('tr').attr('data_fileLocation'));
            else
                $('#viewframe').attr('src', _target.closest('tr').attr('data_fileLocation').split(".docx")[0] + ".pdf");
            $("#dialog").dialog("open");
        } else if (_target.hasClass("browse")) {
            backCounter = counter;
            counter = 1;
            $('#goback').show();
            $('#addFolder').hide();
            onBrowse(_target);
        }
       
    });

    function renderLandingPageTable(values) {
    	
        var whtml;
        var divhtml;
        if (values.fileList != null) {
            for (var i = 0; i < values.fileList.length; i++) {
                if (values.fileList[i]["fileName"].trim() === "Photograph") {
                    bindBackgroundCheckControls(values.fileList[i]);
                } else {
                    whtml = "";
                    var remark = values.fileList[i]["remark"];
                    var lastModified = ((values.fileList[i]["lastModified"] != null) ? values.fileList[i]["lastModified"] : "");
                    whtml += "<tr class='dataTable' data_fileLocation='" + values.fileList[i]["fileLocation"] + "' data_fileName='" + values.fileList[i]["fileName"] + "' data_fileId='" + values.fileList[i]["fileId"] + "' data_fileDescriptionId='" + values.fileList[i]["fileDescriptionId"] + "' data_flagId='" + values.fileList[i]["flagId"] + "' data_id ='" + values.fileList[i]["fileId"] + "'>";
                    whtml += "<td><input type='checkbox'  class='case' name='case[]'></td>";
                    whtml += "<td>" + (counter) + "</td>";
                    whtml += "<td><img src='" + contextPath + "/resources/images/photo-icon.png'></td>";
                    if (values.fileList[i]["fileLocation"] != null) {
                        whtml += "<td class='description' width='100%'><a class='view-pdf'>" + values.fileList[i]["fileName"] + "</td> ";
                    } else
                        whtml += "<td class='description' width='100%'>" + values.fileList[i]["fileName"] + "</td> ";
                    whtml += "<td width='100%' >" + lastModified + "</td>";
                    whtml += "<td class='flag'><img src='" + contextPath + "/resources/images/" + values.fileList[i]["flagId"] + ".png'></td>";
                    //add role here 
                    if (role == "ROLE_USER" && values.fileList[i]["flagId"] != 3 && (values.preEmp.editFlag == 1 || values.preEmp.totalEditFlag == 1)) {
                        whtml += "<td><button type='button'id='upload'  class='btn  btn-link btn-xs'   data-target='#upload-single-doc'><span id='uploadthis' class='glyphicon glyphicon-pencil'></span></button></td>";
                    } else {
                        whtml += "<td></td>";
                    }
                    whtml += "</tr>";
                    $("#dataTable>tbody").append(whtml);
                    counter++;
                    if (remark != null && role == "ROLE_USER") {
                        divhtml = "";
                        divhtml += "<div class='alert alert-danger alert-dismissible' role='alert'>";
                        divhtml += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
                        divhtml += values.fileList[i]["fileName"] + " Rejected. " + "Reason:" + remark
                        divhtml += "</div>";
                        $("#notification").append(divhtml);
                    }
                }
            }
        }
        if (values.folderList != null) {
            for (var j = 0; j < values.folderList.length; j++) {
                whtml = "";
                whtml += "<tr data_parentCategoryId ='" + values.folderList[j]["parentCategoryId"] + "' data_categoryId ='" + values.folderList[j]["categoryId"] + "'>";
                if (role == "ROLE_ADMIN")
                    whtml += "<td><input type='checkbox' class='category' name='category[]'></td>";
                else
                    whtml += "<td><input type='checkbox' disabled='disabled'></td>";
                whtml += "<td>" + counter + "</td>";
                whtml += "<td><img src='" + contextPath + "/resources/images/folder-icon.png'></td>";
                whtml += "<td  width='100%'><a class='browse'>" + values.folderList[j]["categoryName"] + "<a></td>";
                if(role=="ROLE_ADMIN" && values.folderList[j]["visibility"] == 0){
                whtml += "<td colspan='2'><select class='form-control input-sm selectVisibility'><option value='1'>Visible</option><option selected='selected' value='0'>Not Visible</option></select></td>";
                }
                else if(role=="ROLE_ADMIN" && values.folderList[j]["visibility"] == 1){
                	whtml += "<td colspan='2'><select class='form-control input-sm selectVisibility'><option value='1' selected='selected'>Visible</option><option value='0'>Not Visible</option></select></td>";
                }
                else{
                whtml += "<td></td>";
                whtml += "<td></td>";
                }
                whtml += "<td></td>";
                whtml += "</tr>";
                $("#dataTable>tbody").append(whtml);
                counter++;
            }
        }
        $("#enable-user").hide();
        $("#total").append(values.uploadedDocumentCount);
        
        
        if (((values.preEmp.editFlag != 1 && values.preEmp.totalEditFlag == 0) && role == "ROLE_USER")) {
            $("#editPanel").hide();
            $("#submit").hide();
            $(".case").hide();
            $(".category").hide();
            $("#selectAll").hide();
        } else if (role == "ROLE_ADMIN") {
            $("#submit").hide();
        }
        $("#username").html(values.preEmp.username);
        $("#applicationId").html(values.preEmp.applicationId);
        $("#firstName").html(values.preEmp.firstName);
        $("#lastName").html(values.preEmp.lastName);
        $("#email").html(values.preEmp.email.split(",")[0]);
        if(values.preEmp.preFlag == 0){
        	$("#hr-status").addClass("alert-success");
        	$("#hr-status").html("HR Approved");
        	$("#make-employee").hide();
        	//$("#reject-employee").show();
        }
        else{
        	$("#hr-status").addClass("alert-warning");
    	$("#hr-status").html("HR Approval Pending");
    	//$("#reject-employee").hide();
        }
        if(values.preEmp.enableFlag == 0)
        {
        	$("#disable-user").hide();
        	$("#enable-user").show();
        	$("#reject-button").hide();
        	$("#approve-button").hide();
        	$("#enable-edit").hide();
        	$("#disable-edit").hide();
        }
        else
        {
         	$("#disable-user").show();
        	$("#enable-user").hide();
        	$("#reject-button").show();
        	$("#approve-button").show();
        	$("#enable-edit").show();
        	$("#disable-edit").show();
        	
        }
       if(role != "ROLE_USER"){ 
        if(values.preEmp.totalEditFlag == 0){
        	$("#enable-edit").show();
        	$("#disable-edit").hide();
        }
        else
        	{
        	$("#disable-edit").show();
        	$("#enable-edit").hide();
        	}
        	
    }
    }
    function onApprove(target) {

        var backgroundCheckTblRows = $("#bgtable>tbody>tr");

        var landingPageTblRows = $("#dataTable>tbody>tr");
        var tr = null;
        for (var i = 0; i < landingPageTblRows.length; i++) {
            tr = $(landingPageTblRows[i]);
            if (tr.hasClass("checked")) {
                if (tr.find('td').find('input').hasClass('case')) {
                    var fileId = tr.attr("data_id");
                    var flagId = tr.attr("data_flagId");
                    if (fileId == 0) {
                        bootbox.alert('One of the selected field is empty');

                        return;
                    } else {
                        var data = {
                            "fileId": fileId,
                            "currentStatus": flagId
                        };
                        approve.push(data);
                    }
                }

            }
        }
        for (var i = 0; i < backgroundCheckTblRows.length; i++) {
            tr = $(backgroundCheckTblRows[i]);
            if (tr.hasClass("checked")) {
                if (tr.find('td').find('input').hasClass('case')) {
                    var fileId = tr.attr("data_fileId");
                    var flagId = tr.attr("data_flagId");
                    if (fileId == 0) {
                        bootbox.alert('One of the selected field is empty');

                        return;
                    } else {
                        var data = {
                            "fileId": fileId,
                            "currentStatus": flagId
                        };
                        approve.push(data);
                    }
                }

            }
        }
        $.ajax({
            type: 'POST',
            url: 'approveFile',
            data: JSON.stringify(approve),
            dataType: 'json',
            contentType: 'application/json',
            success: function(data) {

                var json = data;
                var landingPageTblRows = $("#dataTable>tbody>tr");
                var backgroundCheckTblRows = $("#bgtable>tbody>tr");
                var tr = null;
                for (var m = 0; m < json.approvedList.length; m++) {
                    for (var i = 0; i < landingPageTblRows.length; i++) {
                        tr = $(landingPageTblRows[i]);
                        if (tr.hasClass("checked")) {
                            if (tr.attr("data_fileId") == json.approvedList[m]["fileId"]) {
                                tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.approvedList[m]["currentStatus"] + ".png'>");
                                tr.attr("data_flagId", 3);
                            }

                        }
                    }


                }
                for (var m = 0; m < json.approvedList.length; m++) {
                    for (var i = 0; i < backgroundCheckTblRows.length; i++) {
                        tr = $(backgroundCheckTblRows[i]);
                        if (tr.hasClass("checked")) {
                            if (tr.attr("data_fileId") == json.approvedList[m]["fileId"]) {
                                tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.approvedList[m]["currentStatus"] + ".png'>");
                                tr.attr("data_flagId", 3);
                            }

                        }
                    }


                }
            },
            error: function(data) {
            	if(data.readyState == 4 && data.status == 200){
                    window.location.replace('logout');
                    }else{
                bootbox.alert(data.statusText);
            }}
        });


    }

    function onUpload(target) {
        var landingPageTblRows = $("#dataTable>tbody>tr");
        var tr = null;
        var fileId;
        var flagId;
        var fileDescriptionId;
        for (var i = 0; i < landingPageTblRows.length; i++) {
            tr = $(landingPageTblRows[i]);
            if (tr.hasClass("clicked")) {
                fileId = tr.attr("data_id");
                fileDescriptionId = tr.attr("data_fileDescriptionId");
            }
        }
        $("#form2 :input[name='fileId']").attr("value", fileId);
        $("#form2 :input[name='fileDescriptionId']").attr("value", fileDescriptionId);
        $("#form2 :input[name='parentCategoryId']").attr("value", values.parentCategoryId);
        if ($("#form2 :input[name='file2']").val() == "") {
            $("#form2 :input[name='file2']").focus();
            return;
        }
        uploadJqueryForm();
    }

    function uploadJqueryForm() {
        $("#dataTable").loading({
            height: 440
        });

        $("#form2").ajaxForm({

            success: function(data) {
                values.uploadedDocumentCount++;
                $("#total").html(values.uploadedDocumentCount);
                renderRowOnSuccess(data);
                $("#dataTable>tbody>tr").removeClass('clicked');
                $('#upload-single-doc').modal('hide');
                $("#dataTableLoadingDiv").loading("hide");
            },
            error: function(data, status) {
                $("#dataTable>tbody>tr").removeClass('clicked');
                if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else if(data.status == 406){
                bootbox.alert("file with this name already exists.");
                }else{
                bootbox.alert(data.statusText);
                
                }
                $('#upload-single-doc').modal('hide');
                $("#dataTableLoadingDiv").loading("hide");
                },
            dataType: "json"
        }).submit();
    }

    function renderRowOnSuccess(data) {
        var json = data;
        var landingPageTblRows = $("#dataTable>tbody>tr");
        var tr = null;
        for (var i = 0; i < landingPageTblRows.length; i++) {
            tr = $(landingPageTblRows[i]);
            if (tr.hasClass("clicked")) {
                if (tr.attr("data_fileDescriptionId") == json.uploadData.fileDescriptionId) {
                    tr.attr("data_fileLocation", json.uploadData.fileLocation)
                    tr.attr("data_flagId", json.uploadData.flagId);
                    tr.attr("data_id", json.uploadData.fileId);
                    tr.attr("data_fileId", json.uploadData.fileId);
                    tr.attr("data_fileDescriptionId", json.uploadData.fileDescriptionId);
                    tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.uploadData.flagId + ".png'>");
                    tr.find("td").eq(3).html("<a class='view-pdf'>" + tr.attr("data_fileName") + "</a></td>");
                    tr.find("td").eq(4).html(json.uploadData.lastModified);

                }
            }
        }
    }

    function onBrowse(_target) {
        var parentCategoryId = parseInt(_target.closest('tr').attr('data_categoryId'));
        $.getJSON("insideFolder/" + parentCategoryId, function(folderPage) {
            back = $("#dataTable>tbody>tr").detach();
            backParent = values.parentCategoryId;
            values = folderPage;
            renderLandingPageTable(values);
        }).fail(function(data){
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }
        })
    }
});

function onReject(target) {

    var backgroundCheckTblRows = $("#bgtable>tbody>tr");
    var landingPageTblRows = $("#dataTable>tbody>tr");
    var tr = null;

    for (var i = 0; i < landingPageTblRows.length; i++) {
        tr = $(landingPageTblRows[i]);
        if (tr.hasClass("checked")) {
            if (tr.find('td').find('input').hasClass('case')) {
                var fileId = tr.attr("data_fileId");
                var flagId = tr.attr("data_flagId");
                if (fileId == 0) {
                    bootbox.alert('One of the selected field is empty');

                    return;
                }

                var remark= prompt("Reason for rejection of "+ tr.attr("data_fileName"));
                var data = {
                    "fileId": fileId,
                    "currentStatus": flagId,
                    "remark": remark
                };
                reject.push(data);

            }

        }
    }
    for (var i = 0; i < backgroundCheckTblRows.length; i++) {
        tr = $(backgroundCheckTblRows[i]);
        if (tr.hasClass("checked")) {
            if (tr.find('td').find('input').hasClass('case')) {
                var fileId = tr.attr("data_fileId");
                var flagId = tr.attr("data_flagId");
                if (fileId == 0) {
                    bootbox.alert('One of the selected field is empty');
                    return;
                }

               
                var remark= prompt("Reason for rejection of Background Check Authorization Form");
                var data = {
                    "fileId": fileId,
                    "currentStatus": flagId,
                    "remark": remark
                };
                reject.push(data);

            }

        }
    }
    $.ajax({
        type: 'POST',
        url: 'rejectFile',
        data: JSON.stringify(reject),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var json = data;
            var landingPageTblRows = $("#dataTable>tbody>tr");
            var backgroundCheckTblRows = $("#bgtable>tbody>tr");
            var tr = null;
            for (var m = 0; m < json.rejectedList.length; m++) {
                for (var i = 0; i < landingPageTblRows.length; i++) {
                    tr = $(landingPageTblRows[i]);
                    if (tr.hasClass("checked")) {
                        if (tr.attr("data_fileId") == json.rejectedList[m]["fileId"]) {
                            tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.rejectedList[m]["currentStatus"] + ".png'>");
                            tr.attr("data_flagId", 1);
                        }

                    }
                }
            }
            for (var m = 0; m < json.rejectedList.length; m++) {
                for (var i = 0; i < backgroundCheckTblRows.length; i++) {
                    tr = $(backgroundCheckTblRows[i]);
                    if (tr.hasClass("checked")) {
                        if (tr.attr("data_fileId") == json.rejectedList[m]["fileId"]) {
                            tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.rejectedList[m]["currentStatus"] + ".png'>");
                            tr.attr("data_flagId", 1);
                        }

                    }
                }


            }

        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
        }
        }
    });



}

function onRemove(remove) {
    $.ajax({
        type: 'POST',
        url: 'deleteFile',
        data: JSON.stringify(remove),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var json = data;
            var landingPageTblRows = $("#dataTable>tbody>tr");
            var backgroundCheckTblRows = $("#bgtable>tbody>tr");
            var tr = null;
            for (var m = 0; m < json.removedList.length; m++) {
                for (var i = 0; i < landingPageTblRows.length; i++) {
                    tr = $(landingPageTblRows[i]);
                    if (tr.hasClass("checked")) {
                        if (tr.attr("data_fileId") == json.removedList[m]["fileId"]) {
                            if (tr.attr("data_fileDescriptionId") == 0) {
                                values.uploadedDocumentCount--;
                                $("#total").html(values.uploadedDocumentCount);
                                tr.remove();
                            } else {
                                values.uploadedDocumentCount--;
                                $("#total").html(values.uploadedDocumentCount);
                                tr.attr("data_flagId", 0);
                                tr.attr("data_id", 0);
                                tr.attr("data_fileId", 0);
                                tr.attr("data_fileLocation", "");
                                tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/0.png'>");
                                tr.find("td").eq(4).html("");
                                tr.find("td").eq(3).html("<td class='description' width='100%'>" + tr.attr("data_fileName") + "</td> ");

                            }
                        }
                    }
                }

            }
            for (var m = 0; m < json.removedList.length; m++) {
                for (var i = 0; i < backgroundCheckTblRows.length; i++) {
                    tr = $(backgroundCheckTblRows[i]);
                    if (tr.hasClass("checked")) {
                        if (tr.attr("data_fileId") == json.removedList[m]["fileId"]) {
                                tr.attr("data_flagId", 0);
                                tr.attr("data_fileId", 0);
                                tr.attr("data_fileLocation", "");
                                tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/0.png'>");
                        }
                    }
                }

            }
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });
}

function onFolderRemove(removeFolder) {
    $.ajax({
        type: 'POST',
        url: 'deleteFolder',
        data: JSON.stringify(removeFolder),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var json = data;
            var landingPageTblRows = $("#dataTable>tbody>tr");
            var tr = null;

            for (var m = 0; m < json.deletedFolder.length; m++) {
                for (var i = 0; i < landingPageTblRows.length; i++) {
                    tr = $(landingPageTblRows[i]);
                    if (tr.hasClass("categorychecked")) {
                        if (tr.attr("data_categoryId") == json.deletedFolder[m].categoryId) {
                            tr.remove();
                        }
                    }
                }

            }
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });
}

function uploadJqueryForm2() {

    if ($("#form3 :input[name='file2']").val() == "") {
        $("#form3 :input[name='file2']").focus();
        return;
    }
    $("#upload-non-default-doc").modal('hide');
    $("#form3 :input[name='parentCategoryId']").attr("value", values.parentCategoryId);
    $("#dataTable").loading({
        height: 440
    });

    $("#form3").ajaxForm({

        success: function(data) {
            renderRowOnSuccessNonDefault(data);
            $('#upload-non-default-doc').modal('hide');
            $("#dataTableLoadingDiv").loading("hide");
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else if(data.status == 406){
                bootbox.alert("file with this name already exists.");
                }else{
            bootbox.alert(data.statusText);
           
                }
        	 $('#upload-non-default-doc').modal('hide');
             $("#dataTableLoadingDiv").loading("hide");
        },
        dataType: "text"
    }).submit();
}

function renderRowOnSuccessNonDefault(data) {
    var whtml = "";
    var json = JSON.parse(data);
    whtml += "<tr class='dataTable'  data_fileLocation='" + json.uploadData.fileLocation + "' data_fileId='" + json.uploadData.fileId + "' data_fileName='" + json.uploadData.fileName + "' data_fileDescriptionId='" + json.uploadData.fileDescriptionId + "' data_flagId='" + json.uploadData.flagId + "' data_id ='" + json.uploadData.fileId + "'>";
    whtml += "<td><input type='checkbox' class='case' name='case[]'></td>";
    whtml += "<td>" + counter + "</td>";
    whtml += "<td><img src='" + contextPath + "/resources/images/photo-icon.png'></td>";
    if (json.uploadData.fileLocation != null) {
        whtml += "<td class='description' width='100%'><a class='view-pdf'>" + json.uploadData.fileName + "</a></td> ";
    } else
        whtml += "<td class='description' width='100%'>" + json.uploadData.fileName + "</td> ";
    whtml += "<td width='100%'>" + json.uploadData.lastModified + "</td>";
    whtml += "<td class='flag'><img src='" + contextPath + "/resources/images/" + json.uploadData.flagId + ".png'></td>";
    whtml += "<td  ><button type='button'id='upload'  class='btn  btn-link btn-xs'   data-target='#upload-single-doc'><span id='uploadthis' class='glyphicon glyphicon-pencil'></span></button></td>";
    whtml += "</tr>";
    $("#dataTable>tbody").append(whtml);
    counter++;
}


function createFolderForm() {
    if ($("#form4 :input[name='categoryName']").val() == "") {
        $("#form4 :input[name='categoryName']").focus();
        return;
    }
    var categoryName = $("#form4 :input[name='categoryName']").val();
    var visibility = $("#form4 :input[id='visibleToUser']:checked").length;
    var parentCategoryId = parseInt(values.parentCategoryId);
    var data = {
        "categoryName": categoryName,
        "parentCategoryId": parentCategoryId,
        "visibility" : visibility
    };
    $.ajax({
        type: 'POST',
        url: 'createFolder',
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            renderRowOnFolderAdd(data);
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });


    function renderRowOnFolderAdd(data) {
        var whtml = "";
        var json = data;
        whtml += "<tr data_parentCategoryId =" + json.parentCategoryId + " data_categoryId =" + json.categoryId + ">";
        whtml += "<td><input type='checkbox' class= 'category' name='category[]'></td>";
        whtml += "<td>" + counter++ +"</td>";
        whtml += "<td><img src='" + contextPath + "/resources/images/folder-icon.png'></td>";
        whtml += "<td  width='100%'><a class='browse'>" + json.categoryName + "<a></td>";
        if(role=="ROLE_ADMIN" && json.visibility == 0){
            whtml += "<td colspan='2'><select class='form-control input-sm selectVisibility'><option value='1'>Visible</option><option selected='selected' value='0'>Not Visible</option></select></td>";
            }
            else if(role=="ROLE_ADMIN" && json.visibility == 1){
            	whtml += "<td colspan='2'><select class='form-control input-sm selectVisibility'><option selected='selected' value='1'>Visible</option><option value='0' >Not Visible</option></select></td>";
            }
            else{
            whtml += "<td></td>";
            whtml += "<td></td>";
            }
        whtml += "<td></td>";
        whtml += "</tr>";
        $("#dataTable>tbody").append(whtml);
    }
}

function makeEmployeeForm() {
    if ($("#employeeForm :input[name='employeeId']").val() == "") {
        $("#employeeForm :input[name='employeeId']").focus();
        return;
    }
    if ($("#employeeForm :input[name='employeeEmail']").val() == "") {
        $("#employeeForm :input[name='employeeEmail']").focus();
        return;
    } else if ($("#employeeForm :input[name='employeeId']").val() != "" && isEmail($("#employeeForm :input[name='employeeEmail']").val())) {
        $('#makeEmployee').modal('hide');
        var employeeId = parseInt($("#employeeForm :input[name='employeeId']").val());
        var employeeEmail = $("#employeeForm :input[name='employeeEmail']").val();
        var data = {
            "empId": employeeId,
            "email": employeeEmail,
            "applicationId":values.preEmp.applicationId
        };
        makeEmployeeData.push(data);
        $.ajax({
            type: 'POST',
            url: 'makeMultipleEmployees',
            data: JSON.stringify(makeEmployeeData),
            dataType: 'json',
            contentType: 'application/json',
            success: function(data) {
            	$("#reject-employee").show();
            	$("#make-employee").hide();
            	$("#hr-status").removeClass("alert-warning");
            	$("#hr-status").addClass("alert-success");
            	$("#hr-status").html("HR Approved");
                bootbox.alert("employeeAdded");
                
            },
            error: function(data) {
            	if(data.readyState == 4 && data.status == 200){
                    window.location.replace('logout');
                    }else{
                bootbox.alert(data.statusText);
                    }
            }
        });
    }
}

function onSubmit() {
    var applicationId = values.preEmp.applicationId;
    data = {
        "applicationId": parseInt(applicationId)
    };
    $.ajax({
        type: 'POST',
        url: 'submitData',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data) {
            location.reload();
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });
}

function onUploadBackgroundCheck(target) {
    var landingPageTblRows = $("#bgtable>tbody>tr");
    var tr = null;
    var fileId;
    var fileDescriptionId;
    for (var i = 0; i < landingPageTblRows.length; i++) {
        tr = $(landingPageTblRows[i]);
        if (tr.hasClass("clicked")) {
            fileId = tr.attr("data_fileId");
            fileDescriptionId = tr.attr("data_fileDescriptionId");
        }
    }
    $("#bgform :input[name='fileId']").attr("value", fileId);
    $("#bgform :input[name='fileDescriptionId']").attr("value", fileDescriptionId);
    $("#bgform :input[name='parentCategoryId']").attr("value", values.parentCategoryId);
    if ($("#bgform :input[name='file2']").val() == "") {
        $("#bgform :input[name='file2']").focus();
        return;
    }
    uploadJqueryFormBackgroundCheck();
}

function uploadJqueryFormBackgroundCheck() {
    $("#bgtable").loading({
        height: 55
    });

    $("#bgform").ajaxForm({

        success: function(data) {
            values.uploadedDocumentCount++;
            $("#total").html(values.uploadedDocumentCount);
            renderRowOnSuccessBackgroundCheck(data);
            $("#bgtable>tbody>tr").removeClass('clicked');
            $('#upload-background-check-doc').modal('hide');
            $("#bgtableLoadingDiv").loading("hide");
            $("submit").attr("disabled", false);
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }
        	else if(data.status == 406){
                bootbox.alert("file with this name already exists.");
                }else{
                	bootbox.alert(data.statusText);
           
                }
        	 $("#bgtable>tbody>tr").removeClass('clicked');
             
             $('#upload-background-check-doc').modal('hide');
             $("#bgtableLoadingDiv").loading("hide");
        },
        dataType: "json"
    }).submit();
}

function renderRowOnSuccessBackgroundCheck(data) {
    var json = data;
    var landingPageTblRows = $("#bgtable>tbody>tr");
    var tr = null;
    for (var i = 0; i < landingPageTblRows.length; i++) {
        tr = $(landingPageTblRows[i]);
        if (tr.hasClass("clicked")) {
            if (tr.attr("data_fileDescriptionId") == json.uploadData.fileDescriptionId) {
                tr.attr("data_fileLocation", json.uploadData.fileLocation)
                tr.attr("data_fileId", json.uploadData.fileId);
                tr.attr("data_fileDescriptionId", json.uploadData.fileDescriptionId);
                tr.find("td").find(":input[type='image']").attr('src', contextPath + "/" +json.uploadData.fileLocation);
                /*tr.find("td.flag").html("<img src='" + contextPath + "/resources/images/" + json.uploadData.flagId + ".png'>");
*/
            }
        }
    }
}

function bindBackgroundCheckControls(values) {
    var whtml;
    var divhtml;

    /*if (values["flagId"] != 0) {
        var flag = values["flagId"];
    } else
        var flag = 0;*/
    $("#bgtable>tbody>tr").attr("data_fileDescriptionId", values["fileDescriptionId"]);
    $("#bgtable>tbody>tr").attr("data_fileId", values["fileId"]);
    $("#bgtable>tbody>tr").attr("data_fileLocation", values["fileLocation"]);
    if(values["fileLocation"] != null){
    	 $("#bgtable>tbody>tr").find("td").find(":input[type='image']").attr('src', contextPath + "/" +values["fileLocation"]);
    }
    /*$("#bgtable>tbody>tr").attr("data_flagId", values["flagId"]);
    $("#bgtable>tbody>tr").find("td.flag").html("<img src='" + contextPath + "/resources/images/" + flag + ".png'>");
    if(flag == 3){
    	$("#uploadthisbgCheck").hide();
    }*/
    /*if(values["fileId"]== 0){
    	$("#submit").attr("disabled",true);
    }*/
    /*var remark = values["remark"];

    if (remark != null && role == "ROLE_USER") {
        divhtml = "";
        divhtml += "<div class='alert alert-danger alert-dismissible' role='alert'>";
        divhtml += "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>";
        divhtml += values["fileName"] + "Rejected. " + "Reason:" + remark
        divhtml += "</div>";
        $("#notification").append(divhtml);
    }*/
}


function rejectEmployee() {
   
        $.ajax({
            type: 'POST',
            url: 'rejectEmployee',
            dataType: 'json',
            contentType: 'application/json',
            success: function(data) {
            	$("#make-employee").show();
            	$("#reject-employee").hide();
            	$("#hr-status").removeClass("alert-success");
            	$("#hr-status").addClass("alert-warning");
            	$("#hr-status").html("HR Approval Pending");
                bootbox.alert("employee Rejected");
                
            },
            error: function(data) {
            	if(data.readyState == 4 && data.status == 200){
                    window.location.replace('logout');
                    }else{
                bootbox.alert(data.statusText);
            }
            }
        });
    
}

function onEnableDisableUser(status)
{
	var url;
	if(status)
	{
		url = 'enableUser';
	}
	else
	{
		url = 'disableUser';
	}
	$.ajax({
	    type: 'POST',
	    url: url,
	    dataType: 'json',
	    contentType: 'application/json',
	    success: function(data) {
	    	if(status == false)
    		{
		    	if(data.status == true)
		    	{
		    		$("#approve-button").hide();
		    		$("#reject-button").hide();
		    		$("#disable-user").hide();
		    		$("#enable-user").show();    		
		    		//bootbox.alert("Could not enable user");
		    	}
		    	else
		    	{
		    		bootbox.alert("Could not disable user");
		    	}
	    	}
	    	else
	    	{
	    		if(data.status == true)
		    	{
		    		$("#approve-button").show();
		    		$("#reject-button").show();
		    		$("#disable-user").show();
		    		$("#enable-user").hide();    		
		    		//bootbox.alert("Could not enable user");
		    	}
		    	else
		    	{
		    		bootbox.alert("Could not enable user");
		    	}
	    	}
	    },
	    error: function(data) {
	    	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
	        bootbox.alert(data.statusText);
                }
	    }
	});
	
}

function onFolderApprove(){
	var landingPageTblRows = $("#dataTable>tbody>tr");
    var tr = null;
    for (var i = 0; i < landingPageTblRows.length; i++) {
        tr = $(landingPageTblRows[i]);
        if (tr.hasClass("categorychecked")) {
            if (tr.find('td').find('input').hasClass('category')) {
                var categoryId = tr.attr("data_categoryId");
                var data = {
                        "categoryId": categoryId
                    };
                    approveFolder.push(data);
                }
            }

        }
    
    $.ajax({
        type: 'POST',
        url: 'approveFolder',
        data: JSON.stringify(approveFolder),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var json = data;
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });
    }

function onFolderReject(){
	var landingPageTblRows = $("#dataTable>tbody>tr");
    var tr = null;
    for (var i = 0; i < landingPageTblRows.length; i++) {
        tr = $(landingPageTblRows[i]);
        if (tr.hasClass("categorychecked")) {
            if (tr.find('td').find('input').hasClass('category')) {
                var categoryId = tr.attr("data_categoryId");
                var data = {
                        "categoryId": categoryId
                    };
                    rejectFolder.push(data);
                }
            }

        }
    
    $.ajax({
        type: 'POST',
        url: 'rejectFolder',
        data: JSON.stringify(rejectFolder),
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var json = data;
        },
        error: function(data) {
        	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
            bootbox.alert(data.statusText);
                }
        }
    });
    }
function changeVisiblity(data){
	 $.ajax({
	        type: 'POST',
	        url: 'changeFolderVisibility',
	        data: JSON.stringify(data),
	        contentType: 'application/json',
	        success: function(data) {
	            var json = data;
	        },
	        error: function(data) {
	        	if(data.readyState == 4 && data.status == 200){
	                window.location.replace('logout');
	                }else{
	            bootbox.alert(data.statusText);
	                }
	        }
	    });
	    
}
function sendWelcomeMail(data){
	
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
}

function onEnableDisableEdit(status)
{
	var url;
	if(status)
	{
		url = 'enableEdit';
	}
	else
	{
		url = 'disableEdit';
	}
	$.ajax({
	    type: 'POST',
	    url: url,
	    dataType: 'json',
	    contentType: 'application/json',
	    success: function(data) {
	    	if(status == false)
    		{
		    	if(data.status == true)
		    	{
		    		
		    		$("#enable-edit").show(); 
		    		$("#disable-edit").hide();
		    		
		    	}
		    	else
		    	{
		    		bootbox.alert("Could not disable user");
		    	}
	    	}
	    	else
	    	{
	    		if(data.status == true)
		    	{
		    		
		    		$("#disable-edit").show();
		    		$("#enable-edit").hide(); 
		    		
		    	}
		    	else
		    	{
		    		bootbox.alert("Could not enable user");
		    	}
	    	}
	    },
	    error: function(data) {
	    	if(data.readyState == 4 && data.status == 200){
                window.location.replace('logout');
                }else{
	        bootbox.alert(data.statusText);
                }
	    }
	});
	
}

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}