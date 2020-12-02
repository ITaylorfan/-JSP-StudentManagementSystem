package com.administrator.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class administratorExitLogin
 */
@WebServlet("/administratorExitLogin")
public class administratorExitLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorExitLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session=request.getSession();
		   Cookie[] cookies = request.getCookies();
	        if (cookies != null && cookies.length > 0) {
	            for (Cookie c : cookies) {
	                if (c.getName().equals("rememberAdministrator")) {
	                    c.setMaxAge(0);
	                    response.addCookie(c);               
	                    session.setAttribute("success","注销成功！");
	                    
	                    //删除所有session
	                    session.invalidate();
	                    response.sendRedirect("./administrator/Login.jsp");
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
