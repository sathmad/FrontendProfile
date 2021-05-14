package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Profile {
 
	//A common method to connect to the DB
		public Connection connect() {
			System.out.println("Inside connect method");
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/frontend", "root", ""); 
				System.out.println("successfully connected");
			} catch (Exception e) {
				System.out.println("not connected");
				e.printStackTrace();
			}
			return con;
		}
		
		public String readProfiles()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Customer Name</th><th>Customer Email</th>" +
						"<th>Customer NIC</th>" +
						"<th>Customer PhoneNumber</th>" +
						"<th>Customer Address</th>" +
						"<th>Update</th><th>Remove</th></tr>";

				String query = "select * from profiles";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					String customerID = Integer.toString(rs.getInt("customerID"));
					String customerName = rs.getString("customerName");
					String customerEmail = rs.getString("customerEmail");
					String customerNIC = rs.getString("customerNIC");
					String customerPhoneNum = rs.getString("customerPhoneNum");
					String customerAddress = rs.getString("customerAddress");
					// Add into the html table
					output += "<tr><td>" + customerName + "</td>";
					output += "<td>" + customerEmail + "</td>";
					output += "<td>" + customerNIC + "</td>";
					output += "<td>" + customerPhoneNum + "</td>";
					output += "<td>" + customerAddress + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"+ "<td><form method='post' action='profiles.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='customerID' type='hidden' value='" + customerID+ "'>" + "</form></td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String insertProfile(String name, String email, String nic, String phnnumber, String address)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into profiles(`customerID`,`customerName`,`customerEmail`,`customerNIC`,`customerPhoneNum`,`customerAddress`)"
						+ " values (?, ?, ?, ?, ?,?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, email);
				preparedStmt.setString(4, nic);
				preparedStmt.setString(5, phnnumber);
				preparedStmt.setString(6, address);
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProfiles = readProfiles();
				output = "{\"status\":\"success\",\"data\":\""+newProfiles + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\",\"data\":\"Error while inserting the profile.\"}";
						
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String updateProfile(String ID, String name, String email, String nic, String phnnumber, String address)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE profiles SET customerName=?,customerEmail=?,customerNIC=?,customerPhoneNum=?,customerAddress=?WHERE customerID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, email);
				preparedStmt.setString(3, nic);
				preparedStmt.setString(4, phnnumber);
				preparedStmt.setString(5, address);
				preparedStmt.setInt(6, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProfiles = readProfiles();
				output ="{\"status\":\"success\",\"data\":\""+newProfiles + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\",\"data\":\"Error while updating the profile.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String deleteProfile(String customerID)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// create a prepared statement
				String query = "delete from profiles where customerID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(customerID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newProfiles = readProfiles();
				output ="{\"status\":\"success\",\"data\":\""+newProfiles + "\"}";
				
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\",\"data\":\"Error while deleting the profile.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}

		public String insertProfile(String parameter, String parameter2, String parameter3, String parameter4,
				String parameter5, String parameter6) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	
}
