// src/main/java/com/javaex/vo/ChallengesVo.java

package com.javaex.vo;

public class HmkChallengeVo {
	//필드
	private int roomNum;
	private String roomTitle;
	private String roomStartDate;
	private String endDate;
	private String roomThumbNail;
	
	//생성자
	public HmkChallengeVo() {
		super();
	}
	public HmkChallengeVo(int roomNum, String roomTitle, String roomStartDate, String endDate, String roomThumbNail) {
		super();
		this.roomNum = roomNum;
		this.roomTitle = roomTitle;
		this.roomStartDate = roomStartDate;
		this.endDate = endDate;
		this.roomThumbNail = roomThumbNail;
	}
	// getter - setter
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomTitle() {
		return roomTitle;
	}
	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}
	public String getRoomStartDate() {
		return roomStartDate;
	}
	public void setRoomStartDate(String roomStartDate) {
		this.roomStartDate = roomStartDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRoomThumbNail() {
		return roomThumbNail;
	}
	public void setRoomThumbNail(String roomThumbNail) {
		this.roomThumbNail = roomThumbNail;
	}
	
	//일반 메소드
	
	@Override
	public String toString() {
		return "ChallengeVo [roomNum=" + roomNum + ", roomTitle=" + roomTitle + ", roomStartDate=" + roomStartDate
				+ ", endDate=" + endDate + ", roomThumbNail=" + roomThumbNail + "]";
	}
	

	
}
