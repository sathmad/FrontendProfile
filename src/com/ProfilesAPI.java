package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

/**
 * Servlet implementation class ProfilesAPI
 */
@WebServlet("/ProfilesAPI")
public class ProfilesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Profile profileObj = new Profile();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesAPI() {
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got insert");

		String output = profileObj.insertProfile(request.getParameter("customerID"), 
				 request.getParameter("customerName"), 
				request.getParameter("customerEmail"), 
				request.getParameter("customerNIC"),
				request.getParameter("customerPhoneNum"),
				request.getParameter("customerAddress"));
				response.getWriter().write(output);	
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got here");
		Map paras = getParasMap(request); 
		 String output = profileObj.updateProfile(paras.get("hidcustomerIDSave").toString(), 
		 paras.get("customerName").toString(), 
		 paras.get("customerEmail").toString(), 
		paras.get("customerNIC").toString(),
		paras.get("customerPhoneNum").toString(),
		paras.get("customerAddress").toString()); 
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = profileObj.deleteProfile(paras.get("customerID").toString()); 
		response.getWriter().write(output);
	}
	//new comment
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			String queryString = scanner.hasNext() ? 
					scanner.useDelimiter("\\A").next() : ""; 
					scanner.close(); 
					String[] params = queryString.split("&"); 
					for (String param : params) 
					{ 
						String[] p = param.split("=");
						map.put(p[0], p[1]); 
					} 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace();
		} 
		return map; 
	}

}
