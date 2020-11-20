package com.front.servlet;

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

import com.alibaba.fastjson.JSON;
import com.front.Bean.MyChart;
import com.jdbc.jdbc;

/**
 * Servlet implementation class MyCourseChart
 */
@WebServlet("/MyCourseChart")
public class MyCourseChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCourseChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		// ��cookie��ȡֵ
		Cookie[] cookies = request.getCookies();
		Cookie remember = null;
		String[] data = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie c : cookies) {
				if (c.getName().equals("remember")) {
					remember = c;
					// �ַ����ָ�
					data = URLDecoder.decode(remember.getValue(), "utf-8")
							.split(","); /* ʹ��utf-8��ȡ */
					// out.print(data[2]);
				}
			}
		}
		// System.out.println(data[3].substring(0,data[3].length()-2));
		
		

		if (data != null) {
			// ��ȡ���
			String class_id= data[3].substring(0,data[3].length()-2);
			
			String sql = "SELECT a.date,a.address,b.cname "
					+ "FROM course_chart a,course_info b "
					+ "WHERE a.cno=b.cno AND a.class_id="+class_id+";";
			//System.out.println(sql);
			ResultSet rSet = jdbc.query(sql);
					
			List<MyChart> list=new ArrayList<MyChart>();
			try {
				while (rSet.next()) {
					// ��װ��javaBean��
					MyChart myChart = new MyChart(rSet.getString(1),rSet.getString(2),rSet.getString(3));
					// �Ѷ����������
					list.add(myChart);
				}
				// ����������session
				HttpSession session = request.getSession();
				session.setAttribute("MyChart", list);
				
				
				//��list�����е�����ת����json����
				request.setCharacterEncoding("utf-8");  // ����request�ַ�����
		        String searchText = request.getParameter("search"); // ��ȡ�����search�ֶε�����
		        response.setContentType("text/json; charset=utf-8");    // ����response�ı��뼰��ʽ
		        PrintWriter out = response.getWriter();
		        String resJSON = JSON.toJSONString(list);     // ת��Ϊjson
		        //System.out.println(resJSON);
		        out.print(resJSON); // ��������
		        
		        //response.sendRedirect("control/MyCourseChart.jsp");
				
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
