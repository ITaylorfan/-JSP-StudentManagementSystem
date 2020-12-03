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
		//���url��ַ��������������
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
			//��ҳ��
			//ҳ���йص�����
			int []page_num=null;
			//ע��˴�list����Ϊ�������ݵ�list
			page_num=PagingFunction.getPageNum(list2);
			
			session.setAttribute("ClassInfoPageNum", page_num);
			session.setAttribute("ClassInfoCurrentPage",page_num[0]-1);  //��ǰҳ�� Ĭ��Ϊ��һҳ
			
			session.setAttribute("AllClassInfo",list2); //��������������������   ��֮ǰ���ڵ��������ݸ��ǵ�  ˢ��ʱ�����»����������
		
			list2=PagingFunction.getPageList(list2,"0",10);//���÷�ҳ����
			session.setAttribute("ClassInfo",list2);  //����Ҫ��ʾ��list
			
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
