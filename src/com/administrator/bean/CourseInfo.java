package com.administrator.bean;

public class CourseInfo {
	private int cno;
	private String cname;
	private String study_time;
	private int credit;
	public CourseInfo(int cno, String cname, String study_time, int credit) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.study_time = study_time;
		this.credit = credit;
	}
	public int getCno() {
		return cno;
	}
	public String getCname() {
		return cname;
	}
	public String getStudy_time() {
		return study_time;
	}
	public int getCredit() {
		return credit;
	}
	
}
