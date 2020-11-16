package com.front.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.front.Bean.MyClass;
import com.front.Bean.MyInfo;
import com.jdbc.jdbc;

/**
 * Servlet implementation class MyClassServlet
 */
@WebServlet("/MyClassServlet")
public class MyClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//从cookie中取值
        Cookie[] cookies = request.getCookies();
        Cookie remember = null;
        String[] data=null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("remember")) {
                    remember = c;
                    //字符串分割
                    data=URLDecoder.decode(remember.getValue(),"utf-8").split(",");  /*使用utf-8读取*/
                   	//out.print(data[2]);
                }
            }
        }
        if(data!=null){
        	String sql="SELECT * FROM class_info WHERE class_id="
        			+ "(SELECT classId FROM student_info WHERE sno="+data[3]+")";
    		ResultSet rSet = jdbc.query(sql);
    		//System.out.println(sql);
    		try {
    			while(rSet.next()){
    				//封装到javaBean中
    				MyClass myClass=new MyClass(rSet.getLong(6), 
    						rSet.getString(1), rSet.getString(2), rSet.getInt(3),rSet.getDate(4), rSet.getString(5));
    				
    				//把对象加入session
    				HttpSession session=request.getSession();
    				session.setAttribute("MyClassInfo",myClass);
    				//System.out.println(myClass.getMajor());
    				response.sendRedirect("control/MyClass.jsp");
    			}
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }else{
        	response.sendRedirect("Login.jsp");
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
