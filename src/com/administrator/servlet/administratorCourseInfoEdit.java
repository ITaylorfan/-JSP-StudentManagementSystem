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
 * Servlet implementation class administratorCourseInfoEdit
 */
@WebServlet("/administratorCourseInfoEdit")
public class administratorCourseInfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseInfoEdit() {
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
		
		String cno=request.getParameter("cno");
		String cname=request.getParameter("cname");
		String study_time=request.getParameter("study_time");
		String credit=request.getParameter("credit");
		
		
		//System.out.println(classId);
		
		String sql="update course_info set cname='"+cname+"',study_time='"+study_time+"',credit='"+credit+"' where cno='"+cno+"';";
		jdbc.update(sql);
		//System.out.println(sql);
		HttpSession session=request.getSession();
		session.setAttribute("success","±£´æ³É¹¦£¡");
		response.sendRedirect("./administratorCourseInfoGet");
	}

}
