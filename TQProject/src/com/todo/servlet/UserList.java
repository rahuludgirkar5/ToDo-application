package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.allobjects.User;
import com.getdata.cls.GetUserList;


@WebServlet("/UserList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public UserList() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/json");
		PrintWriter out=res.getWriter();
		
		ObjectMapper om=new ObjectMapper();
		
			List<User> ulist=GetUserList.getList();
			
			String userlist=om.writeValueAsString(ulist);
			res.addHeader("Access-Control-Allow-Origin", "*");
			out.write(userlist);
			
	}

}
