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

import com.administrator.bean.ClassInfo;
import com.administrator.page.PagingFunction;

/**
 * Servlet implementation class administratorClassInfoSearch
 */
@WebServlet("/administratorClassInfoSearch")
public class administratorClassInfoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorClassInfoSearch() {
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
			List<ClassInfo> list=(List<ClassInfo>)session.getAttribute("ClassInfo");
			//System.out.println(query);
			for(int i=0;i<list.size();i++){
				if(list.get(i).getClass_id().toString().equals(query)||list.get(i).getClass_name().equals(query)
						||list.get(i).getMajor().equals(query)||Integer.toString(list.get(i).getTotal_person()).toString().equals(query)||list.get(i).getJoin_year().toString().equals(query)
						||list.get(i).getCounselor().equals(query)
						||list.get(i).getHeadmaster().equals(query)){
					list2.add(list.get(i));
				}				
			}
			//总页数
			//页数有关的数组
			int []page_num=null;
			//注意此处list必须为所有数据的list
			page_num=PagingFunction.getPageNum(list2);
			
			session.setAttribute("ClassInfoPageNum", page_num);
			session.setAttribute("ClassInfoCurrentPage",page_num[0]-1);  //当前页数 默认为第一页
			
			session.setAttribute("AllClassInfo",list2); //保留搜索到的所有数据   将之前存在的所有数据覆盖掉  刷新时会重新获得所有数据
		
			list2=PagingFunction.getPageList(list2,"0",10);//调用分页函数
			session.setAttribute("ClassInfo",list2);  //设置要显示的list
			
			response.sendRedirect("administrator/ClassInfo.jsp");
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
