package com.javaex.vo;

public class DyCheckinVo {
	
	//필드
	private int checkinNum;
	private int userNum;
	private String checkinDate;
	
	
	//생성자
	public DyCheckinVo() {
		super();
	}

	public DyCheckinVo(int checkinNum, int userNum, String checkinDate) {
		super();
		this.checkinNum = checkinNum;
		this.userNum = userNum;
		this.checkinDate = checkinDate;
	}

	
	public int getCheckinNum() {
		return checkinNum;
	}

	public void setCheckinNum(int checkinNum) {
		this.checkinNum = checkinNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}

	
	@Override
	public String toString() {
		return "DyCheckinVo [checkinNum=" + checkinNum + ", userNum=" + userNum + ", checkinDate=" + checkinDate + "]";
	}

}
