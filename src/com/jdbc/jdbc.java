package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 * 这是jdbc封装好的文件
 */
public class jdbc {
	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jspfinalproject","root","123456");
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//增删改 
	public static void update(String sql){
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//查询
	public static ResultSet query(String sql){
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		return rs;
	}
	
}