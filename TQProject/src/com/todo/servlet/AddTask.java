package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.allobjects.AddToDoTask;
import com.dbconnection.DB_Connect;


@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AddTask() {
        super();  
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
		
	}
 	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/json");
		PrintWriter out=resp.getWriter();
		
		ObjectMapper om=new ObjectMapper();
		
		AddToDoTask addtask= om.readValue(req.getInputStream(), AddToDoTask.class);
		
		String taskname =addtask.getTaskname();
		String desc =addtask.getDescription();
		String status = "Pending";
		String email=addtask.getEmail();
		
		LocalDate ld=LocalDate.now();
		String curdate=ld.toString();
		
		String query ="insert into todolist(taskname,description,status,user_email,taskdate) values(?,?,?,?,?)";
		Connection con=DB_Connect.getConnect();
		PreparedStatement ps=null;
		
		try {
			
			ps=con.prepareStatement(query);
			ps.setString(1,taskname);
			ps.setString(2, desc);
			ps.setString(3, status);
			ps.setString(4,email);
			ps.setString(5, curdate);
			int j=ps.executeUpdate();
			
			if(j>0) {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write("Task Added");
			}
			else {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write("Task Not Added Something Wrong");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
