$(document).ready(function()
		{ 
	if ($("#alertSuccess").text().trim() == "") 
	{ 
		$("#alertSuccess").hide(); 
	} 
	$("#alertError").hide(); 
		}); 
$(document).on("click", "#btnSave", function(event)
		{ 
//	Clear alerts---------------------
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide(); 
//	Form validation-------------------
	var status = validateProfileForm(); 
	if (status != true) 
	{ 
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	} 
//	If valid------------------------
	var type = ($("#hidcustomerIDSave").val() == "") ? "POST" : "PUT"; 
	$.ajax( 
			{ 
				url : "ProfilesAPI", 
				type : type, 
				data : $("#formProfile").serialize(), 
				dataType : "text", 
				complete : function(response, status) 
				{ 
					onProfileSaveComplete(response.responseText, status); 
				} 
			}); 
		});

function onProfileSaveComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{ 
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show(); 
			$("#divProfilesGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		} 
	} else if (status == "error") 
	{ 
		$("#alertError").text("Error while saving."); 
		$("#alertError").show(); 
	} else
	{ 
		$("#alertError").text("Unknown error while saving.."); 
		$("#alertError").show(); 
	} 
	$("#hidcustomerIDSave").val(""); 
	$("#formProfile")[0].reset(); 
}

$(document).on("click", ".btnUpdate", function(event)
		{ 
	$("#hidcustomerIDSave").val($(this).data("customerID")); 
	$("#customerName").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#customerEmail").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#customerNIC").val($(this).closest("tr").find('td:eq(2)').text());
	$("#customerPhoneNum").val($(this).closest("tr").find('td:eq(3)').text());
	$("#customerAddress").val($(this).closest("tr").find('td:eq(4)').text()); 
		});

$(document).on("click", ".btnRemove", function(event)
		{ 
	$.ajax( 
			{ 
				url : "ProfilesAPI", 
				type : "DELETE", 
				data : "customerID=" + $(this).data("customerid"),
				dataType : "text", 
				complete : function(response, status) 
				{ 
					onProfileDeleteComplete(response.responseText, status); 
				} 
			}); 
		});

function onProfileDeleteComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{ 
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			$("#divProfilesGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		} 
	} else if (status == "error") 
	{ 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} else
	{ 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}
function validateProfileForm() 
{ 
//	NAME
	if ($("#customerName").val().trim() == "") 
	{ 
		return "Insert Customer Name."; 
	} 
//	EMAIL
	if ($("#customerEmail").val().trim() == "") 
	{ 
		return "Insert Customer Email."; 
	} 9
//	NIC-------------------------------
	if ($("#customerNIC").val().trim() == "") 
	{ 
		return "Insert Customer NIC."; 
	} 
//	PNUMBER
	if ($("#customerPhoneNum").val().trim() == "") 
	{ 
		return "Insert Customer PhoneNumber."; 
	} 9

//	ADDRESS------------------------
	if ($("#customerAddress").val().trim() == "") 
	{ 
		return "Insert Customer Address."; 
	} 
	return true; 
}
