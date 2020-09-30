package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.allobjects.User;

//import org.json.JSONObject;

import com.dbconnection.DB_Connect;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();  
    }


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json");
		PrintWriter out=resp.getWriter();
		
		
		ObjectMapper om=new ObjectMapper();
		
		User user=om.readValue(req.getInputStream(), User.class);
		System.out.println(user);
		
		String firstname=user.getFirstname();
		String lastname=user.getLastname();
		String gender=user.getGender();
		String dob=user.getDob();
		String email=user.getEmail();
		String password=user.getPassword();
		String status="Active";
		
		LocalDate ld=LocalDate.now();
		String curdate=ld.toString();
		
		String query="insert into user(firstname,lastname,gender,dob,email,password,activated_date,status) values(?,?,?,?,?,?,?,?)";
		
		Connection con=DB_Connect.getConnect();
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, gender);
			ps.setString(4, dob);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.setString(7, curdate);
			ps.setString(8, status);
			int i=ps.executeUpdate();
			
			if(i>0) {
				dataInserted(i,req,resp,out);
			}
			
		}
		catch(MySQLIntegrityConstraintViolationException e) {
			String result="PassWord or Email Already Exist Please Enter Unique One";
//			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Origin", "*");

			out.write(om.writeValueAsString(result));
			
			e.printStackTrace();
		}
		catch (Exception e) {

			String result="Not Inserted";
			
//			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Origin", "*");

			out.write(om.writeValueAsString(result));
		
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void dataInserted(int i,HttpServletRequest req, HttpServletResponse resp,PrintWriter out) {
		if(i>0) {
			ObjectMapper om=new ObjectMapper();
			
			String result="Registration Successfull... You can Login Now";
			
//			resp.setHeader("Access-Control-Allow-Origin", "*");
			resp.addHeader("Access-Control-Allow-Origin", "*");
			
			try {
				result =om.writeValueAsString(result);
				out.write(result);
				
			} catch (JsonGenerationException e) {
				
				e.printStackTrace();
			} catch (JsonMappingException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}

}
