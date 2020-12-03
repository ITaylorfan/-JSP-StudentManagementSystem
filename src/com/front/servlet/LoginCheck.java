package com.front.servlet;

import java.io.IOException;
import java.net.URLEncoder;
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

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
			request.setCharacterEncoding("utf-8");
			String account = request.getParameter("account");
			String passWord = request.getParameter("password");
			String isChecked = request.getParameter("isChecked");
			String checkCode=request.getParameter("checkCode");  //获取输入验证码
			//System.out.println("是否选中" + isChecked);

			String sql = "select * from user_login where username='" + account + "' and password='" + passWord + "'";

			ResultSet rSet = jdbc.query(sql);
			HttpSession session = request.getSession();
			if(!checkCode.equals(session.getAttribute("rCode"))){
				// 验证码错误！
				String error = "验证码错误！";
				session.setAttribute("error", error);
				response.sendRedirect("Login.jsp");
			}
			if (!rSet.next()) {
				// 账号或密码错误！
				//HttpSession session = request.getSession();
				String error = "账号或密码错误！";
				session.setAttribute("error", error);
				response.sendRedirect("Login.jsp");
			} else {
				// rSet.previous(); //向前移
					
				long idNumber = 0;
				String userType = "学生"; // 用户类别
				rSet.previous();    //需要前移一位
				//System.out.println(rSet.next());
				while(rSet.next()){
					idNumber = rSet.getLong(4); 
				    userType = rSet.getString(3);
				}
			    	 
				//System.out.println(idNumber + userType);
				// 新建cookie
				// 将用户名和密码和用户类别和用户信息识别id加入cookie
				String data = account + "," + passWord + "," + userType + "," + idNumber;
				Cookie cookie = new Cookie("remember",URLEncoder.encode(data,"utf-8"));  //使用utf-8解决cookie不能保存中文的问题

				// 2 判断remeber 是否被选中
				if (isChecked != null && isChecked.equals("on")) {
					// "yes" 勾选了 ==> 设置有效时间为 一周
					cookie.setMaxAge(60 * 60 * 24 * 7);
				} else {
					// null 没勾选 ==> 设置cookie的有效时间为0 表示不保存cookie
					//设置为-1 负数表示临时cookie
					cookie.setMaxAge(-1);
				}
				// 3 将cookie添加到response
				response.addCookie(cookie);
				response.sendRedirect("HomeServlet");

				//HttpSession session = request.getSession();
				String error = null;
				session.setAttribute("error", error);

			}

			//System.out.println(account + passWord);
		} catch (Exception e) {
			// TODO: handle exception

		}

		// response.sendRedirect("index.jsp");
	}

}
