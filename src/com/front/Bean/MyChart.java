package com.front.Bean;

public class MyChart {
	private String date;  //�ſ�id
	private String address; //��ַ
	private String cname;   //�γ���
	public MyChart(String date, String address, String cname) {
		super();
		this.date = date;
		this.address = address;
		this.cname = cname;
	}
	public String getDate() {
		return date;
	}
	public String getAddress() {
		return address;
	}
	public String getCname() {
		return cname;
	}
	
}
