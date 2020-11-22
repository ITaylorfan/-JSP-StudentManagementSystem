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

import com.administrator.bean.RegisterStudentInfo;
import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorHome
 */
@WebServlet("/administratorHome")
public class administratorHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
	        	String sql="SELECT a.*,b.name,b.sex FROM user_login a,student_info b WHERE TYPE='学生' AND a.user_info_id=b.sno order by b.sno;";
	    		ResultSet rSet = jdbc.query(sql);
	    		List<RegisterStudentInfo> list=new ArrayList<RegisterStudentInfo>();
	    		//System.out.println(sql);
	    		try {
	    			while(rSet.next()){
	    				//封装到javaBean中
	    				RegisterStudentInfo registerStudentInfo=new RegisterStudentInfo(rSet.getString(1), 
	    						rSet.getLong(4),rSet.getString(5),rSet.getString(6));
	    				//把对象加入容器
	    				list.add(registerStudentInfo);
	    			}
	    			//把容器加入session
    				HttpSession session=request.getSession();
    				session.setAttribute("RegisterStudentInfo",list);
    				//System.out.println(myInfo.getBirthday());
    				response.sendRedirect("administrator/Home.jsp");
	    			
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
