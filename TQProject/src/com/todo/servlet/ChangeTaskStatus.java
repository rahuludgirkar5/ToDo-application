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


@WebServlet("/changetaskstatus")
public class ChangeTaskStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ChangeTaskStatus() {
        super();
        
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		int taskid=Integer.parseInt(req.getParameter("taskid"));
		String status="Complete";
		PreparedStatement ps=null;
		String query="update todolist set status=? where taskid=?";
		
		Connection con=DB_Connect.getConnect();
		
		try {
			ps=con.prepareStatement(query);
			ps.setString(1, status);
			ps.setInt(2, taskid);
			int i=ps.executeUpdate();
			
			if(i>0) {
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write("Task Completed");
			}
			else
			{
				resp.addHeader("Access-Control-Allow-Origin", "*");
				out.write("Something Wrong on Server Side");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
