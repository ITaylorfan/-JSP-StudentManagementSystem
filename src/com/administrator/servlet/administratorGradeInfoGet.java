package com.administrator.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.administrator.bean.GradeInfo;
import com.administrator.page.PagingFunction;
import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorGradeInfoGet
 */
@WebServlet("/administratorGradeInfoGet")
public class administratorGradeInfoGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorGradeInfoGet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Cookie[] cookies = request.getCookies();
        Cookie remember = null;
        String[] data=null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("rememberAdministrator")) {
                    remember = c;
                    //�ַ����ָ�
                    data=URLDecoder.decode(remember.getValue(),"utf-8").split(",");  /*ʹ��utf-8��ȡ*/
                   	//System.out.println(data[2]);
                }
            }
        }
        if(data!=null){
        	String sql="SELECT a.*,b.name,b.class,c.cname FROM grade_info a,student_info b,course_info c WHERE a.sno=b.sno AND a.cno=c.cno;";
    		ResultSet rSet = jdbc.query(sql);
    		List<Object> list=new ArrayList<Object>();
    		//System.out.println(sql);
    		try {
    			while(rSet.next()){
    				//��װ��javaBean��
    				GradeInfo gradeInfo=new GradeInfo(rSet.getLong(1),rSet.getString(4),rSet.getString(5),rSet.getInt(2),rSet.getString(6),rSet.getInt(3));
    				//�Ѷ����������
    				list.add(gradeInfo);
    			}
    			//����������session
				HttpSession session=request.getSession();
				session.setAttribute("AllGradeInfo",list);
				session.setAttribute("searchContent", "");
				//System.out.println(myInfo.getBirthday());
				
				//��ҳ��
				//ҳ���йص�����
				int []page_num=null;
				//ע��˴�list����Ϊ�������ݵ�list
				page_num=PagingFunction.getPageNum(list);
				//System.out.println(page_num[1]);
				session.setAttribute("GradeInfoPageNum", page_num);
				session.setAttribute("GradeInfoCurrentPage",page_num[0]-1);  //��ǰҳ�� Ĭ��Ϊ��һҳ
				
				list=PagingFunction.getPageList(list,"0",10);//���÷�ҳ����
				session.setAttribute("GradeInfo",list);  //��ҳ������ʾ������
				
				
				response.sendRedirect("administrator/GradeInfo.jsp");
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else{
        	response.sendRedirect("./administrator/Login.jsp");
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
