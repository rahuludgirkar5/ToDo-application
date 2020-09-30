package com.getdata.cls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.allobjects.User;
import com.dbconnection.DB_Connect;

public class GetUserList {
	
	public static List<User> getList(){
		
		List<User> ulist=new ArrayList<User>();

		String query="select * from user";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection con = DB_Connect.getConnect();
		
		try {
			ps=con.prepareStatement(query);
			rs= ps.executeQuery();
			
			if(rs!=null) {
				while(rs.next()) {
					User u=new User();
					u.setUserId(rs.getInt(1));
					u.setFirstname(rs.getString(2));
					u.setLastname(rs.getString(3));
					u.setGender(rs.getString(4));
					u.setDob(rs.getString(5));
					u.setEmail(rs.getString(6));
					u.setPassword(rs.getString(7));
					u.setStatus(rs.getString(9));
					ulist.add(u);
				}
				
			}
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ulist;
	}

}
