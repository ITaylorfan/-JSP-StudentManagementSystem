package com.front.Bean;

import java.sql.Date;

/*
 * 我都班级javaBean
 * 
 * */
public class MyClass {
	private long classId;
	private String className;
	private String major;
	private int totalPerson;
	private Date joinYear;
	private String counselor;    //辅导员
	public MyClass(long classId, String className, String major, int totalPerson, Date joinYear, String counselor) {
		super();
		this.classId = classId;
		this.className = className;
		this.major = major;
		this.totalPerson = totalPerson;
		this.joinYear = joinYear;
		this.counselor = counselor;
	}
	public long getClassId() {
		return classId;
	}
	public String getClassName() {
		return className;
	}
	public String getMajor() {
		return major;
	}
	public int getTotalPerson() {
		return totalPerson;
	}
	public Date getJoinYear() {
		return joinYear;
	}
	public String getCounselor() {
		return counselor;
	}
	
	
}
