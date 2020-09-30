package com.todo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.mail.MailOTP;

@WebServlet("/OTPGeneration")
public class OTPGeneration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OTPGeneration() {
        super();
       
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/json");
		PrintWriter out=res.getWriter();
		
		ObjectMapper om=new ObjectMapper();
		
		String email=req.getParameter("email");
		
		String otp = MailOTP.sendMail(email);
		
		res.addHeader("Access-Control-Allow-Origin", "*");
		out.write(om.writeValueAsString(otp));
		
		
	}

}
