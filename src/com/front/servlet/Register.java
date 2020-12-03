package com.front.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		try{
			String account=request.getParameter("account");
			String passWord=request.getParameter("password");
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/jspfinalproject","root","123456");
			
			String sql="insert into student_login(account,password) value(?,?)";
			PreparedStatement pStatement=connection.prepareStatement(sql);
			pStatement.setString(1,account);
			pStatement.setString(2, passWord);
			int n=pStatement.executeUpdate();
			
			if (n>0) {
				HttpSession session=request.getSession();
				session.setAttribute("welcome",account);
				response.sendRedirect("RegisterSuccess.jsp");
			}else {
				response.sendRedirect("RegisterError.jsp");
			}
			
			//System.out.println(account+passWord);
		}catch (Exception e) {
			// TODO: handle exception
			//response.sendRedirect("RegisterError.jsp");
			
		}
		
	}

}
