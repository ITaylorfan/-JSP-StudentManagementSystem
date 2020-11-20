package com.front.Bean;

public class MyGrade {
	private int cno;
	private String cname;
	private int grade;
	private int credit;
	private String teacher;
	public MyGrade(int cno, String cname, int grade, int credit, String teacher) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.grade = grade;
		this.credit = credit;
		this.teacher = teacher;
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
	public int getCredit() {
		return credit;
	}
	public String getTeacher() {
		return teacher;
	}
	
}
