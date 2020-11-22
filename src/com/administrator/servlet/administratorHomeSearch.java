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

import com.administrator.bean.RegisterStudentInfo;
import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorHomeSearch
 */
@WebServlet("/administratorHomeSearch")
public class administratorHomeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorHomeSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		String q=request.getParameter("query");
		//解决url地址栏参数中文乱码
		String query = new String(q.getBytes("iso-8859-1"), "utf-8");
		//System.out.println(query);
		try{
			List<RegisterStudentInfo> list2=new ArrayList<RegisterStudentInfo>();
			HttpSession session=request.getSession();
			List<RegisterStudentInfo> list=(List<RegisterStudentInfo>)session.getAttribute("RegisterStudentInfo");
			for(int i=0;i<list.size();i++){
				if(list.get(i).getUsername().equals(query)||list.get(i).getSno().toString().equals(query)||list.get(i).getName().equals(query)||list.get(i).getSex().equals(query)){
					list2.add(list.get(i));
				}				
			}
			session.setAttribute("RegisterStudentInfo",list2);
			response.sendRedirect("./administrator/Home.jsp");
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
