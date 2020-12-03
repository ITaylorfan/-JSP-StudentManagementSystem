package com.administrator.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class administratorGradeInfoPaging
 */
@WebServlet("/administratorGradeInfoPaging")
public class administratorGradeInfoPaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorGradeInfoPaging() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession session = request.getSession();
		// 跳转的页数
		String page = request.getParameter("page");
		List<Object> list=new ArrayList<>();
		list = (List<Object>) session.getAttribute("AllGradeInfo");
		//获取页数
		int [] page_num=(int[])session.getAttribute("GradeInfoPageNum");
		//获取挡圈页码
		int currentPage=(int) session.getAttribute("GradeInfoCurrentPage");
		//PagingFunction pagingFunction=new PagingFunction();
		
		if(page.equals("prev")){
			if (currentPage-1<0) {
				currentPage=0;
				page=currentPage+"";
			}else{
				page=currentPage-1+"";
			}
			
		}else if(page.equals("next")){
			if(currentPage+1>page_num[page_num.length-1]-1){
				currentPage=page_num[page_num.length-1]-1;
				page=currentPage+"";
			}else{
				page=currentPage+1+"";
			}
			
		}
		//System.out.println(page_num[page_num.length-1]-1);
		list=PagingFunction.getPageList(list,page,10);//调用分页函数
		//获取页面数组
		
		//System.out.println("页码"+page);
		//System.out.println();
		
		currentPage=Integer.parseInt(page);
		session.setAttribute("GradeInfoCurrentPage",currentPage);
		
		session.setAttribute("GradeInfo", list);
		//request.setAttribute("paging", paging);
		response.sendRedirect("./administrator/GradeInfo.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
