package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.dbconnection.DB_Connect;


@WebServlet("/report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Report() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/json");
		PrintWriter out=res.getWriter();
		
		ObjectMapper om = new ObjectMapper();

		ArrayList<Integer> al=new ArrayList<>();
		
		LocalDate date1=LocalDate.now();
		LocalDate date2 =date1.minusDays(7);
		
		String curdate=date1.toString();
		String date7daysago = date2.toString();
		String act="Active";
		String deact="Deactivated";
		Connection con = DB_Connect.getConnect();
		try {
			int dcount=deactiveCount(con,curdate,date7daysago,deact);
			int actcount=activeCount(con,curdate,date7daysago,act);
			int totaltask=taskCount(con,curdate,date7daysago);
			
			al.add(actcount);
			al.add(dcount);
			al.add(totaltask);
			
			con.close();
			String rep=om.writeValueAsString(al);
			
			res.addHeader("Access-Control-Allow-Origin", "*");
			out.write(rep);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int deactiveCount(Connection con, String curdate,String date7daysago,String status) {
		String pendingquery="select count(*) from user where status=? and activated_date between ? and ?";
		Integer pcount=0;
		PreparedStatement ps=null;
		ResultSet dact=null;
		
		try {
			ps=con.prepareStatement(pendingquery);
			ps.setString(1, status);
			ps.setString(2, date7daysago);
			ps.setString(3, curdate);
			dact = ps.executeQuery();
		
			if(dact.next()) {
				pcount=dact.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return pcount;
	}
	public int activeCount(Connection con, String curdate,String date7daysago,String status) {
		String activequery = "select count(*) from user where status=? and activated_date between ? and ?";
		Integer acount=0;
		PreparedStatement ps=null;
		ResultSet act=null;
		
		try {
			ps=con.prepareStatement(activequery);
			ps.setString(1, status);
			ps.setString(2, date7daysago);
			ps.setString(3, curdate);
			act = ps.executeQuery();
		
			if(act.next()) {
				acount=act.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return acount;
	}
	
	public int taskCount(Connection con, String curdate,String date7daysago) {
		
		String taskquery ="select count(*) from todolist where taskdate between ? and ?";
		Integer tcount=0;
		PreparedStatement ps=null;
		ResultSet task=null;
		
		try {
			ps=con.prepareStatement(taskquery);
			ps.setString(1, date7daysago);
			ps.setString(2, curdate);
			task = ps.executeQuery();
		
			if(task.next()) {
				tcount=task.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tcount;
	}

}
