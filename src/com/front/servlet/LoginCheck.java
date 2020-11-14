package com.front.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.jdbc.jdbc;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		// response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			String account = request.getParameter("account");
			String passWord = request.getParameter("password");
			String isChecked = request.getParameter("isChecked");
			System.out.println("是否选中" + isChecked);

			String sql = "select * from user_login where username='" + account + "' and password='" + passWord + "'";

			ResultSet rSet = jdbc.query(sql);

			if (!rSet.next()) {
				//账号或密码错误！
				HttpSession session=request.getSession();
				String error="账号或密码错误！";
				session.setAttribute("error",error);
				response.sendRedirect("Login.jsp");
			} else {
				// 新建cookie
				//将用户名和密码加入cookie
				String data=account+","+passWord;
				Cookie cookie = new Cookie("remember",data);
				
				// 2 判断remeber 是否被选中
				if (isChecked != null && isChecked.equals("on")) {
					// "yes" 勾选了 ==> 设置有效时间为 一周
					cookie.setMaxAge(60 * 60 * 24 * 7);
				} else {
					// null 没勾选 ==> 设置cookie的有效时间为0
					cookie.setMaxAge(0);
				}
				// 3 将cookie添加到response
				response.addCookie(cookie);
				response.sendRedirect("./control/home.jsp");
				
				HttpSession session=request.getSession();
				String error=null;
				session.setAttribute("error",error);
			}

			System.out.println(account + passWord);
		} catch (Exception e) {
			// TODO: handle exception

		}

		// response.sendRedirect("index.jsp");
	}

}
