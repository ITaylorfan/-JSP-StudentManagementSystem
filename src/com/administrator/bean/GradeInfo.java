package com.administrator.bean;

public class GradeInfo {
	private Long sno;
	private String name;
	private String className;
	private int cno;
	private String cname;
	private int grade;    //³É¼¨
	public GradeInfo(Long sno, String name, String className, int cno, String cname, int grade) {
		super();
		this.sno = sno;
		this.name = name;
		this.className = className;
		this.cno = cno;
		this.cname = cname;
		this.grade = grade;
	}
	public Long getSno() {
		return sno;
	}
	public String getName() {
		return name;
	}
	public String getClassName() {
		return className;
	}
	public int getCno() {
		return cno;
	}
	public String getCname() {
		return cname;
	}
	public int getGrade() {
		return grade;
	}
	
	
}
