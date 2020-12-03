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


import com.administrator.bean.CourseChartInfo;
import com.administrator.page.PagingFunction;

/**
 * Servlet implementation class administratorCourseChartInfoSearch
 */
@WebServlet("/administratorCourseChartInfoSearch")
public class administratorCourseChartInfoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseChartInfoSearch() {
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
			List<Object> list2=new ArrayList<Object>();
			HttpSession session=request.getSession();
			List<CourseChartInfo> list=(List<CourseChartInfo>)session.getAttribute("AllCourseChartInfo");
			//System.out.println(query);
			for(int i=0;i<list.size();i++){
				if(Integer.toString(list.get(i).getPlan_id()).equals(query)||Integer.toString(list.get(i).getCno()).equals(query)
						||list.get(i).getClass_id().toString().equals(query)||list.get(i).getCname().equals(query)||list.get(i).getTeacher().equals(query)
						||list.get(i).getDate().equals(query)
						||list.get(i).getAddress().equals(query)){
					list2.add(list.get(i));
				}				
			}
			
			//总页数
			//页数有关的数组
			int []page_num=null;
			//注意此处list必须为所有数据的list
			page_num=PagingFunction.getPageNum(list2);
			
			session.setAttribute("CourseChartInfoPageNum", page_num);
			session.setAttribute("CourseChartInfoCurrentPage",page_num[0]-1);  //当前页数 默认为第一页
			
			session.setAttribute("AllCourseChartInfo",list2); //保留搜索到的所有数据   将之前存在的所有数据覆盖掉  刷新时会重新获得所有数据
		
			list2=PagingFunction.getPageList(list2,"0",10);//调用分页函数
			session.setAttribute("CourseChartInfo",list2);  //设置要显示的list
			
			
			session.setAttribute("searchContent",query);
			response.sendRedirect("administrator/CourseChartInfo.jsp");
			
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
