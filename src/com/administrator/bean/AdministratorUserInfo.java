package com.administrator.bean;

import java.util.Date;

public class AdministratorUserInfo {
	private int manageId;
	private String name;
	private String sex;
	private Date birthday;
	private Long phone;
	private String avatar;
	public AdministratorUserInfo(int manageId, String name, String sex, Date birthday, Long phone, String avatar) {
		super();
		this.manageId = manageId;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.avatar = avatar;
	}
	public int getManageId() {
		return manageId;
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
	public Long getPhone() {
		return phone;
	}
	public String getAvatar() {
		return avatar;
	}
	
}
