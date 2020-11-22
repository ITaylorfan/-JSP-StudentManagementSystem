package com.administrator.bean;

public class RegisterStudentInfo {
	private String username;
	private Long sno;
	private String name;
	private String sex;
	public RegisterStudentInfo(String username, Long sno, String name, String sex) {
		super();
		this.username = username;
		this.sno = sno;
		this.name = name;
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public Long getSno() {
		return sno;
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	
	
}
