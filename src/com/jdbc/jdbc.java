package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
 * ����jdbc��װ�õ��ļ�
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
	
	//��ɾ�� 
	public static void update(String sql){
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	//��ѯ
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