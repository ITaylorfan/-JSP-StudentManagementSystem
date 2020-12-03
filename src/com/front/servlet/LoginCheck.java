package com.front.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.jdbc.jdbc;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCheck() {
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
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		// response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		try {
			request.setCharacterEncoding("utf-8");
			String account = request.getParameter("account");
			String passWord = request.getParameter("password");
			String isChecked = request.getParameter("isChecked");
			String checkCode=request.getParameter("checkCode");  //��ȡ������֤��
			//System.out.println("�Ƿ�ѡ��" + isChecked);

			String sql = "select * from user_login where username='" + account + "' and password='" + passWord + "'";

			ResultSet rSet = jdbc.query(sql);
			HttpSession session = request.getSession();
			if(!checkCode.equals(session.getAttribute("rCode"))){
				// ��֤�����
				String error = "��֤�����";
				session.setAttribute("error", error);
				response.sendRedirect("Login.jsp");
			}
			if (!rSet.next()) {
				// �˺Ż��������
				//HttpSession session = request.getSession();
				String error = "�˺Ż��������";
				session.setAttribute("error", error);
				response.sendRedirect("Login.jsp");
			} else {
				// rSet.previous(); //��ǰ��
					
				long idNumber = 0;
				String userType = "ѧ��"; // �û����
				rSet.previous();    //��Ҫǰ��һλ
				//System.out.println(rSet.next());
				while(rSet.next()){
					idNumber = rSet.getLong(4); 
				    userType = rSet.getString(3);
				}
			    	 
				//System.out.println(idNumber + userType);
				// �½�cookie
				// ���û�����������û������û���Ϣʶ��id����cookie
				String data = account + "," + passWord + "," + userType + "," + idNumber;
				Cookie cookie = new Cookie("remember",URLEncoder.encode(data,"utf-8"));  //ʹ��utf-8���cookie���ܱ������ĵ�����

				// 2 �ж�remeber �Ƿ�ѡ��
				if (isChecked != null && isChecked.equals("on")) {
					// "yes" ��ѡ�� ==> ������Чʱ��Ϊ һ��
					cookie.setMaxAge(60 * 60 * 24 * 7);
				} else {
					// null û��ѡ ==> ����cookie����Чʱ��Ϊ0 ��ʾ������cookie
					//����Ϊ-1 ������ʾ��ʱcookie
					cookie.setMaxAge(-1);
				}
				// 3 ��cookie��ӵ�response
				response.addCookie(cookie);
				response.sendRedirect("HomeServlet");

				//HttpSession session = request.getSession();
				String error = null;
				session.setAttribute("error", error);

			}

			//System.out.println(account + passWord);
		} catch (Exception e) {
			// TODO: handle exception

		}

		// response.sendRedirect("index.jsp");
	}

}
