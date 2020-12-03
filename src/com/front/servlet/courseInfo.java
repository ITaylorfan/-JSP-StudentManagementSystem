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

import com.front.Bean.MyCourseInfo;
import com.jdbc.jdbc;

/**
 * Servlet implementation class courseInfo
 */
@WebServlet("/courseInfo")
public class courseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public courseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			// 获取班号字符串截取
			String classId = data[3].substring(0, data[3].length() - 2);
			
			String sql = "SELECT DISTINCT a.*,b.teacher FROM course_info  a,course_chart b " + "WHERE class_id="
					+ classId + " AND a.cno=b.cno;";
			//System.out.println(sql);
			ResultSet rSet = jdbc.query(sql);
					
			List<MyCourseInfo> list=new ArrayList<MyCourseInfo>();
			try {
				while (rSet.next()) {
					// 封装到javaBean中
					MyCourseInfo myCourseInfo = new MyCourseInfo(rSet.getInt(1), rSet.getString(2), rSet.getString(3),
							rSet.getInt(4), rSet.getString(5));
					// 把对象加入容器
					list.add(myCourseInfo);
				}
				// 把容器加入session
				HttpSession session = request.getSession();
				session.setAttribute("MyCourseInfo", list);
				response.sendRedirect("control/CourseInfo.jsp");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("Login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
