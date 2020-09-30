package com.getdata.cls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allobjects.ToDo;
import com.dbconnection.DB_Connect;

public class GetToDoList {
	
	public static List<ToDo> getToDoList(String email){
		
		PreparedStatement ps =null;
		String query="select * from todolist where user_email=?";
		ArrayList<ToDo> tdlist=new ArrayList<>();
		ToDo td=null;
		Connection con= DB_Connect.getConnect();
		
		try {
			
			
			ps=con.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					
					td=new ToDo();
					td.setTaskid(rs.getInt(1));
					td.setTaskname(rs.getString(2));
					td.setDescription(rs.getString(3));
					td.setStatus(rs.getString(4));
					
					tdlist.add(td);
					
				}
			}
			else {
				return null;
			}
			
		}catch(Exception e) {
			
		}
		
		return tdlist;
		
	}

}
