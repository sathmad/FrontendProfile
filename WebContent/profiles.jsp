<%@page import="com.Profile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/profiles.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Profile V10.1</h1>
				<form id="formProfile" name="formProfile">
					Customer Name:- 
					<input id="	customerName" name="customerName" type="text"
						class="form-control form-control-sm"> <br> 
					Customer Email:-
					<input id="customerEmail" name="customerEmail" type="text"
						class="form-control form-control-sm"> <br> 
					Customer NIC:-
					 <input id="customerNIC" name="customerNIC" type="text"
						class="form-control form-control-sm"> <br> 
					Phone Number:-
					 <input id="customerPhoneNum" name="customerPhoneNum" type="text"
						class="form-control form-control-sm"> <br> 
					Address:-
					 <input id="customerAddress" name="customerAddress" type="text"
						class="form-control form-control-sm"> 
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
					<input type="hidden"
						id="hidcustomerIDSave" name="hidcustomerIDSave" value="">
						
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProfilesGrid">
					<%
					Profile profileObj = new Profile();
					out.print(profileObj.readProfiles());
					%>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>