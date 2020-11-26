package com.administrator.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.administrator.bean.CourseInfo;

/**
 * Servlet implementation class administratorCourseInfoSearch
 */
@WebServlet("/administratorCourseInfoSearch")
public class administratorCourseInfoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseInfoSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		String q=request.getParameter("query");
		//解决url地址栏参数中文乱码
		String query = new String(q.getBytes("iso-8859-1"), "utf-8");
		
		//System.out.println(query);
		try{
			List<CourseInfo> list2=new ArrayList<CourseInfo>();
			HttpSession session=request.getSession();
			List<CourseInfo> list=(List<CourseInfo>)session.getAttribute("CourseInfo");
			//System.out.println(query);
			for(int i=0;i<list.size();i++){
				if(Integer.toString(list.get(i).getCno()).equals(query)||list.get(i).getCname().equals(query)
						||list.get(i).getStudy_time().equals(query)||Integer.toString(list.get(i).getCredit()).equals(query)){
					list2.add(list.get(i));
				}				
			}
			session.setAttribute("CourseInfo",list2);
			session.setAttribute("searchContent",query);
			response.sendRedirect("administrator/CourseInfo.jsp");
			//System.out.println(sno);
		}catch(Exception e){
			e.printStackTrace();
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
