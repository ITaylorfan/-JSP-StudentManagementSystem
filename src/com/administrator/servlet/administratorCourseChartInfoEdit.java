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
 * Servlet implementation class administratorCourseChartInfoEdit
 */
@WebServlet("/administratorCourseChartInfoEdit")
public class administratorCourseChartInfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseChartInfoEdit() {
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
		String plan_id=request.getParameter("plan_id");
		String cno=request.getParameter("cno");
		String class_id=request.getParameter("class_id");
		String teacher=request.getParameter("teacher");
		String date=request.getParameter("date");
		String address=request.getParameter("address");
	
		
		//System.out.println(classId);
		
		String sql="update course_chart set cno='"+cno+"',class_id='"+class_id+"',teacher='"
		+teacher+"',date='"+date+"',address='"+address+"' where plan_id='"+plan_id+"';";
		jdbc.update(sql);
		//System.out.println(sql);
		HttpSession session=request.getSession();
		session.setAttribute("success","±£´æ³É¹¦£¡");
		response.sendRedirect("./administratorCourseChartInfoGet");
	}

}
