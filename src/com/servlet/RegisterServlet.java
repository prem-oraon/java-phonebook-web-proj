package com.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.conn.DbConnect;
import com.entity.User;

import com.dao.UserDAO;
import com.entity.User;
@SuppressWarnings("unused")
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User u=new User(name,email,password);
		UserDAO dao=new UserDAO(DbConnect.getConn());
		boolean f=dao.userRegister(u);
		
		HttpSession session=request.getSession();
		if(f)
		{
			session.setAttribute("sucssMsg", "user registration successfully");
			response.sendRedirect("register.jsp");
			System.out.print("user registration successfully");
		}
		else {
			session.setAttribute("errorMsg", "something went wrong");
			response.sendRedirect("register.jsp");
			System.out.print("something went wrong");
		}
	}

}
