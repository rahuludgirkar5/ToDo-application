package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.*;
import com.allobjects.UserEmailPass;
import com.dbconnection.DB_Connect;


@WebServlet("/ActivateUser")
public class ActivateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ActivateUser() {
        super();
        
    }
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/json");
		PrintWriter out=res.getWriter();
		
		ObjectMapper om=new ObjectMapper();
		
		UserEmailPass user=om.readValue(req.getInputStream(), UserEmailPass.class);
		
		String email=user.getEmail();
		String password=user.getPassword();
		
		Integer i=0;
		PreparedStatement ps =null;
		String status="Active";
		
		String updatequery="update user set status=? where email=? and password=?";
		
		try {
			
			Connection con= DB_Connect.getConnect();
			ps=con.prepareStatement(updatequery);
			ps.setString(1, status);
			ps.setString(2, email);
			ps.setString(3, password);
			i=ps.executeUpdate();
			
			if(i>0) {
				con.close();
				
				String result="User Activation Successfull";
				
//				resp.setHeader("Access-Control-Allow-Origin", "*");
				res.addHeader("Access-Control-Allow-Origin", "*");
				out.write(om.writeValueAsString(result));
			}
			else {
				String result="User Activation Failed";

				res.addHeader("Access-Control-Allow-Origin", "*");
				out.write(om.writeValueAsString(result));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
