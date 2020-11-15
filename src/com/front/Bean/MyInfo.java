package com.front.Bean;

import java.util.Date;

/*存放学生个人信息的javaBean*/
public class MyInfo {
	private String name;
	private Long sno;
	private String sex;
	private Date birthday;
	private String major;   //专业
	private String myClass; //班级
	private String address;
	private Long phone;  
	private String remark;  //备注
	
	public MyInfo(String name, Long sno, String sex, Date birthday, String major, String myClass, String address,
			Long phone, String remark) {
		super();
		this.name = name;
		this.sno = sno;
		this.sex = sex;
		this.birthday = birthday;
		this.major = major;
		this.myClass = myClass;
		this.address = address;
		this.phone = phone;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public Long getSno() {
		return sno;
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

	public String getMyClass() {
		return myClass;
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



