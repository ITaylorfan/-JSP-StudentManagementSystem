package com.front.Bean;

public class MyCourseInfo {
	private Integer cno;
	private String cname;
	private String study_time;  //ѧʱ
	private Integer credit;    //ѧ��
	private String teacher;  //��ʦ
	public MyCourseInfo(Integer cno, String cname, String study_time, Integer credit,String teacher) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.study_time = study_time;
		this.credit = credit;
		this.teacher=teacher;
	}
	public Integer getCno() {
		return cno;
	}
	public String getCname() {
		return cname;
	}
	public String getStudy_time() {
		return study_time;
	}
	public Integer getCredit() {
		return credit;
	}
	public String getTeacher() {
		return teacher;
	}
	
}
