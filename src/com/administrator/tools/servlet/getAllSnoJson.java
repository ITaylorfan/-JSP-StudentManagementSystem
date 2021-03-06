package com.administrator.tools.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.alibaba.fastjson.JSON;
import com.jdbc.jdbc;

/**
 * Servlet implementation class getAllSnoJson
 */
@WebServlet("/getAllSnoJson")
public class getAllSnoJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllSnoJson() {
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
        	String sql="SELECT * FROM student_info;";
    		ResultSet rSet = jdbc.query(sql);
    		List<AllStudentInfo> list=new ArrayList<AllStudentInfo>();
    		//System.out.println(sql);
    		try {
    			while(rSet.next()){
    				//封装到javaBean中
    				AllStudentInfo allStudentInfo=new AllStudentInfo(rSet.getLong(1), 
    						rSet.getString(2),rSet.getString(3),rSet.getDate(4),
    						rSet.getString(5),rSet.getString(6),rSet.getString(7),rSet.getLong(8),rSet.getString(9));
    				//把对象加入容器
    				list.add(allStudentInfo);
    			}
    			//把容器转换为Json数据
    			request.setCharacterEncoding("utf-8");  // 设置request字符编码
    	        
    	        response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
    	        PrintWriter out = response.getWriter();
    	        String resJSON = JSON.toJSONString(list);     // 转换为json
    	       
    	        out.print(resJSON); // 返回数据
    			
    			
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
