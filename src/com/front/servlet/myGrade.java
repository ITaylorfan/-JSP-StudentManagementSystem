package com.front.servlet;

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


import com.front.Bean.MyGrade;
import com.jdbc.jdbc;

/**
 * Servlet implementation class myGrade
 */
@WebServlet("/myGrade")
public class myGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myGrade() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 从cookie中取值
		Cookie[] cookies = request.getCookies();
		Cookie remember = null;
		String[] data = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("remember")) {
					remember = c;
					// 字符串分割
					data = URLDecoder.decode(remember.getValue(), "utf-8")
							.split(","); /* 使用utf-8读取 */
					// out.print(data[2]);
				}
			}
		}
		// System.out.println(data[3].substring(0,data[3].length()-2));
		
		

		if (data != null) {
			// 获取学号
			String sno= data[3];
			
			String sql = "SELECT DISTINCT a.cno,b.cname,a.grade,b.credit,c.teacher "
					+ "FROM grade_info a,course_info b,course_chart c "
					+ "WHERE a.cno=b.cno AND a.cno=c.cno AND a.sno="+sno+";";
			//System.out.println(sql);
			ResultSet rSet = jdbc.query(sql);
					
			List<MyGrade> list=new ArrayList<MyGrade>();
			try {
				while (rSet.next()) {
					// 封装到javaBean中
					MyGrade myGrade = new MyGrade(rSet.getInt(1), rSet.getString(2), rSet.getInt(3),
							rSet.getInt(4), rSet.getString(5));
					// 把对象加入容器
					list.add(myGrade);
				}
				// 把容器加入session
				HttpSession session = request.getSession();
				session.setAttribute("MyGrade", list);
				response.sendRedirect("control/MyGrade.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
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
