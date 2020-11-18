package com.front.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class exitLogin
 * 退出登录的servlet
 */
@WebServlet("/exitLogin")
public class exitLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exitLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//把cookie中的值删掉
		HttpSession session=request.getSession();
		   Cookie[] cookies = request.getCookies();
	        if (cookies != null && cookies.length > 0) {
	            for (Cookie c : cookies) {
	                if (c.getName().equals("remember")) {
	                    c.setMaxAge(0);
	                    response.addCookie(c);               
	                    session.setAttribute("success","注销成功！");
	                    response.sendRedirect("Login.jsp");
	                }
	            }
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
