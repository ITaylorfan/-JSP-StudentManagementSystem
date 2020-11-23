package com.administrator.bean;

import java.util.Date;
public class AllStudentInfo {
	private Long sno;
	private String name;
	private String sex;
	private Date birthday;
	private String major;   //系
	private String className; //班级
	private String address;   //地址
	private Long phone;
	private String remark;   //备注
	public AllStudentInfo(Long sno, String name, String sex, Date birthday, String major, String className,
			String address, Long phone, String remark) {
		super();
		this.sno = sno;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.major = major;
		this.className = className;
		this.address = address;
		this.phone = phone;
		this.remark = remark;
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
	public Date getBirthday() {
		return birthday;
	}
	public String getMajor() {
		return major;
	}
	public String getClassName() {
		return className;
	}
	public String getAddress() {
		return address;
	}
	public Long getPhone() {
		return phone;
	}
	public String getRemark() {
		return remark;
	}
	
	
}
