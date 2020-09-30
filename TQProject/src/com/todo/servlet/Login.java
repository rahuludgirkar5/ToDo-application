package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;


import com.allobjects.UserEmailPass;
import com.dbconnection.DB_Connect;
//import com.fasterxml.jackson.databind.ObjectMapper;



@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json");
		PrintWriter out=resp.getWriter();
		
		ObjectMapper om = new ObjectMapper();
		
		UserEmailPass idpass=om.readValue(req.getInputStream(), UserEmailPass.class);
		
		String email=idpass.getEmail();
		String password=idpass.getPassword();
		String result="";
		PreparedStatement ps =null;
		PreparedStatement psadmin=null;
		String query="select * from user";
		String adminquery="select * from admin";
		
		Connection con= DB_Connect.getConnect();
		
		try {
			
			ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			
			psadmin=con.prepareStatement(adminquery);
			ResultSet adminrs=psadmin.executeQuery();
			
			while(rs.next()) {
				String em=rs.getString(6);
				String pass=rs.getString(7);
				String status=rs.getString(9);
				if(em.equals(email) && pass.equals(password) && status.equalsIgnoreCase("Active")) {
					result="user";
					break;
					}
			}
			if(result.equals("")) {
				while(adminrs.next()) {
					String em=adminrs.getString(2);
					String pass=adminrs.getString(3);
					if(em.equals(email) && pass.equals(password)) {
						result="Admin";
						break;		
						}
				}
			}
			
			con.close();
			if(result.equals("")) {
				result="Not Match";
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write(om.writeValueAsString(result));
			}
			else {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write(om.writeValueAsString(result));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
