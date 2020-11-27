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
 * Servlet implementation class administratorCourseChartInfoAdd
 */
@WebServlet("/administratorCourseChartInfoAdd")
public class administratorCourseChartInfoAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseChartInfoAdd() {
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
		
		String sql="insert into course_chart (plan_id,cno,class_id,teacher,date,address) values('"+plan_id+"','"+cno+"','"+class_id+"','"+teacher+"','"+date+"',"
				+ "'"+address+"');";
		jdbc.update(sql);
		System.out.println(sql);
		HttpSession session=request.getSession();
		session.setAttribute("success","Ìí¼Ó³É¹¦£¡");
		response.sendRedirect("./administratorCourseChartInfoGet");
	}

}
