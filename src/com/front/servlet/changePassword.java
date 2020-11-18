package com.front.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.jdbc;

/**
 * Servlet implementation class changePassword
 * 改密码的servlet
 */
@WebServlet("/changePassword")
public class changePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		request.setCharacterEncoding("utf-8");
		
		String passWord = request.getParameter("password");
		
		
		
		Cookie[] cookies = request.getCookies();
		Cookie remember = null;
		String[] data = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("remember")) {
					remember = c;
					//字符串分割
					data = URLDecoder.decode(remember.getValue(), "utf-8").split(","); /*使用utf-8读取*/
					
					//删除cookie
					c.setMaxAge(0);
	                response.addCookie(c);
				}
			}
		}
		
		String sql = "UPDATE user_login SET PASSWORD='"+passWord+"' WHERE username='"+data[0]+"'";
		jdbc.update(sql);
		
		HttpSession session=request.getSession();
		session.setAttribute("success","更改密码成功！请重新登录");
		response.sendRedirect("Login.jsp");
		
	}

}
