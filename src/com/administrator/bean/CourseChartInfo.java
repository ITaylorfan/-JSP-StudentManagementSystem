package com.administrator.bean;

public class CourseChartInfo {
	private int plan_id;
	private int cno;
	private Long class_id;
	private String teacher;
	private String date;
	private String address;
	private String cname;
	public CourseChartInfo(int plan_id, int cno, Long class_id, String teacher, String date, String address,
			String cname) {
		super();
		this.plan_id = plan_id;
		this.cno = cno;
		this.class_id = class_id;
		this.teacher = teacher;
		this.date = date;
		this.address = address;
		this.cname = cname;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public int getCno() {
		return cno;
	}
	public Long getClass_id() {
		return class_id;
	}
	public String getTeacher() {
		return teacher;
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
