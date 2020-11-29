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
 * Servlet implementation class administratorGradeInfoEdit
 */
@WebServlet("/administratorGradeInfoEdit")
public class administratorGradeInfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorGradeInfoEdit() {
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
		
		String sno=request.getParameter("sno");
		String cno=request.getParameter("cno");
		String grade=request.getParameter("grade");
		String oldCno=request.getParameter("oldCno");
		
		//System.out.println(classId);
		
		String sql="update grade_info set cno='"+cno+"',grade='"+grade+"' where sno='"+sno+"' and cno='"+oldCno+"';";
		jdbc.update(sql);
		//System.out.println(sql);
		HttpSession session=request.getSession();
		session.setAttribute("success","±£´æ³É¹¦£¡");
		response.sendRedirect("./administratorGradeInfoGet");
	}

}
