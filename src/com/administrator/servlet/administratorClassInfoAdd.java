package com.administrator.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorClassInfoAdd
 */
@WebServlet("/administratorClassInfoAdd")
public class administratorClassInfoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorClassInfoAdd() {
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
		String class_id=request.getParameter("class_id");
		String class_name=request.getParameter("class_name");
		String major=request.getParameter("major");
		String join_year=request.getParameter("join_year");
		String counselor=request.getParameter("counselor");
		String headmaster=request.getParameter("headmaster");
	
		
		//System.out.println(classId);
		
		String sql="insert into class_info (class_name,major,join_year,counselor,class_id,headmaster) values('"+class_name+"','"+major+"','"+join_year+"','"+counselor+"','"+class_id+"',"
				+ "'"+headmaster+"');";
		jdbc.update(sql);
		//System.out.println(sql);
		HttpSession session=request.getSession();
		session.setAttribute("success","Ìí¼Ó³É¹¦£¡");
		response.sendRedirect("./administratorClassInfoGet");
	}

}
