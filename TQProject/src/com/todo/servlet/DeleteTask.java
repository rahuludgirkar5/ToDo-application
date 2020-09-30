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

import com.dbconnection.DB_Connect;


@WebServlet("/deletetask")
public class DeleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteTask() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		int taskid=Integer.parseInt(req.getParameter("taskid"));
		String query ="delete from todolist where taskid=?";
		Connection con=DB_Connect.getConnect();
		PreparedStatement ps=null;
		try {
			
			ps=con.prepareStatement(query);
			ps.setInt(1, taskid);
			int i=ps.executeUpdate();
			
			if(i>0) {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write("Deleted");
		
			}else {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.print("Not Deleted");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
