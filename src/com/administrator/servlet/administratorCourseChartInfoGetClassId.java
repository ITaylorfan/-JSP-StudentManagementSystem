package com.administrator.servlet;

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

import com.administrator.bean.ClassInfo;

import com.alibaba.fastjson.JSON;
import com.jdbc.jdbc;

/**
 * Servlet implementation class administratorCourseChartInfoGetClassId
 */
@WebServlet("/administratorCourseChartInfoGetClassId")
public class administratorCourseChartInfoGetClassId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public administratorCourseChartInfoGetClassId() {
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
        	String sql="SELECT * FROM class_info;";
    		ResultSet rSet = jdbc.query(sql);
    		List<ClassInfo> list=new ArrayList<ClassInfo>();
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
				//HttpSession session=request.getSession();
				//session.setAttribute("ClassInfo",list);
				//session.setAttribute("searchContent", "");
				//System.out.println(myInfo.getBirthday());
				//response.sendRedirect("administrator/ClassInfo.jsp");
    						
    			//将list容器中的内容转换成json数据
    			request.setCharacterEncoding("utf-8");  // 设置request字符编码
    	        String searchText = request.getParameter("search"); // 获取传入的search字段的内容
    	        response.setContentType("text/json; charset=utf-8");    // 设置response的编码及格式
    	        PrintWriter out = response.getWriter();
    	        String resJSON = JSON.toJSONString(list);     // 转换为json
    	       
    	        out.print(resJSON); // 返回数据
    	        
    	        //System.out.println(resJSON);
    			//System.out.println(searchText);
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
