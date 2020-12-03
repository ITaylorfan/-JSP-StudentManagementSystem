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

import com.administrator.bean.AllStudentInfo;
import com.administrator.bean.ClassInfo;
import com.administrator.page.PagingFunction;
import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorClassInfoGet
 */
@WebServlet("/administratorClassInfoGet")
public class administratorClassInfoGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorClassInfoGet() {
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
                    //字符串分割
                    data=URLDecoder.decode(remember.getValue(),"utf-8").split(",");  /*使用utf-8读取*/
                   	//System.out.println(data[2]);
                }
            }
        }
        if(data!=null){
        	String sql="SELECT * FROM class_info;";
    		ResultSet rSet = jdbc.query(sql);
    		List<Object> list=new ArrayList<Object>();
    		//System.out.println(sql);
    		try {
    			while(rSet.next()){
    				//封装到javaBean中
    				ClassInfo classInfo=new ClassInfo(rSet.getLong(6), 
    						rSet.getString(1),rSet.getString(2),rSet.getInt(3),
    						rSet.getDate(4),rSet.getString(5),rSet.getString(7));
    				//把对象加入容器
    				list.add(classInfo);
    			}
    			//把容器加入session
				HttpSession session=request.getSession();
				session.setAttribute("AllClassInfo",list);
				session.setAttribute("searchContent", "");
				//System.out.println(myInfo.getBirthday());
				
				//总页数
				//页数有关的数组
				int []page_num=null;
				//注意此处list必须为所有数据的list
				page_num=PagingFunction.getPageNum(list);
				//System.out.println(page_num[1]);
				session.setAttribute("ClassInfoPageNum", page_num);
				session.setAttribute("ClassInfoCurrentPage",page_num[0]-1);  //当前页数 默认为第一页
				
				list=PagingFunction.getPageList(list,"0",10);//调用分页函数
				session.setAttribute("ClassInfo",list);  //在页面中显示的容器
				
				
				
				response.sendRedirect("administrator/ClassInfo.jsp");
    			
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
