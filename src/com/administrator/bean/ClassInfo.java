package com.administrator.bean;

import java.util.Date;

public class ClassInfo {
	private Long class_id;
	private String class_name;
	private String major;
	private int total_person;
	private Date join_year;
	private String counselor;
	private String headmaster;
	public ClassInfo(Long class_id, String class_name, String major, int total_person, Date join_year, String counselor,
			String headmaster) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.major = major;
		this.total_person = total_person;
		this.join_year = join_year;
		this.counselor = counselor;
		this.headmaster = headmaster;
	}
	public Long getClass_id() {
		return class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public String getMajor() {
		return major;
	}
	public int getTotal_person() {
		return total_person;
	}
	public Date getJoin_year() {
		return join_year;
	}
	public String getCounselor() {
		return counselor;
	}
	public String getHeadmaster() {
		return headmaster;
	}
	
}
