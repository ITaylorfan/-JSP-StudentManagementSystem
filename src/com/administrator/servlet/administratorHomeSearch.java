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
import com.administrator.page.PagingFunction;
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
		//���url��ַ��������������
		String query = new String(q.getBytes("iso-8859-1"), "utf-8");
		//System.out.println(query);
		try{
			List<Object> list2=new ArrayList<Object>();
			HttpSession session=request.getSession();
			List<RegisterStudentInfo> list=(List<RegisterStudentInfo>)session.getAttribute("AllRegisterStudentInfo");
			for(int i=0;i<list.size();i++){
				if(list.get(i).getUsername().equals(query)||list.get(i).getSno().toString().equals(query)||list.get(i).getName().equals(query)||list.get(i).getSex().equals(query)){
					list2.add(list.get(i));
				}				
			}
			
			//��ҳ��
			//ҳ���йص�����
			int []page_num=null;
			//ע��˴�list����Ϊ�������ݵ�list
			page_num=PagingFunction.getPageNum(list2);
			
			session.setAttribute("HomePageNum", page_num);
			session.setAttribute("HomeCurrentPage",page_num[0]-1);  //��ǰҳ�� Ĭ��Ϊ��һҳ
			
			session.setAttribute("AllRegisterStudentInfo",list2); //��������������������   ��֮ǰ���ڵ��������ݸ��ǵ�  ˢ��ʱ�����»����������
		
			list2=PagingFunction.getPageList(list2,"0",10);//���÷�ҳ����
			session.setAttribute("RegisterStudentInfo",list2);  //����Ҫ��ʾ��list
			
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
