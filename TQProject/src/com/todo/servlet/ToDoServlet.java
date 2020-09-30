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

import com.allobjects.ToDo;

import com.getdata.cls.GetToDoList;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ToDoServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/json");
		PrintWriter out=res.getWriter();
		
		ObjectMapper om=new ObjectMapper();
		
		String email=req.getParameter("email");
		
		List<ToDo> tdlist=GetToDoList.getToDoList(email);
		if(tdlist!=null) {
		
		String jsonToDoList = om.writeValueAsString(tdlist);
		res.addHeader("Access-Control-Allow-Origin", "*");
		out.write(jsonToDoList);
		}
		else
		{
			String jsonToDoList="No List";
			res.addHeader("Access-Control-Allow-Origin", "*");
			out.write(jsonToDoList);
		}
	}



}
